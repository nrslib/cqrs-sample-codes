package com.example.cqrssamplecodes.modules.order.app.application.query.inventory;

import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.InventoryCreated;
import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.InventoryStockAdded;
import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.InventoryStockReserved;
import com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory.InventoryDataModel;
import com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory.InventoryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record InventoryDataModelProjection(InventoryRepository inventoryRepository) {
    @EventHandler
    public void on(InventoryCreated event) {
        var inventoryDataModel = new InventoryDataModel();
        inventoryDataModel.setProductId(event.productId());

        inventoryRepository.save(inventoryDataModel);
    }

    @EventHandler
    public void on(InventoryStockAdded event) {
        var inventoryDataModel = inventoryRepository.findById(event.productId()).orElseThrow();
        inventoryDataModel.setQuantity(inventoryDataModel.getQuantity() + event.quantity());

        inventoryRepository.save(inventoryDataModel);
    }

    @EventHandler
    public void on(InventoryStockReserved event) {
        var inventoryDataModel = inventoryRepository.findById(event.productId()).orElseThrow();
        inventoryDataModel.setQuantity(inventoryDataModel.getQuantity() - event.quantity());

        inventoryRepository.save(inventoryDataModel);
    }
}
