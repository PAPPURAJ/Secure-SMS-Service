package com.example.securesmsservice.message;

public class MessageData {
    private String message;
    private boolean isSend;

    public MessageData(String message, boolean isSend) {
        this.message = message;
        this.isSend = isSend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSend() {
        return isSend;
    }
}
