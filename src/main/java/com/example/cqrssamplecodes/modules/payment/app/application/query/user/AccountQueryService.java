package com.example.cqrssamplecodes.modules.payment.app.application.query.user;

import com.example.cqrssamplecodes.modules.payment.app.application.query.user.queries.GetAccount;
import com.example.cqrssamplecodes.modules.payment.app.application.query.user.queries.GetAccountResult;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.AccountApi;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public record AccountQueryService(AccountApi accountApi) {
    @QueryHandler
    public GetAccountResult handle(GetAccount query) {
        try {
            var response = accountApi.get(query.accountId());

            return new GetAccountResult(Optional.of(response));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatusCode.valueOf(404)) {
                return new GetAccountResult(Optional.empty());
            } else {
                throw e;
            }
        }
    }
}
