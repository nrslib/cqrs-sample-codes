package com.example.cqrssamplecodes.modules.user.app.application.domain.models.user;

import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.events.UserCreated;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.events.UserEvent;

public record User(UserId userId, boolean deleted) {
    public static UserCreated create(UserId docId, String name) {
        return new UserCreated(docId, name);
    }

    public static User applyEvent(UserCreated event) {
        return new User(event.userId(), false);
    }

    public UserModified modify(String name) {
        if (deleted) {
            throw new IllegalStateException("User is deleted"); // If you want to create a domain exception, you can create a new exception class and throw it here.
        }

        return new UserModified(userId, name);
    }

    public UserDeleted delete() {
        return new UserDeleted(userId);
    }

    public User applyEvent(UserEvent event) {
        return switch (event) {
            case UserCreated _ -> applyEvent((UserCreated) event);
            case UserModified _ -> this;
            case UserDeleted _ -> new User(userId(), true);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }
}
