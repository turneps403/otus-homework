package com.otus.homework.my.aggregators;

import com.otus.homework.my.commands.CreateUserCommand;
import com.otus.homework.my.events.CreateUserEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("user")
public class UserAggregator implements Aggregator<CreateUserCommand, CreateUserEvent> {
    private CreateUserEvent event;

    public CreateUserEvent getEvent() {
        return event;
    }
    public void setEvent(CreateUserEvent e) {
        event = e;
    }

    @Override
    public void convertCommandToEvent(CreateUserCommand ucmd) {
        final String userID = UUID.randomUUID().toString();

        CreateUserEvent ev = new CreateUserEvent();
        ev.setUserID(userID);
        ev.setFirstName(ucmd.getFirstName());
        ev.setLastName(ucmd.getLastName());

        this.setEvent(ev);
    }

}