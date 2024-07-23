package com.futureboost.FutureBoostify.exception;

public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String s) {
        super(s);
    }
    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
