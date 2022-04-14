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

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _currentPage = MutableLiveData<Int>(1)
    val currentPage: LiveData<Int> get() = _currentPage

    private val _movies = MutableLiveData<List<MovieUi>>()
    val movies: LiveData<List<MovieUi>> get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getPopularMovies()
    }

    fun getPopularMovies() = viewModelScope.launch {
        _isLoading.postValue(true)
        when(val result = getPopularMoviesUseCase(currentPage.value!!)) {
            is ResultDomain.Success -> {
                _movies.postValue(result.result.results.map { it.toUi() })
            }
            is ResultDomain.Error -> _error.postValue(result.error)
        }
        _isLoading.postValue(false)
    }

    fun getPlayingNowMovies() = viewModelScope.launch {
        _isLoading.postValue(true)
        when(val result = getPlayingNowMoviesUseCase(currentPage.value!!)) {
            is ResultDomain.Success -> {
                _movies.postValue(result.result.results.map { it.toUi() })
            }
            is ResultDomain.Error -> _error.postValue(result.error)
        }
        _isLoading.postValue(false)
    }

}