package no.rogfk.srns.service

import spock.lang.Specification

import java.time.LocalDateTime
import java.time.ZoneId

class NotificationServiceSpec extends Specification {
    private NotificationService notificationService

    void setup() {
        def configService = Mock(ConfigService) {
            getMinRetryDays() >> 5
        }
        notificationService = new NotificationService(configService: configService)
    }

    def "Notify student when last notified equals 0"() {
        when:
        def notify = notificationService.shouldNotify(0)

        then:
        notify
    }

    def "Do not notify student when last notified is now"() {
        when:
        def notify = notificationService.shouldNotify(System.currentTimeMillis())

        then:
        !notify
    }

    def "Notify when time since days since last notification is higher than min-retry-days"() {
        given:
        def lastNotified = LocalDateTime.of(2015, 1, 1, 0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        when:
        def notify = notificationService.shouldNotify(lastNotified)

        then:
        notify
    }
}
