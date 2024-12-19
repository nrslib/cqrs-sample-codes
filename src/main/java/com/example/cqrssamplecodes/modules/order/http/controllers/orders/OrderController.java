package com.example.cqrssamplecodes.modules.order.http.controllers.orders;

import com.example.cqrssamplecodes.modules.order.app.adaptor.order.commands.OrderCreate;
import com.example.cqrssamplecodes.modules.order.app.domain.models.order.OrderId;
import com.example.cqrssamplecodes.modules.order.http.models.orders.post.OrderPostRequest;
import com.example.cqrssamplecodes.modules.order.http.models.orders.post.OrderPostResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(description = "Order API", name = "Order API")
@RestController
@RequestMapping("/api/orders")
public record OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
    @PostMapping
    public OrderPostResponse post(@RequestBody OrderPostRequest request) {
        var command = new OrderCreate(request.items());
        OrderId orderId = commandGateway.sendAndWait(command);

        return new OrderPostResponse(orderId.value());
    }
}
