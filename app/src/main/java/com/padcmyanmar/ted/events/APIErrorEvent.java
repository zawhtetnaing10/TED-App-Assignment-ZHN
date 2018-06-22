package com.padcmyanmar.ted.events;


public class APIErrorEvent {
    private String message;

    public APIErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
