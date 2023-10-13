package com.huoergai.muse.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huoergai.muse.model.MovieListType
import com.huoergai.muse.model.entity.Configuration
import com.huoergai.muse.model.entity.Movie
import com.huoergai.muse.repo.ConfigRepo
import com.huoergai.muse.repo.MovieRepo
import com.huoergai.muse.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            val defer = listOf(
                async { _config.value = configRepo.loadConfig() },
                async { _movieList.value = movieRepo.loadMovies(MovieListType.NowPlaying) }
            )
            defer.awaitAll()
            _isLoading.value = false
        }


    }

}