package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.MovieListResponse
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
     */
    @GET("movie/{list_type}")
    suspend fun loadMovies(
        @Path("list_type") listType: String,
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "zh-CN"
    ): MovieListResponse

}