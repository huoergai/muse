package com.huoergai.muse.ui.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huoergai.muse.network.dola.onSuccess
import com.huoergai.muse.network.model.network.Keyword
import com.huoergai.muse.network.model.network.Review
import com.huoergai.muse.network.model.network.Video
import com.huoergai.muse.persistence.entity.Movie
import com.huoergai.muse.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * D&T: 2023-10-24 16:36
 * DES:
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val movieRepo: MovieRepo
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<Movie?>(null)
    val movieDetail: StateFlow<Movie?> = _movieDetail

    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    private val _keywords = MutableStateFlow<List<Keyword>>(emptyList())
    val keywords: StateFlow<List<Keyword>> = _keywords

    fun loadData(movieID: Int) {
        viewModelScope.launch {
            val defer = listOf(
                async {
                    movieRepo.loadVideos(movieID).onSuccess {
                        _videos.value = this.data.results
                    }
                },
                async {
                    movieRepo.loadKeywords(movieID).onSuccess {
                        _keywords.value = this.data.keywords
                    }
                },
                async {
                    movieRepo.loadReviews(movieID).onSuccess {
                        _reviews.value = this.data.results
                    }
                }
            )

            defer.awaitAll()
        }
    }


}