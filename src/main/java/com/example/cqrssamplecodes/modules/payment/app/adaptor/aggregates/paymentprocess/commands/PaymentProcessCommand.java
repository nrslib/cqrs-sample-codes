package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface PaymentProcessCommand {
    @TargetAggregateIdentifier
    PaymentProcessId paymentProcessId();
}
