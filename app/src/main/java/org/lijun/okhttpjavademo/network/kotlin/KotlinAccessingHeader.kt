package org.lijun.okhttpjavademo.network.kotlin

import okhttp3.Request
import okio.IOException
import org.lijun.okhttpjavademo.network.until.NetworkCallback

class KotlinAccessingHeader {
    val myOkHttpClient = MyOkHttpClient()
    fun accessingHeaderGet(networkCallback : NetworkCallback<String>){
        val request = Request.Builder()
            .url("https://api.github.com/repos/square/okhttp/issues")
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json; q=0.5")
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()

        myOkHttpClient.okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println("Server: ${response.header("Server")}")
            println("Date: ${response.header("Date")}")
            println("Vary: ${response.headers("Vary")}")
        }

    }
}