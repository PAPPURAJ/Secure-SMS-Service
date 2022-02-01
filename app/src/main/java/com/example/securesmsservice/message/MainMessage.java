package com.example.securesmsservice.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.securesmsservice.R;
import com.example.securesmsservice.SMS_System;
import com.example.securesmsservice.conversation.SMSData;

public class MainMessage extends AppCompatActivity {
    private String num;
    private RecyclerView recyclerView;
    private EditText msgEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);
        msgEt=findViewById(R.id.mainMsgEt);
        num=getIntent().getStringExtra("number");
        recyclerView=findViewById(R.id.mainMsgRecy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainMsgAdapter(this,new SMS_System(this).getAllSms(num)));

    }

    public void mainMsgSendClick(View view) {
        String msg = msgEt.getText().toString();
        if(msg.equals("")){
            Toast.makeText(this, "Message is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        msgEt.setText("");


    }
}