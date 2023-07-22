package org.lijun.okhttpjavademo.network.until

import java.io.IOException

interface NetworkCallback<T> {
    fun successful(t: T)
    fun fail(e: IOException)
}