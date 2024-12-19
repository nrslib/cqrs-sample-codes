package com.example.cqrssamplecodes.modules.order.app.domain.models.order.events;

import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderItem;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;

import java.util.List;

public record OrderCreated(OrderId orderId, List<OrderItem> items) implements OrderEvent {
}
