package com.solvd.realestate.exceptions;

public class DateBeforeTodayException extends Exception {

    public DateBeforeTodayException(String message) {
        super(message);
    }
}