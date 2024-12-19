package com.example.cqrssamplecodes.modules.user.app.application.query.user.queries;


import com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user.UserDataModel;

import java.util.Optional;

public record GetUserDataModelResult(Optional<UserDataModel> userDataModel) {
}
