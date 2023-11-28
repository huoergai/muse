package com.huoergai.muse.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huoergai.muse.network.dola.onSuccess
import com.huoergai.muse.persistence.entity.Movie
import com.huoergai.muse.persistence.entity.Person
import com.huoergai.muse.persistence.entity.Tv
import com.huoergai.muse.repo.MovieRepo
import com.huoergai.muse.repo.PeopleRepo
import com.huoergai.muse.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val movieRepo: MovieRepo,
    private val tvRepo: TvRepo,
    private val peopleRepo: PeopleRepo
) : ViewModel() {

    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    private val _tvList = MutableStateFlow<List<Tv>>(emptyList())
    val tvList: StateFlow<List<Tv>> = _tvList

    private val _people = MutableStateFlow<List<Person>>(emptyList())
    val persons: StateFlow<List<Person>> = _people

    fun initData() {
        loadMovies()
        loadTvs()
        loadPeople()
    }

    fun loadMovies() {
        viewModelScope.launch {
            movieRepo.loadMovies(1).onSuccess {
                val movies = this.data.results
                // Timber.tag("MainViewModel").d("movies ${movies.size}")
                _movieList.value = movies
            }
        }
    }

    fun loadTvs() {
        viewModelScope.launch {
            tvRepo.loadTvs(1).onSuccess {
                val movies = this.data.results
                // Timber.tag("MainViewModel").d("TVs ${movies.size}")
                _tvList.value = movies
            }
        }
    }

    fun loadPeople() {
        viewModelScope.launch {
            peopleRepo.loadPeople(1).onSuccess {
                val people = this.data.results
                // Timber.tag("MainViewModel").d("People ${people.size}")
                _people.value = people
            }
        }
    }
}