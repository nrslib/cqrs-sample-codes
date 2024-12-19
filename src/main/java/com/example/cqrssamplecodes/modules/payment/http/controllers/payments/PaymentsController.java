package com.example.cqrssamplecodes.modules.payment.http.controllers.payments;

import com.example.cqrssamplecodes.http.exceptions.NotFoundException;
import com.example.cqrssamplecodes.modules.payment.app.adaptor.aggregates.paymentprocess.commands.PaymentRequest;
import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.paymentprocess.PaymentProcessId;
import com.example.cqrssamplecodes.modules.payment.app.application.query.user.queries.GetAccount;
import com.example.cqrssamplecodes.modules.payment.app.application.query.user.queries.GetAccountResult;
import com.example.cqrssamplecodes.modules.payment.http.models.payments.post.PaymentsPostRequest;
import com.example.cqrssamplecodes.modules.payment.http.models.payments.post.PaymentsPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(description = "Payment API", name = "Payment API")
@RestController
@RequestMapping("/api/payments")
public record PaymentsController(CommandGateway commandGateway, QueryGateway queryGateway) {
    @Operation(summary = "Request payment.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentsPostResponse post(PaymentsPostRequest request) {
        var query = new GetAccount(request.accountId());
        var getAccountResult = queryGateway.query(query, GetAccountResult.class).join();
        if (getAccountResult.accountData().isEmpty()) {
            throw new NotFoundException("Account not found");
        }

        PaymentProcessId paymentProcessId = commandGateway.sendAndWait(new PaymentRequest(request.accountId(), request.amount(), request.paymentMethod()));

        return new PaymentsPostResponse(paymentProcessId.value());
    }
}
