package com.yoloroy.gameoflife.data.local

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.common.mapListResource
import com.yoloroy.gameoflife.common.mapResource
import com.yoloroy.gameoflife.common.mapToResourceSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

fun <T, R> Flow<List<T>>.mapToListResource(transformFun: (T) -> R) = this
    .mapToResourceSuccess()
    .onStart { emit(Resource.Loading()) }
    .mapListResource(transformFun)

fun <T, R> Flow<T>.mapToResource(transformFun: (T) -> R) = this
    .mapToResourceSuccess()
    .onStart { emit(Resource.Loading()) }
    .mapResource(transformFun)
