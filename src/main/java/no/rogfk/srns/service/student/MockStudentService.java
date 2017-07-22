package no.rogfk.srns.service.student;

import lombok.extern.slf4j.Slf4j;
import no.rogfk.srns.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("mock")
@Slf4j
@Service
public class MockStudentService implements StudentService {
    private List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init() {
        Student student1 = new Student();
        student1.setDn(new MockName("test1"));
        student1.setCn("test1");
        student1.setActiveStatus("activeStatus");
        student1.setFullname("test1 test2");
        student1.setLoginDisabled(true);
        student1.setMail("test1@test1.com");
        student1.setMobile("90882080");
        student1.setSchool("school");

        Student student2 = new Student();
        student2.setDn(new MockName("test2"));
        student2.setCn("test2");
        student2.setActiveStatus("activeStatus");
        student2.setFullname("test2 test2");
        student2.setLoginDisabled(true);
        student2.setMail("test2@test2.com");
        student2.setMobile("41402715");
        student2.setSchool("school");

        students.add(student1);
        students.add(student2);
    }

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Optional<Student> getStudent(String id) {
        return students.stream().filter(s -> s.getCn().equals(id)).findFirst();
    }

    @Override
    public List<Student> getStudentsWithNoMobile() {
        return students;
    }

    @Override
    public void updateStudent(Student student) {
    }

    @Override
    public void updateMobile(String cn, String mobile) {
        Optional<Student> student = getStudent(cn);
        if(student.isPresent()) {
            student.get().setMobile(mobile);
        }
    }
}
