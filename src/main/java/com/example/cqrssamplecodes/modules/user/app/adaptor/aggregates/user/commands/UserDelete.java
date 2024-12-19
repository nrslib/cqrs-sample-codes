package com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands;

import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.UserId;

public record UserDelete(UserId userId) implements UserCommand {
}