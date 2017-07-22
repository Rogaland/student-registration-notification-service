package no.rogfk.srns.model;

import lombok.Data;

@Data
public class ActivationNotification {
    private long lastNotified;
    private int notifiedCount = 0;

    public void increaseNotifiedCount() {
        notifiedCount++;
    }
}
