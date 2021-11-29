package com.demo.demo.exception;

public class ExceptionHandler extends RuntimeException {

    public ExceptionHandler(String string) {
        super("ExceptionHandler thrown");
    }
}
