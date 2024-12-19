package com.example.cqrssamplecodes.modules.user.http.models.users.find;


import com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user.UserDataModel;
import org.springframework.data.domain.Page;

public record UserFindResponse(Page<UserDataModel> users) {
}
