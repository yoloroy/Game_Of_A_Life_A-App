package com.yoloroy.gameoflife.common

sealed class Resource<T>(val data: T? = null, val error: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data) {
        override fun toString() = "Success " + super.toString()
    }
    class Error<T>(error: Throwable, data: T? = null) : Resource<T>(data, error) {
        override fun toString() = "Error " + super.toString()
    }
    class Loading<T>(data: T? = null) : Resource<T>(data) {
        override fun toString() = "Loading " + super.toString()
    }

    override fun toString() = "Resource { data=$data error=$error }"
}

fun <T, R> Resource<T>.transform(transformFun: (T) -> R) = when (this) {
    is Resource.Success -> Resource.Success(transformFun(data!!))
    is Resource.Error -> Resource.Error(error!!, data?.let { transformFun(it) })
    is Resource.Loading -> Resource.Loading(data?.let { transformFun(it) })
}

fun <T, R> Resource<List<T>>.transformMap(transformFun: (T) -> R) = transform { it.map(transformFun) }
