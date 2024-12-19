package com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account;

import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.shared.AccountData;

import java.util.UUID;

public interface AccountApi {
    AccountData get(UUID accountId);
}
