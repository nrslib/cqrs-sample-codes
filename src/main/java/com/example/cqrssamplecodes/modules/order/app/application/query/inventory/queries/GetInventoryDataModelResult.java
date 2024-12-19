package com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries;

import com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory.InventoryDataModel;

import java.util.Optional;

public record GetInventoryDataModelResult(Optional<InventoryDataModel> inventoryDataModel) {
}
