package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(UUID accountId, BigDecimal amount, PaymentMethod paymentMethod) {
}
