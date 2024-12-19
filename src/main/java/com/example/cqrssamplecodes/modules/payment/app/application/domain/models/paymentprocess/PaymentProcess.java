package com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess;


import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.errors.PaymentProcessError;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.errors.PaymentProcessInvalidError;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessCompleted;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessEvent;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessFailed;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessRequested;
import fj.data.Either;

import java.math.BigDecimal;

public record PaymentProcess(PaymentProcessId paymentProcessId, AccountId accountId, boolean error) {
    public static PaymentProcessRequested create(PaymentProcessId paymentProcessId, AccountId accountId, BigDecimal price, PaymentMethod paymentMethod) {
        return new PaymentProcessRequested(paymentProcessId, accountId, price, paymentMethod);
    }

    public static PaymentProcess applyEvent(PaymentProcessRequested event) {
        return new PaymentProcess(event.paymentProcessId(), event.accountId(), false);
    }

    public Either<PaymentProcessError, PaymentProcessCompleted> complete() {
        if (error) {
            return Either.left(new PaymentProcessInvalidError());
        }

        return Either.right(new PaymentProcessCompleted(paymentProcessId, accountId));
    }

    public PaymentProcessFailed fail() {
        return new PaymentProcessFailed(paymentProcessId);
    }

    public PaymentProcess applyEvent(PaymentProcessEvent event) {
        return switch (event) {
            case PaymentProcessRequested paymentProcessRequested -> applyEvent(paymentProcessRequested);
            case PaymentProcessCompleted _ -> this;
            case PaymentProcessFailed _ -> new PaymentProcess(paymentProcessId, accountId, true);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }
}