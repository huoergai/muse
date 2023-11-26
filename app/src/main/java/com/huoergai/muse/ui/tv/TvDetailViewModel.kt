package com.huoergai.muse.ui.tv

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huoergai.muse.network.dola.onSuccess
import com.huoergai.muse.network.model.network.Keyword
import com.huoergai.muse.network.model.network.Review
import com.huoergai.muse.network.model.network.Video
import com.huoergai.muse.persistence.entity.Tv
import com.huoergai.muse.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * D&T: 2023-10-27 10:09
 * DES:
 */
@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val tvRepo: TvRepo
) : ViewModel() {
    private val _tvDetail = MutableStateFlow<Tv?>(null)
    val tvDetail: StateFlow<Tv?> = _tvDetail

    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos

    private val _keywords = MutableStateFlow<List<Keyword>>(emptyList())
    val keywords: StateFlow<List<Keyword>> = _keywords

    private val _tvReviews = MutableStateFlow<List<Review>>(emptyList())
    val tvReview: StateFlow<List<Review>> = _tvReviews

    fun loadData(tvID: Int) {
        viewModelScope.launch {
            val defer = listOf(
                async {
                    tvRepo.loadVideos(tvID).onSuccess {
                        _videos.value = this.data.results
                    }
                },
                async {
                    tvRepo.loadKeywords(tvID).onSuccess {
                        _keywords.value = this.data.results
                    }
                },
                async {
                    tvRepo.loadReviews(tvID).onSuccess {
                        _tvReviews.value = this.data.results
                    }
                }
            )

            defer.awaitAll()
        }
    }

}