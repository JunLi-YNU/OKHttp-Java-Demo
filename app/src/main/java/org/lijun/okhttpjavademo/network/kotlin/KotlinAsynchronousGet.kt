package org.lijun.okhttpjavademo.network.kotlin

import android.content.ContentValues.TAG
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.lijun.okhttpjavademo.network.until.NetworkCallback

class KotlinAsynchronousGet {
    fun asynchronousGet(networkCallback: NetworkCallback<String>) {
        val request = Request.Builder()
            .url("https://www.baidu.com")
            .build()
        val myOkHttpClient = MyOkHttpClient()
        myOkHttpClient.okHttpClient.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                networkCallback.fail(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use{
                    if(!response.isSuccessful) throw IOException("Unexpected code $response")
                    for ((name,value ) in response.headers){
                        Log.i(TAG, "onResponse: <$name,$value>")
                    }
                    val str = response.body!!.string()
                    Log.i(TAG, "onResponse: responseStr:$str")
                    networkCallback.successful(str)
                }
            }

        })

    }
}