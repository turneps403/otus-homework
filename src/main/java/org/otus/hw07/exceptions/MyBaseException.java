package org.otus.hw07.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBaseException extends RuntimeException {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public MyBaseException(String message, Exception e) {
        super(message);
        log.error(message + "; " + e.toString());
    }

    public MyBaseException(String message) {
        super(message);
    }
}
