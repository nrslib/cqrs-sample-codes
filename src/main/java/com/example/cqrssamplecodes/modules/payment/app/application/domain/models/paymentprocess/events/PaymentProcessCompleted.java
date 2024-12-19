package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

public record PaymentProcessCompleted(PaymentProcessId paymentProcessId, AccountId accountId) implements PaymentProcessEvent {
}
