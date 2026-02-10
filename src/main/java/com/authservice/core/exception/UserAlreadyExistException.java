package com.authservice.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Unable to create user due to a conflict. Username is already taken.")
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String userIdIsAlreadyTaken) {
        super(userIdIsAlreadyTaken);
    }

    public UserAlreadyExistException() {
    }
}
