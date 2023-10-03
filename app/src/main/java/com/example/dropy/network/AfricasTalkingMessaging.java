package com.example.dropy.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AfricasTalkingMessaging {

    public void message() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "username=sandbox&to=+254794700294,+254738523316&message=This is a sample message bro.&enqueue=1");
        Request request = new Request.Builder()
                .url("https://api.sandbox.africastalking.com/version1/messaging")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        Log.d("nhujhk", "message:" + response);
    }
}
