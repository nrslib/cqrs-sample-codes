package com.example.cqrssamplecodes.modules.payment.app.application.external.notification;

import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.PaymentProcessAggregate;
import com.example.cqrssamplecodes.modules.payment.app.application.external.notification.protocol.sendpaymentexecution.NotificationPaymentExecutionSent;
import com.example.cqrssamplecodes.modules.payment.app.application.external.notification.protocol.sendpaymentexecution.NotificationSendPaymentExecution;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(EventGateway eventGateway, EventSourcingRepository<PaymentProcessAggregate> paymentAggregateRepository) {
    @CommandHandler
    public void handle(NotificationSendPaymentExecution command) {
        paymentAggregateRepository.load(command.paymentProcessId().asString())
                .execute(paymentProcessAggregate -> {
                    var payment = paymentProcessAggregate.getAggregate();

                    // Call http client to send notification.

                    eventGateway.publish(new NotificationPaymentExecutionSent(command.paymentProcessId(), payment.accountId()));
                });
    }
}
