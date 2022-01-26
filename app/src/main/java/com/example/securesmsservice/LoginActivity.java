package com.example.securesmsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.securesmsservice.conversation.MainMenu;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("LoginInfo",MODE_PRIVATE);
       startActivity(new Intent(this, MainMenu.class));
    }


    public  void loginClick(View view){
        EditText name=findViewById(R.id.loginUsernameEt);
        String userName=name.getText().toString();

        String pass=((EditText)findViewById(R.id.loginUserpassEt)).getText().toString();

        if(userName.equals("") || pass.equals("") || !sp.getString("name","-1").equals(userName) || !sp.getString("pass",pass).equals(pass)){
            Toast.makeText(getApplicationContext(),"Please input correct username and pass!",Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainMenu.class));

    }



    public void registerClick(View view){

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        EditText userNameEt=new EditText(this);
        userNameEt.setHint("Enter username");
        EditText userPassEt=new EditText(this);
        userPassEt.setHint("Enter pass");

        linearLayout.addView(userNameEt);
        linearLayout.addView(userPassEt);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Register")
                .setView(linearLayout)
                .setNegativeButton("Cancel",null)
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name=userNameEt.getText().toString();
                        String pass=userPassEt.getText().toString();

                        if(name.equals("") || pass.equals(""))
                        {
                            Toast.makeText(getApplicationContext(),"Please input username and pass!",Toast.LENGTH_LONG).show();
                            return;
                        }
                        sp.edit().putString("name",name).putString("pass",pass).apply();
                        Toast.makeText(getApplicationContext(),"Account created!",Toast.LENGTH_LONG).show();



                    }
                }).show();

    }
}
