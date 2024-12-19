package com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events;

import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;

public record InventoryStockReservationFailed(String productId, OrderId orderId) implements InventoryEvent {
}
