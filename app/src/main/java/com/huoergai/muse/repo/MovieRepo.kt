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
            Timber.tag("TEST").d("loadMovies from DB")
            return ApiResponse.from { Response.success(MoviesResponse(page, movies, null)) }
        }

        Timber.tag("TEST").d("loadMovies from NET")
        val moviesResponse = movieService.loadMovies(page)
        moviesResponse.suspendOnSuccess {
            val newMovies = this.data.results
            if (newMovies.isNotEmpty()) {
                newMovies.forEach { it.page = page }
                Timber.tag("TEST").d("save newMovies to DB")
                movieDao.insertAll(newMovies)
            }
        }

        return moviesResponse
    }

    @WorkerThread
    suspend fun loadVideos(movieID: Long): ApiResponse<VideosResponse> {
        val movie = movieDao.getMovie(movieID)
        val videos = movie?.videos
        if (videos.isNullOrEmpty()) {
            Timber.tag("TEST").d("loadVideos from NET")
            val videoResponse = movieService.fetchVideos(movieID)
            movie?.let {
                videoResponse.suspendOnSuccess {
                    val newVideos = this.data.results
                    if (newVideos.isNotEmpty()) {
                        it.videos = newVideos
                        Timber.tag("TEST").d("save newVideos to DB")
                        try {
                            movieDao.update(it)
                        } catch (e: Exception) {
                            Timber.tag("TEST").e("update video failed:${it}")
                            e.printStackTrace()
                        }
                    }
                }
            }

            return videoResponse
        }

        Timber.tag("TEST").d("loadVideos from DB")
        return ApiResponse.from { Response.success(VideosResponse(movieID, videos)) }
    }

    @WorkerThread
    suspend fun loadKeywords(movieID: Long): ApiResponse<MovieKeywordsResponse> {
        val movie = movieDao.getMovie(movieID)
        val keywords = movie?.keywords
        if (keywords.isNullOrEmpty()) {
            Timber.tag("TEST").d("loadKeywords from NET")
            val keywordsResponse = movieService.fetchKeywords(movieID)
            movie?.let {
                keywordsResponse.suspendOnSuccess {
                    val newKeywords = this.data.keywords
                    if (newKeywords.isNotEmpty()) {
                        it.keywords = newKeywords
                        Timber.tag("TEST").d("save keywords to DB")
                        try {
                            movieDao.update(it)
                        } catch (e: Exception) {
                            Timber.tag("TEST").e("update keywords failed:${it}")
                            e.printStackTrace()
                        }
                    }
                }
            }

            return keywordsResponse
        }

        Timber.tag("TEST").d("loadKeywords from DB")
        return ApiResponse.from { Response.success(MovieKeywordsResponse(movieID, keywords)) }
    }

    @WorkerThread
    suspend fun loadReviews(movieID: Long): ApiResponse<ReviewResponse> {
        val movie = movieDao.getMovie(movieID)
        val reviews = movie?.reviews
        if (reviews.isNullOrEmpty()) {
            Timber.tag("TEST").d("loadReviews from NET")
            val reviewResponse = movieService.fetchReviews(movieID)
            movie?.let {
                reviewResponse.suspendOnSuccess {
                    val newReviews = this.data.results
                    if (newReviews.isNotEmpty()) {
                        it.reviews = newReviews
                        Timber.tag("TEST").d("save reviews to DB")
                        try {
                            movieDao.update(it)
                        } catch (e: Exception) {
                            Timber.tag("TEST").e("update review failed:${it}")
                            e.printStackTrace()
                        }
                    }
                }
            }
            return reviewResponse
        }

        Timber.tag("TEST").d("loadReviews from DB")
        return ApiResponse.from { Response.success(ReviewResponse(movieID, reviews, 0)) }
    }

}

