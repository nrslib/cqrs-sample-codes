package com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events;

public record InventoryStockAdded(String productId, int quantity) implements InventoryEvent {
}
