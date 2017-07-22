package no.rogfk.srns.controller;

import java.util.Optional;

public enum StudentRequestType {
    NO_MOBILE("no-mobile");

    private String type;

    StudentRequestType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static Optional<StudentRequestType> get(String type) {
        StudentRequestType[] types = StudentRequestType.values();
        for (StudentRequestType studentType : types) {
            if (studentType.type().equals(type)) {
                return Optional.of(studentType);
            }
        }
        return Optional.empty();
    }
}
