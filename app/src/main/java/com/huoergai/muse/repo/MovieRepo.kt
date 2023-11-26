package com.huoergai.muse.repo

import androidx.annotation.WorkerThread
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.model.network.MovieKeywordsResponse
import com.huoergai.muse.network.model.network.MoviesResponse
import com.huoergai.muse.network.model.network.ReviewResponse
import com.huoergai.muse.network.model.network.VideosResponse
import com.huoergai.muse.network.service.MovieService
import com.huoergai.muse.persistence.MovieDao

/**
 * D&T: 2023-10-12 10:39
 * DES:
 */
class MovieRepo(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) : Repository {

    @WorkerThread
    suspend fun loadMovies(page: Int): ApiResponse<MoviesResponse> {
        return movieService.loadMovies(page)
    }

    @WorkerThread
    suspend fun loadVideos(movieID: Int): ApiResponse<VideosResponse> {
        return movieService.fetchVideos(movieID)
    }

    @WorkerThread
    suspend fun loadKeywords(movieID: Int): ApiResponse<MovieKeywordsResponse> {
        return movieService.loadKeywords(movieID)
    }

    @WorkerThread
    suspend fun loadReviews(movieID: Int): ApiResponse<ReviewResponse> {
        return movieService.fetchReviews(movieID)
    }

}

