package com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.transferrequest;

import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.transferrequest.commands.TransferRequestCreate;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.TransferRequest;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.TransferRequestId;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.events.TransferRequestEvent;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.events.TransferRequestStarted;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class TransferRequestAggregate {
    private TransferRequest transferRequest;

    @AggregateIdentifier
    public TransferRequestId transferRequestId() {
        if (transferRequest == null) {
            return null;
        }

        return transferRequest.transferRequestId();
    }

    protected TransferRequestAggregate() {
    }

    @CommandHandler
    public TransferRequestAggregate(TransferRequestCreate command) {
        var event = TransferRequest.create(new TransferRequestId());
        apply(event);
    }

    @EventSourcingHandler
    public void on(TransferRequestEvent event) {
        if (transferRequest != null) {
            transferRequest = transferRequest.applyEvent(event);
        } else {
            if (event instanceof TransferRequestStarted transferRequestStarted) {
                transferRequest = TransferRequest.applyEvent(transferRequestStarted);
            } else {
                throw new IllegalArgumentException("Unknown event: " + event);
            }
        }
    }
}
