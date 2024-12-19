package com.example.cqrssamplecodes.modules.order.http.models.orders.post;

import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderItem;

import java.util.List;

public record OrderPostRequest(List<OrderItem> items) {
}
