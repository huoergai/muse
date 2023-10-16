package com.huoergai.muse.network.dola

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

/**
 * D&T: 2023-10-15 17:11
 * DES:
 */
class ApiResponseCallDelegate<T>(
    proxy: Call<T>,
    private val coroutineScope: CoroutineScope
) : RetrofitCallDelegate<T, ApiResponse<T>>(proxy) {

    override fun executeImpl(): Response<ApiResponse<T>> {
        return runBlocking(coroutineScope.coroutineContext) {
            val apiResponse = ApiResponse.from { proxy.execute() }
            Response.success(apiResponse)
        }
    }

    override fun enqueueImpl(callback: Callback<ApiResponse<T>>) {
        coroutineScope.launch {
            try {
                val response = proxy.awaitResponse()
                val apiResponse = ApiResponse.from { response }
                callback.onResponse(this@ApiResponseCallDelegate, Response.success(apiResponse))
            } catch (e: Exception) {
                callback.onResponse(
                    this@ApiResponseCallDelegate,
                    Response.success(ApiResponse.from(e))
                )
            }
        }
    }

    override fun cloneImpl(): Call<ApiResponse<T>> =
        ApiResponseCallDelegate(proxy.clone(), coroutineScope)
}