package com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

import java.math.BigDecimal;

public record CreditApply(PaymentProcessId paymentProcessId, AccountId accountId, BigDecimal amount, int count) {
    public CreditApply(PaymentProcessId paymentProcessId, AccountId accountId, BigDecimal amount) {
        this(paymentProcessId, accountId, amount, 0);
    }

    public CreditApply countUp() {
        return new CreditApply(paymentProcessId, accountId, amount, count + 1);
    }
}
