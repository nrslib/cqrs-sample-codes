package com.example.cqrssamplecodes.modules.payment.http.models.payments.post;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentsPostRequest(UUID accountId, BigDecimal amount, PaymentMethod paymentMethod) {
}
