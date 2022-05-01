package com.yoloroy.gameoflife.common

sealed class Resource<T>(val data: T? = null, val error: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: Throwable, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

fun <T, R> Resource<T>.transform(transformFun: (T) -> R) = when (this) {
    is Resource.Success -> Resource.Success(transformFun(data!!))
    is Resource.Error -> Resource.Error(error!!, data?.let { transformFun(it) })
    is Resource.Loading -> Resource.Loading(data?.let { transformFun(it) })
}
