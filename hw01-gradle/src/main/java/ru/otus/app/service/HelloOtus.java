package ru.otus.app.service;

import com.google.common.base.Joiner;

public class HelloOtus {
    public String getGreeting() {
        Joiner joiner = Joiner.on(" ").skipNulls();
        return joiner.join("Hello", null, "world", "!");
    }
}