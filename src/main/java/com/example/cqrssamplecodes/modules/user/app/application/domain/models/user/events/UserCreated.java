package com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.events;


import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.UserId;

public record UserCreated(
        UserId userId,
        String name
) implements UserEvent {
}
