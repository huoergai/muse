package com.huoergai.muse.network.service

import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.model.network.ReviewResponse
import com.huoergai.muse.network.model.network.TvKeywordsResponse
import com.huoergai.muse.network.model.network.TvResponse
import com.huoergai.muse.network.model.network.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * D&T: 2023-07-21 17:45
 * DES:
 */
interface TvService {

    @GET("tv/popular")
    suspend fun getTVList(
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US"
    ): ApiResponse<TvResponse>

    @GET("tv/{series_id}/videos")
    suspend fun getVideos(@Path("series_id") tvID: Long): ApiResponse<VideosResponse>

    @GET("tv/{series_id}/keywords")
    suspend fun getKeywords(@Path("series_id") tvID: Long): ApiResponse<TvKeywordsResponse>

    @GET("tv/{series_id}/reviews")
    suspend fun getReView(@Path("series_id") tvID: Long): ApiResponse<ReviewResponse>

}