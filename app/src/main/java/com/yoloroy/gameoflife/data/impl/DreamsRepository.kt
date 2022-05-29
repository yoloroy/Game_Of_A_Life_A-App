package com.yoloroy.gameoflife.data.impl

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.common.transform
import com.yoloroy.gameoflife.common.transformMap
import com.yoloroy.gameoflife.data.data_source.DreamsLocalDataSource
import com.yoloroy.gameoflife.data.remote.DreamsRemoteSource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.repository.DreamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DreamsRepositoryImpl @Inject constructor(
    private val dreamsRemoteSource: DreamsRemoteSource,
    private val dreamsLocalDataSource: DreamsLocalDataSource
) : DreamsRepository {

    override fun getDreamsByTags(tags: List<String>): Flow<Resource<List<Dream>>> = flow {
        emit(Resource.Loading())

        val resource = dreamsRemoteSource.getDreamsByTags(tags)
            .transformMap { it.toModel() }
        emit(resource)
    }

    override fun getDreamDetail(dreamId: String): Flow<Resource<DreamDetail>> = flow {
        emit(Resource.Loading())

        val local = dreamsLocalDataSource.getDreamDetail(dreamId)
            .takeIf { it is Resource.Success }
            ?.also { emit(it) }

        val remote = dreamsRemoteSource.getDreamDetail(dreamId)
            .transform { it.toModel() }

        when {
            remote is Resource.Success -> {
                dreamsLocalDataSource.addDreamDetail(remote.data)
                emit(remote)
            }
            remote is Resource.Error && local is Resource.Success -> {
                emit(Resource.Error(data = local.data, error = remote.error))
            }
            else -> {
                emit(remote)
            }
        }
    }
}
