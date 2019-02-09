package com.example.chat;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chat.Main.App;
import com.example.chat.Pojo.SessionData;
import com.example.chat.databinding.ChatBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Chat extends App {

    ChatBinding b;
    DatabaseReference reference1, reference2;
    ImageView sendButton;
    EditText messageArea;
    private String message = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        b = DataBindingUtil.setContentView(Chat.this, R.layout.chat);
        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);

        reference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chat-b3e97.firebaseio.com/Messages/" + SessionData.get().getUserName() + "-" + SessionData.get().getChatWith());
        reference2 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chat-b3e97.firebaseio.com/Messages/" + SessionData.get().getChatWith() + "-" + SessionData.get().getUserName());

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = messageArea.getText().toString();

                Map<String, String> map = new HashMap<String, String>();
                map.put("message",message);
                map.put("user", SessionData.get().getUserName());

                reference1.push().setValue(map);
                reference2.push().setValue(map);

            }
        });

        final DataSnapshot[] dataSnapshot1 = new DataSnapshot[1];
        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                dataSnapshot1[0] = dataSnapshot;



                Map<String, String> map = (Map<String, String>) dataSnapshot1[0].getValue();
                String message = map.get("message");
                String userName = map.get("user");

                if (userName.equals(SessionData.get().getUserName()))
                {
                    addMessage("You:-\n" + message, 1);
                }
                else
                {
                    addMessage(SessionData.get().getChatWith()+ ":-\n" + message, 2);
                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }






    public void addMessage(String message, int type)
    {

        TextView textView = new TextView(Chat.this);
        textView.setText(message);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(10,0,0,10);
        textView.setLayoutParams(layoutParams);

        if(type == 1) {
            textView.setBackgroundResource(R.drawable.shape1);
        }
        else{
            textView.setBackgroundResource(R.drawable.shape2);
        }

        b.messageLayout.addView(textView);
        b.scrollView.fullScroll(View.FOCUS_DOWN);

    }



}
