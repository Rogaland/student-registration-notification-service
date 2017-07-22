package no.rogfk.srns.service.token;

import no.rogfk.jwt.SpringJwtTokenizer;
import no.rogfk.srns.model.Student;
import no.rogfk.srns.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivationUrlGenerator {

    @Autowired
    private ConfigService configService;

    @Autowired
    private SpringJwtTokenizer springJwtTokenizer;

    public String get(Student student) {
        StudentToken studentToken = new StudentToken();
        studentToken.setId(student.getCn());

        return springJwtTokenizer.createWithUrl(configService.getBaseUrl(), "t", studentToken);
    }

}
