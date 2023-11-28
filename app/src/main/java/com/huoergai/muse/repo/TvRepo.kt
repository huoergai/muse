package com.huoergai.muse.repo

import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.dola.suspendOnSuccess
import com.huoergai.muse.network.model.network.ReviewResponse
import com.huoergai.muse.network.model.network.TvKeywordsResponse
import com.huoergai.muse.network.model.network.TvResponse
import com.huoergai.muse.network.model.network.VideosResponse
import com.huoergai.muse.network.service.TvService
import com.huoergai.muse.persistence.TvDao
import retrofit2.Response

/**
 * D&T: 2023-10-12 10:40
 * DES:
 */
class TvRepo(private val tvService: TvService, private val tvDao: TvDao) : Repository {

    suspend fun loadTvs(page: Int): ApiResponse<TvResponse> {
        var tvs = tvDao.getTvs(page)
        if (tvs.isEmpty()) {
            val tvsResponse = tvService.getTVList(page)
            tvsResponse.suspendOnSuccess {
                tvs = data.results
                tvs.forEach { it.page = page }
                tvDao.insertAll(tvs)
            }
            return tvsResponse
        }

        return ApiResponse.from { Response.success(TvResponse(page, tvs, 0)) }
    }

    suspend fun loadVideos(tvID: Long): ApiResponse<VideosResponse> {
        val tv = tvDao.getTv(tvID)
        val videos = tv?.videos
        if (videos.isNullOrEmpty()) {
            val videosResponse = tvService.getVideos(tvID)
            tv?.let {
                videosResponse.suspendOnSuccess {
                    it.videos = this.data.results
                    tvDao.update(it)
                }
            }
            return videosResponse
        }
        return ApiResponse.from { Response.success(VideosResponse(tvID, videos)) }
    }

    suspend fun loadKeywords(tvID: Long): ApiResponse<TvKeywordsResponse> {
        val tv = tvDao.getTv(tvID)
        val keywords = tv?.keywords
        if (keywords.isNullOrEmpty()) {
            val keywordsResponse = tvService.getKeywords(tvID)
            tv?.let {
                keywordsResponse.suspendOnSuccess {
                    it.keywords = this.data.results
                    tvDao.update(it)
                }
            }
            return keywordsResponse
        }

        return ApiResponse.from { Response.success(TvKeywordsResponse(tvID, keywords)) }
    }


    suspend fun loadReviews(tvID: Long): ApiResponse<ReviewResponse> {
        val tv = tvDao.getTv(tvID)
        val reviews = tv?.reviews
        if (reviews.isNullOrEmpty()) {
            val reviewsResponse = tvService.getReView(tvID)
            tv?.let {
                reviewsResponse.suspendOnSuccess {
                    it.reviews = this.data.results
                    tvDao.update(it)
                }
            }
            return reviewsResponse
        }
        return ApiResponse.from { Response.success(ReviewResponse(tvID, reviews, 0)) }
    }

}