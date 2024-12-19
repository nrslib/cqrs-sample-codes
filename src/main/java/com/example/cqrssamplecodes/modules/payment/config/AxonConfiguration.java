package com.example.cqrssamplecodes.modules.payment.config;

import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.PaymentProcessAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {
    @Bean
    public EventSourcingRepository<PaymentProcessAggregate> paymentAggregateEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(PaymentProcessAggregate.class)
                .eventStore(eventStore)
                .build();
    }
}
