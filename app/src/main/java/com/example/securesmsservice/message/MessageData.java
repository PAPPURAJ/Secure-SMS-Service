package com.example.securesmsservice.message;

public class MessageData {
    String message;
    boolean isSend;

    public MessageData(String message, boolean isSend) {
        this.message = message;
        this.isSend = isSend;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSend() {
        return isSend;
    }
}
