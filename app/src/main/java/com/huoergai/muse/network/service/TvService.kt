package com.huoergai.muse.network.service

import com.huoergai.muse.network.dola.ApiResponse
import retrofit2.http.GET

/**
 * D&T: 2023-07-21 17:45
 * DES:
 */
interface TvService {
    @GET("/")
    suspend fun getTVList(): ApiResponse<List<String>>
}