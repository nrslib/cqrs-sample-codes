package com.example.cqrssamplecodes.modules.payment.app.application.process.paymentprocess.request.request;

import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands.PaymentProcessFail;
import com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution.CreditRejected;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessRequestCompensation(CommandGateway commandGateway) {
    @EventHandler
    public void on(CreditRejected event) {
        commandGateway.send(new PaymentProcessFail(event.paymentProcessId()));
    }
}
