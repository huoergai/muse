package com.huoergai.muse.repo

import com.huoergai.muse.model.entity.Tv
import com.huoergai.muse.network.service.TvService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * D&T: 2023-10-12 10:40
 * DES:
 */
class TvRepo(
    private val tvService: TvService,
    private val dispatcher: CoroutineDispatcher
) : Repository {

    suspend fun loadTvs(): List<Tv> {
        val tvs: List<Tv> = emptyList()
        withContext(dispatcher) {
            tvService.getTVList()
        }
        return tvs
    }

}