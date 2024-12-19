package com.example.cqrssamplecodes.modules.order.app.application.query.inventory;

import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.FindInventoryDataModel;
import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.FindInventoryDataModelResult;
import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.GetInventoryDataModel;
import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.GetInventoryDataModelResult;
import com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory.InventoryRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record InventoryDataModelQueryService(InventoryRepository inventoryRepository) {
    @QueryHandler
    public GetInventoryDataModelResult handle(GetInventoryDataModel query) {
        return inventoryRepository.findById(query.productId())
                .map(it -> new GetInventoryDataModelResult(Optional.of(it)))
                .orElseGet(() -> new GetInventoryDataModelResult(Optional.empty()));
    }

    @QueryHandler
    public FindInventoryDataModelResult handle(FindInventoryDataModel query) {
        var inventories = inventoryRepository.findAll(query.pageable());

        return new FindInventoryDataModelResult(inventories);
    }
}
