package com.example.choremonkeys.models.exceptions;

public class InvalidChoresIdException extends RuntimeException {
    public InvalidChoresIdException () {
        super ("Invalid ChoresId");
    }
}
