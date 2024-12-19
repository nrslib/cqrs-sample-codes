package com.example.cqrssamplecodes.modules.payment.app.application.process.paymentprocess.request.creditpayment;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentMethod;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.events.PaymentProcessRequested;
import com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution.CreditApply;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessCreditPaymentStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(PaymentProcessRequested event) {
        if (event.paymentMethod() == PaymentMethod.CREDIT) {
            commandGateway.send(new CreditApply(event.paymentProcessId(), event.accountId(), event.amount()));
        }
    }
}
