package com.epam.jwd.hardziyevich.hr.model.entity;


import com.epam.jwd.hardziyevich.hr.exception.UnknownEntityException;

import java.util.Arrays;

public enum Role {
    GUEST(0),
    CLIENT(1),
    ADMIN(2);

    private long id = 0;

    Role(long id) {
        this.id = id;
    }

    public static Role defineRoleByName(String roleName) {
        return Arrays.stream(values())
                .filter(role -> role.name().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Unknown role: " + roleName));
    }

    public long getId() {
        return id;
    }

    public String getRoleName() {
        return this.name();
    }
}