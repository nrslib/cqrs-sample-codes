package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.errors.PaymentProcessError;

public class PaymentProcessAggregateException extends RuntimeException {
    public PaymentProcessAggregateException(PaymentProcessError error) {
        super(error.toString());
    }
}
