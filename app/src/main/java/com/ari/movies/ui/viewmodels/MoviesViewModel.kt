package com.ari.movies.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.domain.model.ResultDomain
import com.ari.domain.usecases.GetPlayingNowMoviesUseCase
import com.ari.domain.usecases.GetPopularMoviesUseCase
import com.ari.movies.ui.model.MovieUi
import com.ari.movies.ui.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPlayingNowMoviesUseCase: GetPlayingNowMoviesUseCase
): ViewModel() {

    val FIRST_PAGE = 1
    var showingMostPopular = true

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _currentPage = MutableLiveData<Int>(FIRST_PAGE)
    val currentPage: LiveData<Int> get() = _currentPage

    private val _movies = MutableLiveData<List<MovieUi>>(emptyList())
    val movies: LiveData<List<MovieUi>> get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun onCreate() {
        if (movies.value!!.isEmpty()) {
            getPopularMovies()
        }
    }

    // Clean data on change of movies type (most popular or playing now)
    private fun resetPageNumberAndMovies() {
        _currentPage.value = FIRST_PAGE
        _movies.postValue(emptyList())
    }

    fun clearError() = _error.postValue("")

    fun changeToMostPopularMovies() {
        resetPageNumberAndMovies()
        getPopularMovies()
    }

    fun changeToPlayingNowMovies() {
        resetPageNumberAndMovies()
        getPlayingNowMovies()
    }

    fun loadNextPage() {
        if (showingMostPopular) {
            getPopularMovies()
        } else {
            getPlayingNowMovies()
        }
    }

    fun getPopularMovies() = viewModelScope.launch {
        showingMostPopular = true
        _isLoading.postValue(true)
        when(val result = getPopularMoviesUseCase(_currentPage.value!!)) {
            is ResultDomain.Success -> addMovies(result.result.results.map { it.toUi() })
            is ResultDomain.Error -> _error.postValue(result.error)
        }
        _isLoading.postValue(false)
    }

    fun getPlayingNowMovies() = viewModelScope.launch {
        showingMostPopular = false
        _isLoading.postValue(true)
        when(val result = getPlayingNowMoviesUseCase(_currentPage.value!!)) {
            is ResultDomain.Success -> addMovies(result.result.results.map { it.toUi() })
            is ResultDomain.Error -> _error.postValue(result.error)
        }
        _isLoading.postValue(false)
    }

    private fun addMovies(list: List<MovieUi>) {
        val previousList = ArrayList(movies.value!!)
        previousList.addAll(list)
        _movies.postValue(previousList)
        _currentPage.postValue(_currentPage.value!! + 1)
    }

}