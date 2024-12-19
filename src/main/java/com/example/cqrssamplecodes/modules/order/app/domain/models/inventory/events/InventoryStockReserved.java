package com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events;

import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;

public record InventoryStockReserved(String productId, OrderId orderId, int quantity) implements InventoryEvent {
}
