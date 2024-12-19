package com.example.cqrssamplecodes.modules.user.app.application.domain.models.user;

import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.events.UserEvent;

public record UserModified(UserId userId, String name) implements UserEvent {
}
