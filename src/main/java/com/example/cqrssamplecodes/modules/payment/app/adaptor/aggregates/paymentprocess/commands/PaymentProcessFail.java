package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands;


import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

public record PaymentProcessFail(PaymentProcessId paymentProcessId) implements PaymentProcessCommand {
}
