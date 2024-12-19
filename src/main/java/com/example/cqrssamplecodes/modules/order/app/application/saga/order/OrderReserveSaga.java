package com.example.cqrssamplecodes.modules.order.app.application.saga.order;

import com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands.InventoryReserveStock;
import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderCancel;
import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderConfirm;
import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.InventoryStockReservationFailed;
import com.example.cqrssamplecodes.modules.order.app.domain.models.inventory.events.InventoryStockReserved;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.events.OrderCreated;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.HashMap;
import java.util.Map;

@Saga
public class OrderReserveSaga {
    public Map<String, Boolean> productIdToReserved; // public for axon serialization

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreated event, CommandGateway commandGateway) {
        productIdToReserved = new HashMap<>();

        event.items().stream()
                .map(item -> new InventoryReserveStock(item.productId(), event.orderId(), item.quantity()))
                .forEach(command -> {
                    productIdToReserved.put(command.productId(), false);
                    commandGateway.sendAndWait(command);
                });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(InventoryStockReserved event, CommandGateway commandGateway) {
        productIdToReserved.put(event.productId(), true);

        if (productIdToReserved.values().stream().allMatch(Boolean::booleanValue)) {
            commandGateway.send(new OrderConfirm(event.orderId()));
            SagaLifecycle.end();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(InventoryStockReservationFailed event, CommandGateway commandGateway) {
        commandGateway.send(new OrderCancel(event.orderId()));
        SagaLifecycle.end();
    }
}
