package com.android.virgilsecurity.jwtworkexample.data.model.exception;

/**
 * Created by Danylo Oliinyk on 3/26/18 at Virgil Security.
 * -__o
 */

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
