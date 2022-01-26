package com.example.securesmsservice;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import com.example.securesmsservice.conversation.SMSData;
import com.example.securesmsservice.message.MessageData;

import java.util.ArrayList;
import java.util.Date;

public class SMS_System {
    Context context;

    public SMS_System(Context context) {
        this.context = context;
    }

    public ArrayList<MessageData> getAllSms(String num) {
        ArrayList<MessageData> smsData=new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;
        if (c != null) {
            totalSMS = c.getCount();
            Log.e("====="," "+totalSMS);
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                    String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                    String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
                    Date dateFormat= new Date(Long.valueOf(smsDate));

                    if(!num.equals(number))
                        continue;

                    // Toast.makeText(this, ""+body, Toast.LENGTH_SHORT).show();
                    Log.e("===",body);
                    boolean type = true;
                    switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
                        case Telephony.Sms.MESSAGE_TYPE_INBOX:
                            type = false;
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_SENT:
                            type = true;
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                            type = true;
                            break;
                        default:
                            break;
                    }

                    smsData.add(new MessageData(body,type));

                    c.moveToNext();
                }
            }

            c.close();

        } else {

        }
        return smsData;
    }


    public ArrayList<String> getConversation() {
        ArrayList<String> smsData=new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;
        if (c != null) {
            totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                    if(!smsData.contains(number))
                        smsData.add(number);
                    c.moveToNext();
                }
            }
            c.close();
        }
        return smsData;
    }

}
