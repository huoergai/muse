package com.huoergai.muse.repo

import androidx.annotation.WorkerThread
import com.huoergai.muse.model.MovieListType
import com.huoergai.muse.model.entity.Movie
import com.huoergai.muse.network.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * D&T: 2023-10-12 10:39
 * DES:
 */
class MovieRepo(
    private val movieService: MovieService,
    private val dispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    suspend fun loadMovies(movieListType: MovieListType): List<Movie> = withContext(dispatcher) {
        val resp = movieService.loadMovies(movieListType.type)
        resp.results
    }

}