package com.example.cqrssamplecodes.modules.user.app.application.query.user.queries;


import com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user.UserDataModel;
import org.springframework.data.domain.Page;

public record FindUserDataModelResult(Page<UserDataModel> userDataModels) {
}
