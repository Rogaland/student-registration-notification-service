package no.rogfk.srns.controller

import spock.lang.Specification

class StudentRequestTypeSpec extends Specification {

    def "Get student request type"() {
        when:
        def studentRequestType = StudentRequestType.get(StudentRequestType.NO_MOBILE.type())

        then:
        studentRequestType.isPresent()
        studentRequestType.get() == StudentRequestType.NO_MOBILE
    }
}
