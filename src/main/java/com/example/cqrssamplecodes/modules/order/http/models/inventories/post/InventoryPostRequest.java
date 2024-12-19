package com.example.cqrssamplecodes.modules.order.http.models.inventories.post;

import io.swagger.v3.oas.annotations.media.Schema;

public record InventoryPostRequest(@Schema(example = "pen") String name) {
}
