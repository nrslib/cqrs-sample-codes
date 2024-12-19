package com.example.cqrssamplecodes.modules.order.app.domain.models.order;

import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderItem;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.events.OrderCanceled;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.events.OrderConfirmed;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.events.OrderCreated;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.events.OrderEvent;

import java.util.List;

public record Order(OrderId orderId, List<OrderItem> items) {
    public static OrderCreated create(OrderId orderId, List<OrderItem> items) {
        return new OrderCreated(orderId, items);
    }

    public static Order applyEvent(OrderCreated event) {
        return new Order(event.orderId(), event.items());
    }

    public OrderConfirmed confirm() {
        return new OrderConfirmed(orderId);
    }

    public OrderCanceled cancel() {
        return new OrderCanceled(orderId);
    }


    public Order applyEvent(OrderEvent event) {
        return switch (event) {
            case OrderCreated orderCreated -> applyEvent(orderCreated);
            case OrderConfirmed _ -> this;
            case OrderCanceled _ -> this;
            default -> this;
        };
    }
}
