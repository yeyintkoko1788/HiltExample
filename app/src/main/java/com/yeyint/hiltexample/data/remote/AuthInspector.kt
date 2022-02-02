package com.yeyint.hiltexample.data.remote

import android.content.Context
import com.yeyint.hiltexample.utils.Constant
import okhttp3.Interceptor
import okhttp3.Response

class AuthInspector(context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("X-Requested-With", "XMLHttpRequest")
        requestBuilder.addHeader("Authorization", Constant.BASIC_AUTH_VALUE)

        return chain.proceed(requestBuilder.build())
    }
}