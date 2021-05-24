package com.epam.jwd.hardziyevich.hr.model.entity;

import com.epam.jwd.hardziyevich.hr.exception.UnknownEntityException;

import java.util.Arrays;

public enum Status {
    HIDDEN(1),
    ACTIVE(0);
    private final int id;

    Status(int id) {
        this.id = id;
    }

    public static Status defineStatusByName(String statusName) {
        return Arrays.stream(values())
                .filter(vacancy -> vacancy.name().equalsIgnoreCase(statusName))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Unknown status: " + statusName));
    }

    public long getId() {
        return id;
    }

    public String geStatusName() {
        return this.name();
    }

}
