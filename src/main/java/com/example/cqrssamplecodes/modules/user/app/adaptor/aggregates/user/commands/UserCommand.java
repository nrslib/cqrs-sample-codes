package com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands;


import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.UserId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface UserCommand {
    @TargetAggregateIdentifier
    UserId userId();
}
