package com.ymagis.exceptions;

public class ApiCallFailedException extends Exception {
    public ApiCallFailedException(String s) {
        super("Api server side error : " + s);
    }
}
