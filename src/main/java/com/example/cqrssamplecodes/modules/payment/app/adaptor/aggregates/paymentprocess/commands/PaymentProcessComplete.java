package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

public record PaymentProcessComplete(PaymentProcessId paymentProcessId) implements PaymentProcessCommand {
}
