package com.example.cqrssamplecodes.modules.order.app.adaptor.inventory;

import com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands.InventoryAddStock;
import com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands.InventoryCreate;
import com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands.InventoryReserveStock;
import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.Inventory;
import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.InventoryCreated;
import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.InventoryEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class InventoryAggregateAdaptor {
    private Inventory inventory;

    @AggregateIdentifier
    public String productId() {
        if (inventory == null) {
            return null;
        }

        return inventory.productId();
    }

    protected InventoryAggregateAdaptor() {
    }

    @CommandHandler
    public InventoryAggregateAdaptor(InventoryCreate command) {
        var event = Inventory.create(command.productId());
        apply(event);
    }

    @CommandHandler
    public void handle(InventoryAddStock command) {
        var event = inventory.addStock(command.quantity());
        apply(event);
    }

    @CommandHandler
    public void handle(InventoryReserveStock command) {
        var event = inventory.reserveStock(command.orderId(), command.quantity());
        apply(event);
    }

    @EventSourcingHandler
    public void on(InventoryEvent event) {
        if (inventory != null) {
            inventory = inventory.applyEvent(event);
        } else {
            if (event instanceof InventoryCreated inventoryCreated) {
                inventory = Inventory.applyEvent(inventoryCreated);
            } else {
                throw new IllegalStateException("Cannot apply event to non-existing inventory");
            }
        }
    }
}
