package com.huoergai.muse.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huoergai.muse.model.MovieListType
import com.huoergai.muse.model.TvListType
import com.huoergai.muse.model.entity.Configuration
import com.huoergai.muse.model.entity.Movie
import com.huoergai.muse.model.entity.Tv
import com.huoergai.muse.network.dola.onSuccess
import com.huoergai.muse.repo.ConfigRepo
import com.huoergai.muse.repo.MovieRepo
import com.huoergai.muse.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * D&T: 2023-10-12 07:59
 * DES:
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val configRepo: ConfigRepo,
    private val movieRepo: MovieRepo,
    private val tvRepo: TvRepo
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _config = MutableStateFlow(Configuration.defaultConfig)
    val config: StateFlow<Configuration> = _config

    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    private val _tvList = MutableStateFlow<List<Tv>>(emptyList())
    val tvList: StateFlow<List<Tv>> = _tvList

    fun loadConfig() {
        viewModelScope.launch {
            _isLoading.value = true
            configRepo.loadConfig().onSuccess {
                Timber.tag("MainViewModel").d("config ${data.images.base_url}")
                _config.value = data
            }
            _isLoading.value = false
        }
    }

    fun loadMovies() {
        viewModelScope.launch {
            _isLoading.value = true

            movieRepo.loadMovies(MovieListType.NowPlaying).onSuccess {
                val movies = data.results
                Timber.tag("MainViewModel").d("movies ${movies.size}")
                _movieList.value = movies
            }

            _isLoading.value = false
        }
    }

    fun loadTvs() {
        viewModelScope.launch {
            _isLoading.value = true
            tvRepo.loadTvs(TvListType.AIRING_TODAY).onSuccess {
                val movies = data.results
                Timber.tag("MainViewModel").d("TVs ${movies.size}")
                _tvList.value = movies
            }

            _isLoading.value = false
        }
    }

}