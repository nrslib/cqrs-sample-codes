package com.example.cqrssamplecodes.modules.user.http.controllers.users;

import com.example.cqrssamplecodes.http.exceptions.NotFoundException;
import com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands.UserCreate;
import com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands.UserDelete;
import com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands.UserModify;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.UserId;
import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.FindUserDataModel;
import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.FindUserDataModelResult;
import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.GetUserDataModel;
import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.GetUserDataModelResult;
import com.example.cqrssamplecodes.modules.user.http.models.users.delete.UsersDeleteResponse;
import com.example.cqrssamplecodes.modules.user.http.models.users.find.UserFindResponse;
import com.example.cqrssamplecodes.modules.user.http.models.users.get.UserGetResponse;
import com.example.cqrssamplecodes.modules.user.http.models.users.post.UsersPostRequest;
import com.example.cqrssamplecodes.modules.user.http.models.users.post.UsersPostResponse;
import com.example.cqrssamplecodes.modules.user.http.models.users.put.UsersPutRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Tag(description = "User API", name = "User API")
@RestController
@RequestMapping("/api/users")
public record UsersController(CommandGateway commandGateway, QueryGateway queryGateway) {
    @Operation
    @GetMapping("{userId}")
    public UserGetResponse get(@PathVariable UUID userId) throws ExecutionException, InterruptedException {
        var criteria = new GetUserDataModel(userId);
        var result = queryGateway.query(criteria, GetUserDataModelResult.class).get();

        var user = result.userDataModel().orElseThrow(NotFoundException::new);
        return new UserGetResponse(user.getUserId(), user.getName());
    }

    @Operation
    @GetMapping
    public UserFindResponse find(@RequestParam String userName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws ExecutionException, InterruptedException {
        var criteria = new FindUserDataModel(userName, PageRequest.of(page, size));
        var result = queryGateway.query(criteria, FindUserDataModelResult.class).get();

        return new UserFindResponse(result.userDataModels());
    }

    @Operation(summary = "Create a new user.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersPostResponse post(@Validated @RequestBody UsersPostRequest request) {
        var command = new UserCreate(request.name());
        UserId userId = commandGateway.sendAndWait(command);

        return new UsersPostResponse(userId.value(), request.name());
    }

    @PutMapping("{userId}")
    public void put(@PathVariable UUID userId, @Validated @RequestBody UsersPutRequest request) {
        var command = new UserModify(new UserId(userId), request.name());
        commandGateway.sendAndWait(command);
    }

    @DeleteMapping("{userId}")
    public UsersDeleteResponse delete(@PathVariable UUID userId) {
        var command = new UserDelete(new UserId(userId));
        commandGateway.sendAndWait(command);

        return new UsersDeleteResponse();
    }
}
