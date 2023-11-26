package com.huoergai.muse.network.service

import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.model.network.MovieKeywordsResponse
import com.huoergai.muse.network.model.network.MoviesResponse
import com.huoergai.muse.network.model.network.ReviewResponse
import com.huoergai.muse.network.model.network.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * D&T: 2023-07-19 17:40
 * DES:
 */
interface MovieService {

    /**
     * list_type: now_playing/popular/top_rated/upcoming
     *  en-US zh-CN
     */
    @GET("movie/popular")
    suspend fun loadMovies(
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US"
    ): ApiResponse<MoviesResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun fetchVideos(@Path("movie_id") movieID: Int): ApiResponse<VideosResponse>

    @GET("movie/{movie_id}/keywords")
    suspend fun loadKeywords(@Path("movie_id") movieID: Int): ApiResponse<MovieKeywordsResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchReviews(@Path("movie_id") movieID: Int): ApiResponse<ReviewResponse>

}