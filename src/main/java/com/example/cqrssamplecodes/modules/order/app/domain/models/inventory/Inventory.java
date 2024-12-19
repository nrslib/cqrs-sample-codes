package com.example.cqrssamplecodes.modules.order.app.domain.models.inventory;

import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.*;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;

public record Inventory(String productId, int availableQuantity) {
    public static InventoryCreated create(String productId) {
        return new InventoryCreated(productId);
    }

    public static Inventory applyEvent(InventoryCreated event) {
        return new Inventory(event.productId(), 0);
    }

    public boolean isAvailable(int quantity) {
        return availableQuantity >= quantity;
    }

    public InventoryEvent reserveStock(OrderId orderId, int quantity) {
        if (isAvailable(quantity)) {
            return new InventoryStockReserved(productId, orderId, quantity);
        } else {
            return new InventoryStockReservationFailed(productId, orderId);
        }
    }

    public InventoryStockAdded addStock(int quantity) {
        return new InventoryStockAdded(productId, quantity);
    }

    public Inventory applyEvent(InventoryEvent event) {
        return switch (event) {
            case InventoryCreated created -> applyEvent(created);
            case InventoryStockAdded stockAdded -> new Inventory(productId, availableQuantity + stockAdded.quantity());
            case InventoryStockReserved stockReserved -> new Inventory(productId, availableQuantity - stockReserved.quantity());
            case InventoryStockReservationFailed _ -> this;
            default -> throw new IllegalArgumentException("Unknown event type: " + event);
        };
    }
}
