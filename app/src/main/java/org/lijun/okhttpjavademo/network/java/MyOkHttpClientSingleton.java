package org.lijun.okhttpjavademo.network.java;

import okhttp3.OkHttpClient;

public class MyOkHttpClientSingleton {
    OkHttpClient okHttpClient = new OkHttpClient();
    private static class MyOkHttpClientSingletonHolder{
        private static MyOkHttpClientSingleton instance = new MyOkHttpClientSingleton();
    }
    private MyOkHttpClientSingleton(){
        if(MyOkHttpClientSingletonHolder.instance!=null)
            throw new RuntimeException("OkHttpClient already exists, conn not created.");
    }
     public static MyOkHttpClientSingleton getInstance(){
        return MyOkHttpClientSingletonHolder.instance;
     }
}
