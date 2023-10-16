package com.huoergai.muse.repo

import com.huoergai.muse.model.entity.Configuration
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.service.ConfigService

/**
 * D&T: 2023-10-12 21:23
 * DES:
 */
class ConfigRepo(private val configService: ConfigService) : Repository {

    suspend fun loadConfig(): ApiResponse<Configuration> = configService.loadConfiguration()

}