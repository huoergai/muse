package com.huoergai.muse.repo

import androidx.annotation.WorkerThread
import com.huoergai.muse.model.MovieListType
import com.huoergai.muse.model.network.KeywordsResponse
import com.huoergai.muse.model.network.MovieDetail
import com.huoergai.muse.model.network.MovieVideoResponse
import com.huoergai.muse.model.network.MoviesResponse
import com.huoergai.muse.model.network.ReviewResponse
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.service.MovieService

/**
 * D&T: 2023-10-12 10:39
 * DES:
 */
class MovieRepo(private val movieService: MovieService) : Repository {

    @WorkerThread
    suspend fun loadMovies(movieListType: MovieListType): ApiResponse<MoviesResponse> {
        return movieService.loadMovies(movieListType.type)
    }

    @WorkerThread
    suspend fun loadMovieDetail(movieID: Int): ApiResponse<MovieDetail> {
        return movieService.movieDetails(movieID)
    }

    @WorkerThread
    suspend fun loadVideos(movieID: Int): ApiResponse<MovieVideoResponse> {
        return movieService.fetchVideos(movieID)
    }

    @WorkerThread
    suspend fun loadKeywords(movieID: Int): ApiResponse<KeywordsResponse> {
        return movieService.loadKeywords(movieID)
    }

    @WorkerThread
    suspend fun loadReviews(movieID: Int): ApiResponse<ReviewResponse> {
        return movieService.fetchReviews(movieID)
    }

}

