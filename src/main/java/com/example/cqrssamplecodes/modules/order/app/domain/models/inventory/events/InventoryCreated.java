package com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events;

public record InventoryCreated(String productId) implements InventoryEvent {
}
