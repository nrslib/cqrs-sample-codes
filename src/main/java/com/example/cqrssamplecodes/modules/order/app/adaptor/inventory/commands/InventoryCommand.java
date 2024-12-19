package com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface InventoryCommand {
    @TargetAggregateIdentifier
    String productId();
}
