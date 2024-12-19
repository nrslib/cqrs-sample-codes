package com.example.cqrssamplecodes.modules.order.http.models.inventories.find;

import com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory.InventoryDataModel;
import org.springframework.data.domain.Page;

public record InventoryFindResponse(Page<InventoryDataModel> inventories) {
}
