package com.example.chat;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.example.chat.Adapter.UserAdapter;
import com.example.chat.Main.App;
import com.example.chat.Pojo.SessionData;
import com.example.chat.Pojo.User;
import com.example.chat.databinding.ActiveUsersBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Users extends App implements App.webService {

    ActiveUsersBinding b;
    UserAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_users);

        b = DataBindingUtil.setContentView(Users.this, R.layout.active_users);
        init();
        LinearLayoutManager layoutManager = new LinearLayoutManager(Users.this);
        b.usersList.setLayoutManager(layoutManager);


    }

    private void init() {

        list = new ArrayList<String>();
        String url = "https://chat-b3e97.firebaseio.com/User.json";
        restFul(url, Request.Method.GET, this);

//        StringRequest request = new StringRequest(Request.Method.GET, url,
//        new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    Iterator i = jsonObject.keys();
//                    String key = "";
//
//                    while (i.hasNext())
//                    {
//                        key = i.next().toString();
//                        if (!key.equalsIgnoreCase("pari"))
//                        list.add(key);
//                    }
//
//                    adapter = new UserAdapter(Users.this, list );
//                    b.usersList.setAdapter(adapter);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        },
//        new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                debug("Users_100", error.getMessage() + "\n" + error.networkResponse);
//            }
//        }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return super.getParams();
//            }
//        };
//
//        RequestQueue queue = Volley.newRequestQueue(Users.this);
//        queue.add(request);
//


    }


    @Override
    public void responseMethod(String response) throws JSONException {

        if (response != null && !response.isEmpty())
        {
            debug("responseMethod", response);

            JSONObject jsonObject = new JSONObject(response);

                    Iterator i = jsonObject.keys();
                    String key = "";

                    while (i.hasNext())
                    {
                        key = i.next().toString();
                        if (!key.equalsIgnoreCase(SessionData.get().getUserName()))
                        list.add(key);
                    }

                    adapter = new UserAdapter(list, new UserAdapter.OnItemClick() {
                        @Override
                        public void onClick(int pos) {
                            String chatWith = list.get(pos);
                            SessionData.get().setChatWith(chatWith);
                            startActivity(new Intent(Users.this, Chat.class));
                        }
                    });
                    b.usersList.setAdapter(adapter);
        }

    }
}
