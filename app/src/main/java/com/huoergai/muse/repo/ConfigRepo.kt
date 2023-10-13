package com.huoergai.muse.repo

import com.huoergai.muse.model.entity.Configuration
import com.huoergai.muse.network.service.ConfigService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * D&T: 2023-10-12 21:23
 * DES:
 */
class ConfigRepo(
    private val configService: ConfigService,
    private val dispatcher: CoroutineDispatcher
) : Repository {

    suspend fun loadConfig(): Configuration = withContext(dispatcher) {
        configService.loadConfiguration()
    }

}