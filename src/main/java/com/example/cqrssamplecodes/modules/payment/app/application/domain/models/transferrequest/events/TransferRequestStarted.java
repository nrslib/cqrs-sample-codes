package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.events;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.TransferRequestId;

public record TransferRequestStarted(TransferRequestId transferRequestId) implements TransferRequestEvent {
}
