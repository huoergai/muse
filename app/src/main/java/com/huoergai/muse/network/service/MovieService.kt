package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.MovieListResponse
import com.huoergai.muse.network.ApiResponse
import com.huoergai.muse.network.TMDB_V3
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
    @GET("$TMDB_V3/movie/{list_type}")
    suspend fun movieList(
        @Path("list_type") listType: String,
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "zh-CN"
    ): ApiResponse<MovieListResponse>
}