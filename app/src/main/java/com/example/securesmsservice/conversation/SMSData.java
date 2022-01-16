package com.example.securesmsservice.conversation;

public class SMSData {
        private String smsDate;
        private String number;
        private String body;
        private String type;

    public SMSData(String smsDate, String number, String body, String type) {
        this.smsDate = smsDate;
        this.number = number;
        this.body = body;
        this.type = type;
    }

    public String getSmsDate() {
        return smsDate;
    }

    public String getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }

    public String getType() {
        return type;
    }

}
