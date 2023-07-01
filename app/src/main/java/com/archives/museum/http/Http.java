package com.archives.museum.http;

import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Http {
    static OkHttpClient okHttpClient;
    public static void get(String url ,Callback callback){
        okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
