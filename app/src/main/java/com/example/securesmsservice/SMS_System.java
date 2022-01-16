package com.example.securesmsservice;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import com.example.securesmsservice.conversation.SMSData;

import java.util.ArrayList;
import java.util.Date;

public class SMS_System {
    Context context;

    public SMS_System(Context context) {
        this.context = context;
    }

    public ArrayList<SMSData> getAllSms() {

        ArrayList<SMSData> smsData=new ArrayList<>();

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
                    // Toast.makeText(this, ""+body, Toast.LENGTH_SHORT).show();
                    Log.e("===",body);
                    String type = null;
                    switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
                        case Telephony.Sms.MESSAGE_TYPE_INBOX:
                            type = "inbox";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_SENT:
                            type = "sent";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                            type = "outbox";
                            break;
                        default:
                            break;
                    }

                    smsData.add(new SMSData(smsDate,number,body,type));

                    c.moveToNext();
                }
            }

            c.close();

        } else {

        }
        return smsData;
    }

}
