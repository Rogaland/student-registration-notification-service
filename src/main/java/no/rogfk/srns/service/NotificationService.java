package no.rogfk.srns.service;

import lombok.extern.slf4j.Slf4j;
import no.rogfk.sms.SmsService;
import no.rogfk.srns.exceptions.StudentNotFoundException;
import no.rogfk.srns.model.ActivationNotification;
import no.rogfk.srns.model.Student;
import no.rogfk.srns.service.student.StudentService;
import no.rogfk.srns.service.token.ActivationUrlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NotificationService {

    @Autowired
    private ConfigService configService;

    @Autowired
    private ActivationNotificationFactory activationNotificationFactory;

    @Autowired
    private ActivationUrlGenerator activationUrlGenerator;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SmsService smsService;

    public NotificationService() {
        log.info("Notification service started.");
    }


    @Scheduled(initialDelay = 10000, fixedRate = 600000)
    private void notifyStudents() {
        List<Student> students = studentService.getStudents();

        students.forEach(student -> {
            if (student.getMobile() == null) {
                log.info("{} missing mobile.", student.getDn());
            } else {
                ActivationNotification activationNotification = activationNotificationFactory.create(student.getActivationNotification());
                int maxNotificationCount = configService.getMaxNotificationCount();
                int notifiedCount = activationNotification.getNotifiedCount();
                if (notifiedCount < maxNotificationCount) {
                    log.debug("Notifying {}. Sending SMS to {}", student.getFullname(), student.getMobile());
                    notifyStudent(activationNotification, student, true, false);
                } else {
                    log.debug("Max notification count reached for {}, not sending new SMS", student.getDn());
                }
            }
        });
    }

    public String manualNotifyStudent(String id) {
        Optional<Student> optionalStudent = studentService.getStudent(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            ActivationNotification activationNotification = activationNotificationFactory.create(student.getActivationNotification());
            return notifyStudent(activationNotification, student, false, true);
        } else {
            throw new StudentNotFoundException("Could not find student with id " + id);
        }
    }

    private String notifyStudent(ActivationNotification activationNotification, Student student, boolean increaseCount, boolean force) {
        String activationUrl = activationUrlGenerator.get(student);
        if (force || shouldNotify(activationNotification.getLastNotified())) {
            String mobile = student.getMobile();
            activationNotification.setLastNotified(System.currentTimeMillis());
            if (increaseCount) {
                activationNotification.increaseNotifiedCount();
            }

            String message = String.format(configService.getStudentSmsMessage(), activationUrl);
            log.debug("SMS message: {}", message);

            if (force || !configService.isDryRun()) {
                student.setActivationNotification(activationNotificationFactory.write(activationNotification));
                String response = smsService.sendSms(message, mobile);
                log.info("SMS response: {}", response);
                studentService.updateStudent(student);
            }
        }

        return activationUrl;
    }

    boolean shouldNotify(long lastNotified) {
        if (lastNotified <= 0) {
            return true;
        }

        LocalDateTime notificationDate = Instant.ofEpochMilli(lastNotified).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration duration = Duration.between(notificationDate, LocalDateTime.now());
        return duration.toDays() > configService.getMinRetryDays();
    }

}
