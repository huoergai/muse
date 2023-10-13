package com.huoergai.muse.network

import com.huoergai.muse.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * D&T: 2023-05-15 15:48
 * DES:
 */
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newReq = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_TOKEN}")
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(newReq)
    }
}