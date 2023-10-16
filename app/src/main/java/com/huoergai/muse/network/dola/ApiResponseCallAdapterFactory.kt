package com.huoergai.muse.network.dola

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * D&T: 2023-10-14 18:03
 * DES:
 */
class ApiResponseCallAdapterFactory(
    private val coroutineScope: CoroutineScope
) : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)
        when (rawType) {
            Call::class.java -> {
                if (returnType !is ParameterizedType) {
                    throw IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>")
                }

                val callType = getParameterUpperBound(0, returnType)
                val responseType = getRawType(callType)
                if (responseType != ApiResponse::class.java) {
                    return null
                }

                val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                return ApiResponseCallAdapter(resultType, coroutineScope)
            }

            //  Deferred::class.java -> {}
            else -> return null
        }
    }

    companion object {
        fun create(coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)): ApiResponseCallAdapterFactory {
            return ApiResponseCallAdapterFactory(coroutineScope)
        }
    }

}