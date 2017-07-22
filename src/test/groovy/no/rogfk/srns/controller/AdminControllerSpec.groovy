package no.rogfk.srns.controller

import no.rogfk.srns.model.Student

import no.rogfk.srns.service.student.LdapStudentService
import no.rogfk.srns.service.student.StudentService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class AdminControllerSpec extends Specification {

    private AdminController adminController
    private StudentService studentService

    void setup() {
        studentService = Mock(LdapStudentService)
        adminController = new AdminController(studentService: studentService)
    }

    def "Get not activated students"() {
        when:
        def response = adminController.getStudents(null)

        then:
        1 * studentService.getStudents() >> [new Student()]
        response.body.size() == 1
    }

    def "Get students without mobile"() {
        when:
        def response = adminController.getStudents(StudentRequestType.NO_MOBILE.type())

        then:
        1 * studentService.getStudentsWithNoMobile() >> [new Student()]
        response.body.size() == 1
    }

    def "Bad request, unknown student request type"() {
        when:
        def response = adminController.getStudents("unknown type")

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
    }
}
