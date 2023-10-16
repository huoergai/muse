package com.huoergai.muse.network.dola

import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * D&T: 2023-10-14 09:56
 * DES:
 */
sealed interface ApiResponse<out T> {
    data class Success<T>(val response: Response<T>) : ApiResponse<T> {
        val statusCode: Int = response.code()
        val headers: Headers = response.headers()
        val raw: okhttp3.Response = response.raw()
        val data: T by lazy { response.body() ?: throw EmptyBodyException(statusCode) }

        override fun toString(): String = "[ApiResponse.Success](data=$data)"
    }

    sealed interface Failure<T> : ApiResponse<T> {
        data class Error<T>(val response: Response<T>) : Failure<T> {
            val statusCode: Int = response.code()
            val headers: Headers = response.headers()
            val raw: okhttp3.Response = response.raw()
            val errorBody: ResponseBody? = response.errorBody()

            override fun toString(): String {
                val error = errorBody?.string()
                return if (error.isNullOrEmpty()) {
                    "[ApiResponse.Failure.Error-$statusCode](response=$response)"
                } else {
                    error
                }
            }
        }

        data class Exception<T>(val exception: Throwable) : Failure<T> {
            val message: String? = exception.localizedMessage

            override fun toString(): String {
                return "[ApiResponse.Failure.Exception](message=$message)"
            }
        }
    }

    companion object {
        inline fun <T> from(crossinline f: () -> Response<T>): ApiResponse<T> {
            // TODO add global operation
            return try {
                val response = f()
                val statusCode = response.code()
                if (statusCode in 200..299) {
                    Success(response)
                } else {
                    Failure.Error(response)
                }
            } catch (e: Exception) {
                Failure.Exception(e)
            }
        }

        fun <T> from(exception: Throwable): Failure.Exception<T> {
            // TODO add global operation
            return Failure.Exception(exception)
        }
    }
}