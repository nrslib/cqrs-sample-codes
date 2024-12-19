package com.example.cqrssamplecodes.modules.user.app.application.domain.models.user;


import java.util.UUID;

public record UserId(UUID value) {
    public UserId() {
        this(UUID.randomUUID());
    }

    public String asString() {
        return value.toString();
    }

    @Override
    public String toString() {
        return asString();
    }
}
