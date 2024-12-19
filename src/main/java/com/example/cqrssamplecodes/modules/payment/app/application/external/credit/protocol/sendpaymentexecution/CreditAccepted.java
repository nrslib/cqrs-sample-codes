package com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;

public record CreditAccepted(PaymentProcessId paymentProcessId) {
}
