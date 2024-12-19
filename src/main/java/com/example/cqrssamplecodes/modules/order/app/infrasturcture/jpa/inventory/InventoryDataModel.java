package com.example.cqrssamplecodes.modules.order.app.infrasturcture.jpa.inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inventories")
public class InventoryDataModel {
    @Id
    String productId;
    @Column
    int quantity;
}
