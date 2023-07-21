package com.huoergai.muse.network

import okhttp3.Headers
import retrofit2.Response


/**
 * D&T: 2023-07-07 18:09
 * DES:
 */
sealed interface ApiResponse<out T> {
    data class Success<T>(val response: Response<T>) : ApiResponse<T> {
        val statusCode: StatusCode = getStatusCode(response)
        val headers: Headers = response.headers()
        val raw: okhttp3.Response = response.raw()
        val data: T by lazy { response.body() ?: throw IllegalStateException("") }
    }

    fun <T> getStatusCode(response: Response<T>): StatusCode {
        return StatusCode.values().find { it.code == response.code() } ?: StatusCode.Unknown
    }
}