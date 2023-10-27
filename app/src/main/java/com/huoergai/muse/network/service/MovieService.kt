package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.MovieDetail
import com.huoergai.muse.model.network.MovieKeywordsResponse
import com.huoergai.muse.model.network.MoviesResponse
import com.huoergai.muse.model.network.ReviewResponse
import com.huoergai.muse.model.network.VideosResponse
import com.huoergai.muse.network.dola.ApiResponse
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
    @GET("movie/{list_type}")
    suspend fun loadMovies(
        @Path("list_type") listType: String,
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US"
    ): ApiResponse<MoviesResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun fetchVideos(@Path("movie_id") movieID: Int): ApiResponse<VideosResponse>

    @GET("movie/{movie_id}/keywords")
    suspend fun loadKeywords(@Path("movie_id") movieID: Int): ApiResponse<MovieKeywordsResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchReviews(@Path("movie_id") movieID: Int): ApiResponse<ReviewResponse>

    @GET("movie/{movie_id}")
    suspend fun movieDetails(@Path("movie_id") movieID: Int): ApiResponse<MovieDetail>

}