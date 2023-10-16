package com.huoergai.muse.network.service

import com.huoergai.muse.model.entity.Configuration
import com.huoergai.muse.network.dola.ApiResponse
import retrofit2.http.GET

/**
 * D&T: 2023-07-07 18:48
 * DES:
 */
interface ConfigService {
    @GET("configuration")
    suspend fun loadConfiguration(): ApiResponse<Configuration>

}