package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest;

import java.util.UUID;

public record TransferRequestId(UUID value) {
    public TransferRequestId() {
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
