package com.example.cqrssamplecodes.modules.user.app.application.query.user.queries;

import org.springframework.data.domain.Pageable;

public record FindUserDataModel(String userName, Pageable pageable) {
}
