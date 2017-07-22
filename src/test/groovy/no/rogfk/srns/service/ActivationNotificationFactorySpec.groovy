package no.rogfk.srns.service

import com.fasterxml.jackson.databind.ObjectMapper
import no.rogfk.srns.model.ActivationNotification
import spock.lang.Specification

class ActivationNotificationFactorySpec extends Specification {

    private ActivationNotificationFactory activationNotificationFactory

    void setup() {
        activationNotificationFactory = new ActivationNotificationFactory()
    }

    def "Create activation notification"() {
        given:
        ActivationNotification activationNotification = new ActivationNotification(
                lastNotified: System.currentTimeMillis(),
                notifiedCount: 5)
        def json = new ObjectMapper().writeValueAsString(activationNotification)

        when:
        def result = activationNotificationFactory.create(json)

        then:
        result.lastNotified > 0
        result.notifiedCount == 5
    }
}
