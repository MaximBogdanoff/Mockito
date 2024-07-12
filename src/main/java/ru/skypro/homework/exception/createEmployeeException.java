package ru.skypro.homework.exception;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ComponentScan
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class createEmployeeException extends RuntimeException {
    public createEmployeeException() {
    }

    public createEmployeeException(String message) {
        super(message);
    }

    public createEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public createEmployeeException(Throwable cause) {
        super(cause);
    }

    public createEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}