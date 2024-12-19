package com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries;

import org.springframework.data.domain.Pageable;

public record FindInventoryDataModel(Pageable pageable) {
}
