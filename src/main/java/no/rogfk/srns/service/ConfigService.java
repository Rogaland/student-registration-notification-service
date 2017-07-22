package no.rogfk.srns.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ConfigService {

    @Value("${max-notification-count:5}")
    private int maxNotificationCount;

    @Value("${spring.ldap.base}")
    private String ldapBase;

    @Value("${jwt.base-url}")
    private String baseUrl;

    @Value("${spring.application.student-sms-message}")
    private String studentSmsMessage;

    @Value("${spring.application.dry-run:true}")
    private boolean dryRun;

    @Value("${spring.application.min-retry-days:5}")
    private int minRetryDays;

}
