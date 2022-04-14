package com.ari.domain.model

sealed class ResultDomain<T> {
    class Success<T>(val result: T): ResultDomain<T>()
    class Error<T>(val error: String): ResultDomain<T>()
}