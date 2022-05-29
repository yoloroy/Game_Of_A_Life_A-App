package com.yoloroy.gameoflife.data.data_source

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.DreamDetail

interface DreamsLocalDataSource {

    suspend fun getDreamDetail(dreamId: String): Resource<DreamDetail>

    suspend fun addDreamDetail(dreamDetail: DreamDetail): Resource<Boolean>
}
