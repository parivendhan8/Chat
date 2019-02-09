package com.example.chat;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.chat.Main.App;
import com.example.chat.Pojo.SessionData;
import com.example.chat.databinding.RegisterActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends App {

    RegisterActivityBinding b;
    String user = "", password = "";
    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        b = DataBindingUtil.setContentView(Register.this, R.layout.register_activity);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chat-b3e97.firebaseio.com/");


        b.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = b.username.getText().toString();
                password = b.password.getText().toString();

                if (user.equals(""))
                    b.username.setError("Enter User Name");
                else if (password.equals(""))
                    b.password.setError("Enter User Password");
                else {
                    reference.child("User").child(user).setValue(password)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                        debug("Register_100", "Success");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                debug("Register_101", e.getMessage());
                            }
                    });
                }


            }
        });

    }
}
