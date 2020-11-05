package com.otus.homework.my.aggregators;

import com.otus.homework.my.commands.Command;
import com.otus.homework.my.events.Event;

public interface Aggregator<C extends Command, E extends Event> {
    public E getEvent();
    public void convertCommandToEvent(C cmd);
}

