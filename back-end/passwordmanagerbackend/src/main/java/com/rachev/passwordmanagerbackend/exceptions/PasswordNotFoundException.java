package com.rachev.passwordmanagerbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordNotFoundException extends RuntimeException
{
    public PasswordNotFoundException(String message)
    {
        super(message);
    }
}
