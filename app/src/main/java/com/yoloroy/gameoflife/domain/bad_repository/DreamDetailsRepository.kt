package com.yoloroy.gameoflife.domain.bad_repository

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import kotlinx.coroutines.flow.Flow

interface DreamDetailsRepository {

    fun getDreamById(id: String): Flow<Resource<DreamDetail>>

    fun getIsSubscribedOnDream(dreamId: String): Flow<Boolean>

    fun removeDream(dreamId: String): Flow<Resource<Unit>>

    fun addDream(dreamId: String): Flow<Resource<Unit>>
}