package com.huoergai.muse.repo

import com.huoergai.muse.model.TvListType
import com.huoergai.muse.model.network.ReviewResponse
import com.huoergai.muse.model.network.TvDetail
import com.huoergai.muse.model.network.TvKeywordsResponse
import com.huoergai.muse.model.network.TvResponse
import com.huoergai.muse.model.network.VideosResponse
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.service.TvService

/**
 * D&T: 2023-10-12 10:40
 * DES:
 */
class TvRepo(private val tvService: TvService) : Repository {

    suspend fun loadTvs(type: TvListType): ApiResponse<TvResponse> = tvService.getTVList(type.type)

    suspend fun loadTvDetail(tvID: Int): ApiResponse<TvDetail> = tvService.getTvDetail(tvID)

    suspend fun loadVideos(tvID: Int): ApiResponse<VideosResponse> = tvService.getVideos(tvID)
    suspend fun loadKeywords(tvID: Int): ApiResponse<TvKeywordsResponse> =
        tvService.getKeywords(tvID)

    suspend fun loadReviews(tvID: Int): ApiResponse<ReviewResponse> = tvService.getReView(tvID)

}