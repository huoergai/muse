package com.huoergai.muse.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

/**
 * D&T: 2023-05-15 15:48
 * DES:
 */
class AuthInterceptor @Inject constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newReq = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $TMDB_TOKEN")
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(newReq)
    }
}