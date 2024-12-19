package com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands;

public record InventoryCreate(String productId, String name) implements InventoryCommand {
}
