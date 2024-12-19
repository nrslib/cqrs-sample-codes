package com.example.cqrssamplecodes.modules.user.app.application.query.user;

import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.UserDeleted;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.UserModified;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.events.UserCreated;
import com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user.UserDataModel;
import com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user.UserDataRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record UserDataModelProjection(UserDataRepository userDataRepository) {
    @EventHandler
    public void on(UserCreated event) {
        var userDataModel = new UserDataModel();
        userDataModel.setUserId(event.userId().asString());
        userDataModel.setName(event.name());

        userDataRepository.save(userDataModel);
    }

    @EventHandler
    public void on(UserModified event) {
        var userDataModel = userDataRepository.findById(event.userId().asString()).orElseThrow();
        userDataModel.setName(event.name());

        userDataRepository.save(userDataModel);
    }

    @EventHandler
    public void on(UserDeleted event) {
        userDataRepository.deleteById(event.userId().asString());
    }
}
