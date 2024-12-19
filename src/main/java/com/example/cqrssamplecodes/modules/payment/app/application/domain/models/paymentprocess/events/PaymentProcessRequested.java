package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentMethod;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

import java.math.BigDecimal;

public record PaymentProcessRequested(PaymentProcessId paymentProcessId, AccountId accountId, BigDecimal amount, PaymentMethod paymentMethod) implements PaymentProcessEvent {
}
