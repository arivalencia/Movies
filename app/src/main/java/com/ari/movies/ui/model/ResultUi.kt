package com.ari.movies.ui.model

import com.ari.domain.model.ResultDomain

sealed class ResultUi<T> {
    class Success<T>(val result: T) : ResultUi<T>()
    class Error<T>(val error: String) : ResultUi<T>()
}

fun <T> ResultDomain<T>.toUi(): ResultUi<T> = when (this) {
    is ResultDomain.Success -> ResultUi.Success(this.result)
    is ResultDomain.Error -> ResultUi.Error(this.error)
}