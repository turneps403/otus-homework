package com.otus.homework02.my.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBaseException extends RuntimeException {
    public MyBaseException(String message, Exception e) {
        super(message);
        log.error(message, e);
    }

    public MyBaseException(String message) {
        super(message);
    }
}
