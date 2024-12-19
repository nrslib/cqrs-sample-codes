package com.example.cqrssamplecodes.modules.order.app.domain.models.order.events;

import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;

public interface OrderEvent {
    OrderId orderId();
}
