package com.example.securesmsservice.conversation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.securesmsservice.R;
import com.example.securesmsservice.SMS_System;

import java.util.ArrayList;
import java.util.Date;

public class MainMenu extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    ArrayList<SMSData> arrayList=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();

        setContentView(R.layout.activity_main_menu);
        recyclerView=findViewById(R.id.conversationRecy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> sms_system=new SMS_System(this).getConversation();


        recyclerView.setAdapter(new ConversationAdapter(this,sms_system));






    }



    protected void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }






}