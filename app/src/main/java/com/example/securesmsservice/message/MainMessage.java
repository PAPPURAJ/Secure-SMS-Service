package com.example.securesmsservice.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.securesmsservice.R;
import com.example.securesmsservice.SMS_System;
import com.example.securesmsservice.conversation.SMSData;

import java.util.ArrayList;

public class MainMessage extends AppCompatActivity {
    private String num,msg;
    private RecyclerView recyclerView;
    private EditText msgEt;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private ArrayList<MessageData> messageData=new ArrayList<>();
    private MainMsgAdapter mainMsgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);
        msgEt=findViewById(R.id.mainMsgEt);
        num=getIntent().getStringExtra("number");
        recyclerView=findViewById(R.id.mainMsgRecy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageData=new SMS_System(this).getAllSms(num);
        mainMsgAdapter=new MainMsgAdapter(this,recyclerView,messageData);
        recyclerView.setAdapter(mainMsgAdapter);
    }



    public void mainMsgSendClick(View view) {
        msg = msgEt.getText().toString();
        if(msg.equals("")){
            Toast.makeText(this, "Message is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        checkAndSend();
    }


    protected void checkAndSend() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }else{
            sendMsg();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendMsg();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS sending failed!, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

    void sendMsg(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(num, null, msg, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.",
                Toast.LENGTH_LONG).show();
        mainMsgAdapter.addData(new MessageData(msg,true));
        msgEt.setText("");
    }
}