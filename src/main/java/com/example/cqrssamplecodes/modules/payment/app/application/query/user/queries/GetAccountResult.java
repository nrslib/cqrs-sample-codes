package com.example.cqrssamplecodes.modules.payment.app.application.query.user.queries;

import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.shared.AccountData;

import java.util.Optional;

public record GetAccountResult(Optional<AccountData> accountData) {
}
