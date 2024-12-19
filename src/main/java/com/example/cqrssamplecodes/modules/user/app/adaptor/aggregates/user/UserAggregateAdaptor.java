package com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user;

import com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands.UserCreate;
import com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands.UserDelete;
import com.example.cqrssamplecodes.modules.user.app.adaptor.aggregates.user.commands.UserModify;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.User;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.UserId;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.events.UserCreated;
import com.example.cqrssamplecodes.modules.user.app.application.domain.models.user.events.UserEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class UserAggregateAdaptor {
    private User user;

    @AggregateIdentifier
    public UserId getAggregateId() {
        if (user == null) {
            return null;
        }

        return user.userId();
    }

    protected UserAggregateAdaptor() {
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public UserId handle(UserCreate command) {
        if (user != null) {
            throw new IllegalStateException("User already exists");
        }

        var id = new UserId();
        var event = User.create(id, command.name());
        AggregateLifecycle.apply(event);

        return id;
    }

    @CommandHandler
    public void handle(UserModify command) {
        var event = user.modify(command.name());
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UserDelete command) {
        var event = user.delete();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserEvent event) {
        if (user != null) {
            user = user.applyEvent(event);
        } else {
            if (event instanceof UserCreated userCreated) {
                user = User.applyEvent(userCreated);
            } else {
                throw new IllegalArgumentException("Event is not a UserCreated");
            }
        }
    }
}