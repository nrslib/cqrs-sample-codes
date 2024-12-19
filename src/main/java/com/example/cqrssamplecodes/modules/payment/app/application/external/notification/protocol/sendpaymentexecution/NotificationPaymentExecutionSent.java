package com.example.cqrssamplecodes.modules.payment.app.application.external.notification.protocol.sendpaymentexecution;


import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

public record NotificationPaymentExecutionSent(PaymentProcessId paymentProcessId, AccountId accountId) {
}
