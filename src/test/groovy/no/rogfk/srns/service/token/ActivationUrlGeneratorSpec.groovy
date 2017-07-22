package no.rogfk.srns.service.token

import no.rogfk.srns.Application
import no.rogfk.srns.model.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import spock.lang.Specification

import javax.naming.Name

@IntegrationTest
@SpringApplicationConfiguration(classes = Application)
class ActivationUrlGeneratorSpec extends Specification {

    @Autowired
    private ActivationUrlGenerator activationUrlGenerator

    def "Create activation url"() {
        given:
        def name = Mock(Name) {
            toString() >> "test"
        }
        Student student = new Student(dn: name)

        when:
        def activationUrl = activationUrlGenerator.get(student)

        then:
        activationUrl.startsWith("http://localhost?t=")
    }
}
