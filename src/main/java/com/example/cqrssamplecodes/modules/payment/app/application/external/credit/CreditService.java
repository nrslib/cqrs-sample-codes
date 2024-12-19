package com.example.cqrssamplecodes.modules.payment.app.application.external.credit;

import com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution.CreditAccepted;
import com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution.CreditApply;
import com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution.CreditMakePaymentFailed;
import com.example.cqrssamplecodes.modules.payment.app.application.external.credit.protocol.sendpaymentexecution.CreditRejected;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.CreditApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.makepayment.CreditMakePaymentRequest;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
public record CreditService(CreditApi creditApi, QueryGateway queryGateway, EventGateway eventGateway) {
    @CommandHandler
    public void handle(CreditApply command) {
        var response = creditApi.makePayment(new CreditMakePaymentRequest(command.accountId(), command.amount()));
        if (response.statusCode() != 200) {
            eventGateway.publish(new CreditMakePaymentFailed(command, null));
            return;
        }

        if (response.accepted()) {
            eventGateway.publish(new CreditAccepted(command.paymentProcessId()));
        } else {
            eventGateway.publish(new CreditRejected(command.paymentProcessId()));
        }
    }

    // Fail したときは Retry するようなこともできる
//    @EventHandler
//    public void on(CreditMakePaymentFailed event) {
//        RetryScheduler.exponentialBackoff(scheduler, event.command().count(), 5, 60, (duration) -> {
//            eventGateway.publish(new CreditMakePaymentCriticalErrorOccurred());
//        }, () -> {
//            var command = event.command().countUp();
//            return new ExternalRetryRequested(command);
//        });
//    }
}
