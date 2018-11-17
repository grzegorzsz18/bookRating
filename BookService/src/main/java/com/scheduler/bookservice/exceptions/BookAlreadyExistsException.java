package com.scheduler.bookservice.exceptions;

public class BookAlreadyExistsException extends RuntimeException{

    public BookAlreadyExistsException(String s) {
        super(s);
    }
}
