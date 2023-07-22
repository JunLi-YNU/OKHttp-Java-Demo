package org.lijun.okhttpjavademo.network.java;


import static android.content.ContentValues.TAG;

import android.util.Log;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

public class JavaSynchronousGet {

    public String synchronousGet(){
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        try (Response response = MyOkHttpClientSingleton.getInstance().okHttpClient.newCall(request).execute()){
            if(!response.isSuccessful()) throw new IOException("UnException Code"+response);
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                Log.i(TAG, "ResponseHeaderName:"+responseHeaders.name(i)+" ResponseHeaderValue:"+responseHeaders.value(i));
            }
            String responseStr = Objects.requireNonNull(response.body()).string();
            Log.i(TAG, "synchronousGet: "+responseStr);
            return responseStr;
        } catch (IOException e) {
            Log.w(TAG, "synchronousGet IOException");
            e.printStackTrace();
            return null;
        }
    }
}
