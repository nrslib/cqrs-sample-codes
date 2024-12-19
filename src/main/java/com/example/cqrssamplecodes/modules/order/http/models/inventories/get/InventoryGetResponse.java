package com.example.cqrssamplecodes.modules.order.http.models.inventories.get;

import com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory.InventoryDataModel;

public record InventoryGetResponse(InventoryDataModel inventory) {
}
