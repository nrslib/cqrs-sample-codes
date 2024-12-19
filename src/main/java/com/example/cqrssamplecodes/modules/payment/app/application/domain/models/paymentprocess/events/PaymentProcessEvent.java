package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

public interface PaymentProcessEvent {
    PaymentProcessId paymentProcessId();
}
