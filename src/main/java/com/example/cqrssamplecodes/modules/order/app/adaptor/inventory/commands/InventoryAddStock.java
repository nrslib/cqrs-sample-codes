package com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands;

public record InventoryAddStock(String productId, int quantity) implements InventoryCommand {
}
