package com.example.test.exceptions;

public class BadCredentialException extends RuntimeException {
    public BadCredentialException(String s) {
        super(s);
    }
}