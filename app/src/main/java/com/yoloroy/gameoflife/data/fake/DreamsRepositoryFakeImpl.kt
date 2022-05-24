package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.repository.DreamsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object DreamsRepositoryFakeImpl : DreamsRepository {

    override fun getDreamsByTags(tags: List<String>): Flow<Resource<List<Dream>>> = flow {
        emit(Resource.Loading())
        delay(2.seconds)
        val fixedTags = tags.map { it.lowercase() }
        emit(Resource.Success(
            FakeSource.dreams
                .filter { dream -> dream.tags.any { it.lowercase() in fixedTags } }
                .sortedBy { dream -> dream.tags.intersect(fixedTags.toSet()).size }
                .map { Dream(it) }
        ))
    }

    override fun getDreamDetail(dreamId: String): Flow<Resource<DreamDetail>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(FakeSource.getDreamDetailById(dreamId)))
    }
}
