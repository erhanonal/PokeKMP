package com.erhanonal.pokekmp.common.model

sealed interface BaseResult<out T : Any, out E : Any> {
    data class Error<E : Any>(val error: E) : BaseResult<Nothing, E>

    data class Success<T : Any>(val data: T) : BaseResult<T, Nothing>
}