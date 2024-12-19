package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events;


import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

public record PaymentProcessFailed(PaymentProcessId paymentProcessId) implements PaymentProcessEvent {
}
