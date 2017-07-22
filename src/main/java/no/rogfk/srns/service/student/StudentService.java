package no.rogfk.srns.service.student;

import no.rogfk.srns.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getStudents();

    Optional<Student> getStudent(String id);

    List<Student> getStudentsWithNoMobile();

    void updateStudent(Student student);

    void updateMobile(String cn, String mobile);
}
