package com.yoloroy.gameoflife.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class Resource<T>(open val data: T? = null, open val error: Throwable? = null) {
    class Success<T>(override val data: T) : Resource<T>(data) {
        override fun toString() = "Success " + super.toString()
    }
    class Error<T>(override val error: Throwable, data: T? = null) : Resource<T>(data, error) {
        override fun toString() = "Error " + super.toString()
    }
    class Loading<T>(data: T? = null) : Resource<T>(data) {
        override fun toString() = "Loading " + super.toString()
    }

    override fun toString() = "Resource { data=$data error=$error }"
}

inline fun <T, R> Resource<T>.transform(transformFun: (T) -> R) = when (this) {
    is Resource.Success -> Resource.Success(transformFun(data))
    is Resource.Error -> Resource.Error(error, data?.let { transformFun(it) })
    is Resource.Loading -> Resource.Loading(data?.let { transformFun(it) })
}

inline fun <T, R> Resource<List<T>>.transformMap(transformFun: (T) -> R) = transform { it.map(transformFun) }

fun <T> Flow<T>.mapToResourceSuccess(): Flow<Resource<T>> = map { Resource.Success(it) }

inline fun <T, R> Flow<Resource<T>>.mapResource(crossinline transformFun: (T) -> R) = map { res -> res.transform { transformFun(it) } }

inline fun <T, R> Flow<Resource<List<T>>>.mapListResource(crossinline transformFun: (T) -> R) = mapResource { it.map(transformFun) }
