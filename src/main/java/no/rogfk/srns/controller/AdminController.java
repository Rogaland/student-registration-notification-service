package no.rogfk.srns.controller;

import lombok.extern.slf4j.Slf4j;
import no.rogfk.srns.controller.response.UrlResponse;
import no.rogfk.srns.exceptions.StudentNotFoundException;
import no.rogfk.srns.model.Student;
import no.rogfk.srns.service.NotificationService;
import no.rogfk.srns.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity getStudents(@RequestParam(required = false) String type) {
        if (type == null) {
            return ResponseEntity.ok(studentService.getStudents());
        } else {
            Optional<StudentRequestType> studentRequestType = StudentRequestType.get(type);
            if (studentRequestType.isPresent() && StudentRequestType.NO_MOBILE == studentRequestType.get()) {
                return ResponseEntity.ok(studentService.getStudentsWithNoMobile());
            } else {
                return ResponseEntity.badRequest().body(String.format("Type '%s' not found", type));
            }
        }
    }

    @RequestMapping(value = "/students/{cn}", method = RequestMethod.PATCH)
    public ResponseEntity updateMobile(@PathVariable String cn, @RequestParam String mobile) {
        studentService.updateMobile(cn, mobile);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public ResponseEntity updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/activation/link/send", method = RequestMethod.POST)
    public ResponseEntity sendActivationLink(@RequestParam String id, @RequestParam String mobile) {
        studentService.updateMobile(id, mobile);
        String activationUrl = notificationService.manualNotifyStudent(id);
        return ResponseEntity.ok(new UrlResponse(activationUrl));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity handleStudentNotFound(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
