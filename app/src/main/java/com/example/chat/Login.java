package com.example.chat;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.chat.Main.App;
import com.example.chat.Pojo.SessionData;
import com.example.chat.databinding.LoginActivityBinding;

public class Login extends App {


    LoginActivityBinding b;
    String user = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        b = DataBindingUtil.setContentView(Login.this, R.layout.login_activity);

        b.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        b.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = b.username.getText().toString();
                password = b.password.getText().toString();

                if (user.equals(""))
                    b.username.setError("Enter User Name");
                else if (password.equals(""))
                    b.password.setError("Enter User Password");
                else
                {
                    SessionData.get().setUserName(user);
                    startActivity(new Intent(Login.this, Users.class));
                }




            }
        });






    }
}
