package com.huoergai.muse.repo

import androidx.annotation.WorkerThread
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.dola.suspendOnSuccess
import com.huoergai.muse.network.model.network.MovieKeywordsResponse
import com.huoergai.muse.network.model.network.MoviesResponse
import com.huoergai.muse.network.model.network.ReviewResponse
import com.huoergai.muse.network.model.network.VideosResponse
import com.huoergai.muse.network.service.MovieService
import com.huoergai.muse.persistence.MovieDao
import retrofit2.Response
import timber.log.Timber

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
        val movies = movieDao.getMovies(page)
        if (movies.isNotEmpty()) {
            Timber.tag("TEST").d("load from DB")
            return ApiResponse.from { Response.success(MoviesResponse(page, movies, null, null)) }
        }

        Timber.tag("TEST").d("load from NET")
        val moviesResponse = movieService.loadMovies(page)
        moviesResponse.suspendOnSuccess {
            val newMovies = this.data.results
            if (newMovies.isNotEmpty()) {
                newMovies.forEach { it.page = page }
                movieDao.insertAll(newMovies)
            }
        }

        return moviesResponse
    }

    @WorkerThread
    suspend fun loadVideos(movieID: Long): ApiResponse<VideosResponse> {
        val movie = movieDao.getMovie(movieID)
        val videos = movie?.videos
        if (movie == null || videos.isNullOrEmpty()) {
            val videoResponse = movieService.fetchVideos(movieID)
            videoResponse.suspendOnSuccess {
                val newVideos = this.data.results
                if (newVideos.isNotEmpty()) {
                    movie?.let {
                        it.videos = newVideos
                        movieDao.update(it)
                    }
                }
            }
            return videoResponse
        }

        return ApiResponse.from { Response.success(VideosResponse(movieID, videos)) }
    }

    @WorkerThread
    suspend fun loadKeywords(movieID: Long): ApiResponse<MovieKeywordsResponse> {
        val movie = movieDao.getMovie(movieID)
        val keywords = movie?.keywords
        if (movie == null || keywords.isNullOrEmpty()) {
            val keywordsResponse = movieService.fetchKeywords(movieID)
            keywordsResponse.suspendOnSuccess {
                val newKeywords = this.data.keywords
                if (newKeywords.isNotEmpty()) {
                    movie?.let {
                        it.keywords = newKeywords
                        movieDao.update(it)
                    }
                }
            }
            return keywordsResponse
        }

        return ApiResponse.from { Response.success(MovieKeywordsResponse(movieID, keywords)) }
    }

    @WorkerThread
    suspend fun loadReviews(movieID: Long): ApiResponse<ReviewResponse> {
        val movie = movieDao.getMovie(movieID)
        val reviews = movie?.reviews
        if (movie == null || reviews.isNullOrEmpty()) {
            val reviewResponse = movieService.fetchReviews(movieID)
            reviewResponse.suspendOnSuccess {
                val newReviews = this.data.results
                if (newReviews.isNotEmpty()) {
                    movie?.let {
                        it.reviews = newReviews
                        movieDao.update(it)
                    }
                }
            }
            return reviewResponse
        }

        return ApiResponse.from {
            Response.success(
                ReviewResponse(movieID, movie.page ?: 1, reviews, 0, 0)
            )
        }
    }

}

