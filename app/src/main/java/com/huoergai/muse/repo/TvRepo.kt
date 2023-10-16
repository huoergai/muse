package com.huoergai.muse.repo

import com.huoergai.muse.model.TvListType
import com.huoergai.muse.model.network.TvResponse
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.service.TvService

/**
 * D&T: 2023-10-12 10:40
 * DES:
 */
class TvRepo(private val tvService: TvService) : Repository {

    suspend fun loadTvs(type: TvListType): ApiResponse<TvResponse> = tvService.getTVList(type.type)

}