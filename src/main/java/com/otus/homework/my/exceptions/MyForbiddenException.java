package com.otus.homework.my.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class MyForbiddenException extends MyBaseException {
    public MyForbiddenException(String message, Exception e) {
        super(message, e);
    }

    public MyForbiddenException(String message) {
        super(message);
    }
}
