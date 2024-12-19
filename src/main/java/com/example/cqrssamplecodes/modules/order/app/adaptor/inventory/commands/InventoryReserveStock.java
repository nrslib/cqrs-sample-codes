package com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands;

import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;

public record InventoryReserveStock(String productId, OrderId orderId, int quantity) implements InventoryCommand {
}
