package com.example.cqrssamplecodes.modules.payment.app.infrastructure.inmem.api.account;

import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.AccountApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.shared.AccountData;

import java.util.UUID;

public class InMemoryAccountApi implements AccountApi {
    @Override
    public AccountData get(UUID accountId) {
        return new AccountData(accountId, "John Doe");
    }
}
