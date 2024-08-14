package com.pbyt.finance.exception;

public class BadRequestHandler extends Exception{
    public BadRequestHandler(String error){
        super(error);
    }
}
