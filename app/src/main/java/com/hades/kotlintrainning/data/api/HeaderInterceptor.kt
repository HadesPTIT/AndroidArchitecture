package com.hades.kotlintrainning.data.api

import com.hades.kotlintrainning.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * For all of request using api_key, set header to it
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("api_key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}