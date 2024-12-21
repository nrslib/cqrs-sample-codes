package com.example.cqrssamplecodes.modules.order.http.controllers.inventories;

import com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands.InventoryAddStock;
import com.example.cqrssamplecodes.modules.order.app.adaptor.inventory.commands.InventoryCreate;
import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.FindInventoryDataModel;
import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.FindInventoryDataModelResult;
import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.GetInventoryDataModel;
import com.example.cqrssamplecodes.modules.order.app.application.query.inventory.queries.GetInventoryDataModelResult;
import com.example.cqrssamplecodes.modules.order.http.models.inventories.find.InventoryFindResponse;
import com.example.cqrssamplecodes.modules.order.http.models.inventories.get.InventoryGetResponse;
import com.example.cqrssamplecodes.modules.order.http.models.inventories.post.InventoryPostRequest;
import com.example.cqrssamplecodes.modules.order.http.models.inventories.post.InventoryPostResponse;
import com.example.cqrssamplecodes.modules.order.http.models.inventories.stock.InventoryStockRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Tag(description = "Inventory API", name = "Inventory API")
@RestController
@RequestMapping("/api/inventories")
public record InventoryController(CommandGateway commandGateway, QueryGateway queryGateway) {
    @GetMapping("{productId}")
    public InventoryGetResponse get(@PathVariable String productId) throws ExecutionException, InterruptedException {
        var query = new GetInventoryDataModel(productId);
        var result = queryGateway.query(query, GetInventoryDataModelResult.class).get();

        return new InventoryGetResponse(result.inventoryDataModel().orElseGet(null));
    }

    @GetMapping
    public InventoryFindResponse find(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws ExecutionException, InterruptedException {
        var pageable = PageRequest.of(page, size);
        var query = new FindInventoryDataModel(pageable);
        var result = queryGateway.query(query, FindInventoryDataModelResult.class).get();

        return new InventoryFindResponse(result.inventories());
    }

    @PostMapping
    public InventoryPostResponse post(@RequestBody InventoryPostRequest request) {
        var command = new InventoryCreate(request.name(), request.name());
        String productId = commandGateway.sendAndWait(command);

        return new InventoryPostResponse(productId);
    }

    @PostMapping("{productId}/stock")
    public void stock(@PathVariable String productId, @RequestBody InventoryStockRequest request) {
        var command = new InventoryAddStock(productId, request.quantity());
        commandGateway.sendAndWait(command);
    }
}
