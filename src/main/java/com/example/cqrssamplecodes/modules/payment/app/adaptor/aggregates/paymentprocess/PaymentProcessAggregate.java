package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess;

import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands.PaymentProcessComplete;
import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands.PaymentProcessFail;
import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands.PaymentRequest;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.account.AccountId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcess;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessEvent;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessRequested;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PaymentProcessAggregate {
    private PaymentProcess paymentProcess;

    @AggregateIdentifier
    public PaymentProcessId paymentProcessId() {
        if (paymentProcess == null) {
            return null;
        }

        return paymentProcess.paymentProcessId();
    }

    public PaymentProcess getAggregate() {
        return paymentProcess;
    }

    protected PaymentProcessAggregate() {
    }

    @CommandHandler
    public PaymentProcessAggregate(PaymentRequest command) {
        var accountId = new AccountId(command.accountId());
        var event = PaymentProcess.create(new PaymentProcessId(), accountId, command.amount(), command.paymentMethod());
        apply(event, MetaData.with("processId", event.paymentProcessId().value()));
    }

    @CommandHandler
    public void handle(PaymentProcessComplete command) {
        paymentProcess.complete()
                .either(
                        left -> {
                            throw new IllegalStateException("Unexpected value: " + left);
                        },
                        right -> {
                            apply(right);
                            return null;
                        }
                );
    }

    @CommandHandler
    public void handle(PaymentProcessFail command) {
        var event = paymentProcess.fail();
        apply(event);
    }

    @EventSourcingHandler
    public void on(PaymentProcessEvent event) {
        if (paymentProcess != null) {
            paymentProcess = paymentProcess.applyEvent(event);
        } else {
            if (event instanceof PaymentProcessRequested paymentProcessRequested) {
                paymentProcess = PaymentProcess.applyEvent(paymentProcessRequested);
            } else {
                throw new IllegalStateException("Unexpected value: " + event);
            }
        }
    }
}