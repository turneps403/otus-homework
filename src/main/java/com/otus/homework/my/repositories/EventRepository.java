package com.otus.homework.my.repositories;

import com.otus.homework.my.events.Event;

public interface EventRepository {
    public void save(Event ev);
}
