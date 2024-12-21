package com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands;

import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record OrderConfirm(@TargetAggregateIdentifier OrderId orderId) {
}
