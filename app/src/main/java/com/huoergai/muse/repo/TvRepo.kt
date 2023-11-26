package com.huoergai.muse.repo

import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.dola.suspendOnSuccess
import com.huoergai.muse.network.model.network.ReviewResponse
import com.huoergai.muse.network.model.network.TvKeywordsResponse
import com.huoergai.muse.network.model.network.VideosResponse
import com.huoergai.muse.network.service.TvService
import com.huoergai.muse.persistence.TvDao
import com.huoergai.muse.persistence.entity.Tv
import kotlinx.coroutines.flow.flow

/**
 * D&T: 2023-10-12 10:40
 * DES:
 */
class TvRepo(private val tvService: TvService, private val tvDao: TvDao) : Repository {

    suspend fun loadTvs(page: Int) = flow<List<Tv>> {
        // tvService.getTVList(type.type, page)
        var tvs = tvDao.getTvs(page)
        if (tvs.isEmpty()) {
            tvService.getTVList(page).suspendOnSuccess {
                tvs = data.results
                tvs.forEach {
                    it.page = page
                }
                tvDao.insertAll(tvs)
            }
        }

        emit(tvs)
    }

    suspend fun loadVideos(tvID: Int): ApiResponse<VideosResponse> = tvService.getVideos(tvID)
    suspend fun loadKeywords(tvID: Int): ApiResponse<TvKeywordsResponse> =
        tvService.getKeywords(tvID)

    suspend fun loadReviews(tvID: Int): ApiResponse<ReviewResponse> = tvService.getReView(tvID)

}