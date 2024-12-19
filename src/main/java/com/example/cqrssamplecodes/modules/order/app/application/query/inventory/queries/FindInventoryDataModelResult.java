package com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries;

import com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory.InventoryDataModel;
import org.springframework.data.domain.Page;

public record FindInventoryDataModelResult(Page<InventoryDataModel> inventories) {
}
