package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.TvResponse
import com.huoergai.muse.network.dola.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * D&T: 2023-07-21 17:45
 * DES:
 */
interface TvService {
    @GET("tv/{tv_type}")
    suspend fun getTVList(
        @Path("tv_type") tvType: String,
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "zh-CN"
    ): ApiResponse<TvResponse>
}