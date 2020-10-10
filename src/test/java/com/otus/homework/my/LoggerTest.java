package com.otus.homework.my;

import org.junit.Test;

import java.util.logging.Logger;

public class LoggerTest {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Test
    public void test() {
        log.warning("test warning");
    }
}
