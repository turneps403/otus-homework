package com.otus.homework.my.aggregators;

import com.otus.homework.my.commands.CreateBillingCommand;
import com.otus.homework.my.events.CreateBillingEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bill")
public class BillAggregator implements Aggregator<CreateBillingCommand, CreateBillingEvent> {
    private CreateBillingEvent event;

    @Override
    public CreateBillingEvent getEvent() {
        return event;
    }

    public void setEvent(CreateBillingEvent event) {
        this.event = event;
    }

    @Override
    public void convertCommandToEvent(CreateBillingCommand cmd) {
        CreateBillingEvent ev = new CreateBillingEvent();
        ev.setUserID(cmd.getUserID());
        this.setEvent(ev);
    }
}
