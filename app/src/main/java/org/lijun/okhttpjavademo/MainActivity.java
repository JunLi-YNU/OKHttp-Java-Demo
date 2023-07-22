package org.lijun.okhttpjavademo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.lijun.okhttpjavademo.databinding.ActivityMainBinding;
import org.lijun.okhttpjavademo.network.java.JavaAsynchronousGet;
import org.lijun.okhttpjavademo.network.java.JavaSynchronousGet;
import org.lijun.okhttpjavademo.network.kotlin.KotlinAsynchronousGet;
import org.lijun.okhttpjavademo.network.until.NetworkCallBack;
import org.lijun.okhttpjavademo.network.until.NetworkCallback;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityMainBinding.textViewShow.setText(null);
            }
        });
        activityMainBinding.buttonKotlinSynchronousGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kotlinSynchronousGet();
            }
        });
        activityMainBinding.buttonJavaSynchronousGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                javaSynchronousGet();
            }
        });
        activityMainBinding.buttonKotlinAsynchronousGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kotlinAsynchronousGet();
            }
        });
        activityMainBinding.buttonJavaAsynchronousGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                javaAsynchronousGet();
            }
        });

    }

    private void kotlinAsynchronousGet() {
        KotlinAsynchronousGet kotlinAsynchronousGet = new KotlinAsynchronousGet();
        new Thread(new Runnable() {
            @Override
            public void run() {
                kotlinAsynchronousGet.asynchronousGet(new NetworkCallback<String>() {
                    @Override
                    public void successful(String s) {
                        updateTextView(s);
                    }

                    @Override
                    public void fail(@NonNull IOException e) {
                        updateTextView(e.toString());
                    }
                });
            }
        }).start();
    }

    private void javaAsynchronousGet() {
        JavaAsynchronousGet javaAsynchronousGet = new JavaAsynchronousGet();
        new Thread(new Runnable() {
            @Override
            public void run() {
                javaAsynchronousGet.asynchronousGet(new NetworkCallBack<String>() {
                    @Override
                    public void successful(String s) {
                        javaAsynchronousGet.asynchronousGet(new NetworkCallBack<String>() {
                            @Override
                            public void successful(String s) {
                                updateTextView(s);
                            }

                            @Override
                            public void fail(Exception e) {
                                updateTextView(e.toString());
                            }
                        });
                    }

                    @Override
                    public void fail(Exception e) {

                    }
                });
            }
        }).start();
    }

    private void kotlinSynchronousGet() {
        org.lijun.okhttpjavademo.network.kotlin.KotlinSynchronousGet kotlinSynchronousGet = new org.lijun.okhttpjavademo.network.kotlin.KotlinSynchronousGet();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String synchronousGetStr = kotlinSynchronousGet.synchronousGet();
                updateTextView(synchronousGetStr);
            }
        }).start();
    }

    private void javaSynchronousGet() {
        JavaSynchronousGet synchronousGet = new JavaSynchronousGet();
        new Thread(new Runnable(){
            @Override
            public void run() {
                String synchronousGetStr = synchronousGet.synchronousGet();
                updateTextView(synchronousGetStr);
            }
        }).start();
    }

    private void updateTextView(String synchronousGetStr) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activityMainBinding.textViewShow.setText(synchronousGetStr);
            }
        });
    }

}