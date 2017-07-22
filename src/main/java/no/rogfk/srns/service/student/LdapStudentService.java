package no.rogfk.srns.service.student;

import lombok.extern.slf4j.Slf4j;
import no.rogfk.srns.utilities.SchoolYear;
import no.rogfk.srns.model.Student;
import no.rogfk.srns.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Profile("!mock")
@Slf4j
@Service
public class LdapStudentService implements StudentService {

    @Autowired
    private ConfigService configService;

    @Autowired
    private SchoolYear schoolYear;

    private List<Student> students;

    private LdapTemplate ldapTemplate;

    @Autowired
    public LdapStudentService(LdapTemplate ldapTemplate) {
        log.info("Student service started.");
        this.ldapTemplate = ldapTemplate;
    }

    @Scheduled(initialDelay = 3000, fixedRate = 900000)
    private void getStudentsNotActivated() {
        log.info("Start searching for students not activated...");
        log.info("Current school year: " + schoolYear.getCurrentSchoolYear());
        String schoolYearFilter = String.format("*%s*", schoolYear.getCurrentSchoolYear());
        LdapQuery filter = query()
                .base(configService.getLdapBase())
                .where("sbasAktiveringTillatt").is("true")
                .and("sbaspersonstreng").like(schoolYearFilter)
                .and("sbasaktivstatus").is("0");

        students = ldapTemplate.find(filter, Student.class);

        log.info("{} students found.", students.size());
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Optional<Student> getStudent(String id) {
        return students.stream().filter(student -> student.getCn().equals(id)).findFirst();
    }

    public List<Student> getStudentsWithNoMobile() {
        return students.stream()
                .filter(student -> student.getMobile() == null)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStudent(Student student) {
        log.info("Update student: " + student.toString());
        ldapTemplate.update(student);
    }

    @Override
    public void updateMobile(String cn, String mobile) {
        Optional<Student> optStudent = getStudent(cn);
        if(optStudent.isPresent()) {
            Student student = optStudent.get();
            student.setMobile(mobile);
            updateStudent(student);
        }
    }
}
