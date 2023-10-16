package com.huoergai.muse.network.dola

import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * D&T: 2023-10-15 16:39
 * DES:
 */
class ApiResponseCallAdapter(
    private val responseType: Type,
    private val coroutineScope: CoroutineScope
) :
    CallAdapter<Type, Call<ApiResponse<Type>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<Type>): Call<ApiResponse<Type>> {
        return ApiResponseCallDelegate(call, coroutineScope)
    }

}