package com.example.cqrssamplecodes.modules.payment.app.application.process.paymentprocess.request.complete;

import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands.PaymentProcessComplete;
import com.example.cqrssamplecodes.modules.payment.app.application.external.notification.protocol.sendpaymentexecution.NotificationPaymentExecutionSent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessCompleteStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(NotificationPaymentExecutionSent event) {
        commandGateway.send(new PaymentProcessComplete(event.paymentProcessId()));
    }
}
