package com.example.chat.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chat.Users;

import org.json.JSONException;

import java.util.Map;

public class App extends AppCompatActivity {

    webService services;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    Response.Listener responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            if (services != null)
                try { services.responseMethod(response); } catch (JSONException e) { e.printStackTrace(); }


        }
    };


    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };



    public void debug(String s, String s1)
    {
        Log.d(s,s1);
    }

    public void restFul(String url, int method, webService services)
    {
        this.services = services;

        StringRequest request = new StringRequest(method, url, responseListener, errorListener )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }

    public interface webService
    {
        void responseMethod(String response) throws JSONException;
    }
}
