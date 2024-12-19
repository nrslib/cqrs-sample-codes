package com.example.cqrssamplecodes.modules.payment.app.application.process.paymentprocess.request.transferrequest;

import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.transferrequest.commands.TransferRequestCreate;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentMethod;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessRequested;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessTransferRequestStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(PaymentProcessRequested event) {
        if (event.paymentMethod() == PaymentMethod.BANK_TRANSFER) {
            var command = new TransferRequestCreate(event.paymentProcessId(), event.accountId(), event.amount());
            commandGateway.send(command);
        }
    }
}
