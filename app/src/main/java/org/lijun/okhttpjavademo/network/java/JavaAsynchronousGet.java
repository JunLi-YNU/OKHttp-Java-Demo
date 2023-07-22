package org.lijun.okhttpjavademo.network.java;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import org.lijun.okhttpjavademo.network.until.NetworkCallBack;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JavaAsynchronousGet {

    public void asynchronousGet(NetworkCallBack<String> networkCallBack){
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        MyOkHttpClientSingleton.getInstance().okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                networkCallBack.fail(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (ResponseBody responseBody = response.body()){
                    if(!response.isSuccessful()) throw new IOException("UnException Code"+response);
                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        Log.i(TAG, "ResponseHeaderName:"+responseHeaders.name(i)+" ResponseHeaderValue:"+responseHeaders.value(i));
                    }

                    String responseBodyStr = Objects.requireNonNull(responseBody.string());
                    Log.i(TAG, "synchronousGet: "+responseBodyStr);
                    networkCallBack.successful(responseBodyStr);
                }catch (IOException e){
                    networkCallBack.fail(e);
                }
            }
        });
    }
}
