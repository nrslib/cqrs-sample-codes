package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess;

import java.util.UUID;

public record PaymentProcessId(UUID value) {
    public PaymentProcessId() {
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
