package com.example.cqrssamplecodes.modules.order.app.domain.models.order;

import java.util.UUID;

public record OrderId(UUID value) {
    public OrderId() {
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
