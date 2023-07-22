package org.lijun.okhttpjavademo.network.kotlin

import okhttp3.Request
import okio.IOException
class KotlinSynchronousGet {
    private val myOkHttpClient: MyOkHttpClient = MyOkHttpClient()
    fun synchronousGet(): String {
        val request = Request.Builder()
            .url("https://www.baidu.com")
            .build()
        myOkHttpClient.okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("UnException Code $response")
            for ((name, value) in response.headers) {
                println("Headers:$name,$value")
            }
            val kotlinSynchronousGetStr = response.body!!.string()
            println(kotlinSynchronousGetStr)
            return kotlinSynchronousGetStr
        }
    }
}