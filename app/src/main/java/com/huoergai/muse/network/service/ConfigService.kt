package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.ConfigDetailResponse
import com.huoergai.muse.network.ApiResponse
import com.huoergai.muse.network.TMDB_V3
import retrofit2.http.GET

/**
 * D&T: 2023-07-07 18:48
 * DES:
 */
interface ConfigService {
    @GET("$TMDB_V3/configuration")
    suspend fun configDetail(): ApiResponse<ConfigDetailResponse>

}