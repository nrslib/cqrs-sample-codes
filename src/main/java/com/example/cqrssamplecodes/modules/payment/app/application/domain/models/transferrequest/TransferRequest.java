package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.events.TransferRequestEvent;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.events.TransferRequestStarted;

public record TransferRequest(TransferRequestId transferRequestId) {
    public static TransferRequestStarted create(TransferRequestId transferRequestId) {
        return new TransferRequestStarted(transferRequestId);
    }

    public static TransferRequest applyEvent(TransferRequestStarted event) {
        return new TransferRequest(event.transferRequestId());
    }

    public TransferRequest applyEvent(TransferRequestEvent event) {
        return switch (event) {
            case TransferRequestStarted transferRequestStarted -> applyEvent(transferRequestStarted);
            default -> throw new IllegalArgumentException("Unknown event: " + event);
        };
    }
}
