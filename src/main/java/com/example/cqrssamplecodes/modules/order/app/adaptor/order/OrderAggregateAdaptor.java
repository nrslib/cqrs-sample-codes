package com.example.cqrssamplecodes.modules.order.app.adaptor.order;

import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderCancel;
import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderCreate;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.Order;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.events.OrderCreated;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.events.OrderEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregateAdaptor {
    private Order order;

    @AggregateIdentifier
    public OrderId orderId() {
        if (order == null) {
            return null;
        }

        return order.orderId();
    }

    protected OrderAggregateAdaptor() {
    }

    @CommandHandler
    public OrderAggregateAdaptor(OrderCreate command) {
        var orderId = new OrderId();
        var event = Order.create(orderId, command.items());
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(OrderCancel command) {
        var event = order.cancel();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(OrderEvent event) {
        if (order != null) {
            order = order.applyEvent(event);
        } else {
            if (event instanceof OrderCreated orderCreated) {
                order = Order.applyEvent(orderCreated);
            } else {
                throw new IllegalStateException("Cannot apply event to non-existing inventory");
            }
        }
    }
}
