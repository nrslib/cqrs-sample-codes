package com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands;

import java.util.List;

public record OrderCreate(List<OrderItem> items) {
}
