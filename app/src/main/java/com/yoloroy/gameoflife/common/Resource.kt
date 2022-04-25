package com.yoloroy.gameoflife.common

sealed class Resource<T, E>(val data: T? = null, val error: E? = null) {
    class Success<T, E>(data: T) : Resource<T, E>(data)
    class Error<T, E>(error: E, data: T? = null) : Resource<T, E>(data, error)
    class Loading<T, E>(data: T? = null) : Resource<T, E>(data)
}

fun <T, R, E> Resource<T, E>.transform(transformFun: (T) -> R) = when (this) {
    is Resource.Success -> Resource.Success(transformFun(data!!))
    is Resource.Error -> Resource.Error(error, data?.let { transformFun(it) })
    is Resource.Loading -> Resource.Loading(data?.let { transformFun(it) })
}
