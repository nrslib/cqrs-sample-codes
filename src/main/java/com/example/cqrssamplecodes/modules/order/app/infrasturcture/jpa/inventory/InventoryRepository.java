package com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InventoryRepository extends JpaRepository<InventoryDataModel, String>, JpaSpecificationExecutor<InventoryDataModel> {
}
