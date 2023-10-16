package com.huoergai.muse.repo

import androidx.annotation.WorkerThread
import com.huoergai.muse.model.MovieListType
import com.huoergai.muse.model.network.MoviesResponse
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

}

