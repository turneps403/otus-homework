package com.otus.homework.my.aggregators;

import com.otus.homework.my.commands.OperationCommand;
import com.otus.homework.my.events.OperationEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("operation")
public class OperationAggregator implements Aggregator<OperationCommand, OperationEvent>  {
    private OperationEvent event;

    @Override
    public OperationEvent getEvent() {
        return event;
    }

    public void setEvent(OperationEvent event) {
        this.event = event;
    }

    @Override
    public void convertCommandToEvent(OperationCommand cmd) {
        OperationEvent event = new OperationEvent(
                cmd.getUserID(),
                cmd.getAmount() > 0 ? -1 * cmd.getAmount() : cmd.getAmount(),
                cmd.getOperID());
        this.setEvent(event);
    }
}
