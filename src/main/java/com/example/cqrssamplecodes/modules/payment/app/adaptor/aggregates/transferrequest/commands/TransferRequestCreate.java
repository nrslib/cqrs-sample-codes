package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.transferrequest.commands;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

import java.math.BigDecimal;

public record TransferRequestCreate(PaymentProcessId paymentProcessId, AccountId accountId, BigDecimal amount) {
}
