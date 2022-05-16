package com.yoloroy.gameoflife.domain.repository

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import kotlinx.coroutines.flow.Flow

interface DreamsRepository {

    fun getDreamsByTags(tags: List<String>): Flow<Resource<Dream>>

    fun getDreamDetail(dreamId: String): Flow<Resource<DreamDetail>>
}
