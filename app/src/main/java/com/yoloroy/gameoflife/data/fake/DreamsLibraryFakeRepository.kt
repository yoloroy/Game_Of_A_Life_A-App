package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.Dream
import com.yoloroy.gameoflife.domain.repository.DreamsLibraryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object DreamsLibraryFakeRepository : DreamsLibraryRepository {
    private const val FAKE_DREAMS_SIZE = 10
    private val FAKE_DREAMS = List(FAKE_DREAMS_SIZE) { i ->
        Dream(
            i.toString(),
            "fake_dream$i",
            "...",
            (FAKE_DREAMS_SIZE downTo i).toList().map(Int::toString)
        )
    }

    override suspend fun getDreamsByTags(tags: Collection<String>): Flow<Resource<List<Dream>>> {
        return flow {
            emit(Resource.Loading())
            delay((0.1).seconds)
            emit(Resource.Success(
                FAKE_DREAMS.filter { it.tags.intersect(tags.toSet()).isNotEmpty() }
            ))
        }
    }
}