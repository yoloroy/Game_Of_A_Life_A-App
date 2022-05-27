package com.yoloroy.gameoflife.data.impl

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.common.transform
import com.yoloroy.gameoflife.common.transformMap
import com.yoloroy.gameoflife.data.remote.DreamsRemoteSource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.repository.DreamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DreamsRepositoryImpl @Inject constructor(
    private val dreamsRemoteSource: DreamsRemoteSource
) : DreamsRepository {

    override fun getDreamsByTags(tags: List<String>): Flow<Resource<List<Dream>>> = flow {
        emit(Resource.Loading())

        val resource = dreamsRemoteSource.getDreamsByTags(tags)
            .transformMap { it.toModel() }
        emit(resource)
    }

    override fun getDreamDetail(dreamId: String): Flow<Resource<DreamDetail>> = flow {
        emit(Resource.Loading())

        val resource = dreamsRemoteSource.getDreamDetail(dreamId)
            .transform { it.toModel() }
        emit(resource)
    }
}
