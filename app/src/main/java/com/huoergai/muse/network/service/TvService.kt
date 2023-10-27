package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.ReviewResponse
import com.huoergai.muse.model.network.TvDetail
import com.huoergai.muse.model.network.TvKeywordsResponse
import com.huoergai.muse.model.network.TvResponse
import com.huoergai.muse.model.network.VideosResponse
import com.huoergai.muse.network.dola.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * D&T: 2023-07-21 17:45
 * DES:
 */
interface TvService {

    @GET("tv/{tv_type}")
    suspend fun getTVList(
        @Path("tv_type") tvType: String,
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US"
    ): ApiResponse<TvResponse>

    @GET("tv/{series_id}")
    suspend fun getTvDetail(@Path("series_id") tvID: Int): ApiResponse<TvDetail>

    @GET("tv/{series_id}/videos")
    suspend fun getVideos(@Path("series_id") tvID: Int): ApiResponse<VideosResponse>

    @GET("tv/{series_id}/keywords")
    suspend fun getKeywords(@Path("series_id") tvID: Int): ApiResponse<TvKeywordsResponse>

    @GET("tv/{series_id}/reviews")
    suspend fun getReView(@Path("series_id") tvID: Int): ApiResponse<ReviewResponse>

}