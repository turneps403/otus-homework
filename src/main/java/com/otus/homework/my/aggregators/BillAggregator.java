package com.otus.homework.my.aggregators;

import com.otus.homework.my.commands.BillingCommand;
import com.otus.homework.my.events.BillingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bill")
public class BillAggregator implements Aggregator<BillingCommand, BillingEvent> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private BillingEvent event;

    @Override
    public BillingEvent getEvent() {
        return event;
    }

    public void setEvent(BillingEvent event) {
        this.event = event;
    }

    @Override
    public void convertCommandToEvent(BillingCommand cmd) {
        log.info("got command " + cmd.toString());
        BillingEvent ev = new BillingEvent(cmd.getUserID(), cmd.getAmount());
        log.info("create event {}", ev);
        this.setEvent(ev);
    }

}
