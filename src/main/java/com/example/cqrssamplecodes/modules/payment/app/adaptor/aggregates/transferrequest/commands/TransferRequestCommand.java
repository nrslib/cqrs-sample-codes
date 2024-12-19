package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.transferrequest.commands;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.TransferRequestId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface TransferRequestCommand {
    @TargetAggregateIdentifier
    TransferRequestId transferRequestId();
}
