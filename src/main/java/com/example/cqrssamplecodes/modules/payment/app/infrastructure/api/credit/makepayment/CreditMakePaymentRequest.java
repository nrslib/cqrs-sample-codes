package com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.makepayment;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;

import java.math.BigDecimal;


public record CreditMakePaymentRequest(AccountId accountId, BigDecimal amount) {
}
