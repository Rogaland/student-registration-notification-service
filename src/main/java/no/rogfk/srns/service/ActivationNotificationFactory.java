package no.rogfk.srns.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import no.rogfk.srns.model.ActivationNotification;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ActivationNotificationFactory {

    public ActivationNotification create(String json) {
        try {
            if (json == null) {
                return new ActivationNotification();
            } else {
                return new ObjectMapper().readValue(json, ActivationNotification.class);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Exception when parsing activation notification JSON. Value: " + json, e);
        }
    }

    public String write(ActivationNotification activationNotification) {
        try {
            return new ObjectMapper().writeValueAsString(activationNotification);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
