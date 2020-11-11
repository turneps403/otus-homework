package org.otus.hw07.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyResourceNotFoundException extends MyBaseException {
    public MyResourceNotFoundException(String message, Exception e) {
        super(message, e);
    }
    public MyResourceNotFoundException(String message) {
        super(message);
    }
}
