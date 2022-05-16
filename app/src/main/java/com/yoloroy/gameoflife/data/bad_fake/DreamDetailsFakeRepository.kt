package com.yoloroy.gameoflife.data.bad_fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.bad_repository.DreamDetailsRepository
import com.yoloroy.gameoflife.domain.model.data.Challenge
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.Skill
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

object DreamDetailsFakeRepository : DreamDetailsRepository {
    private const val FAKE_DREAMS_SIZE = 10
    private const val FAKE_DREAMS_CHALLENGES_COUNT = 12
    private val FAKE_DREAMS = List(FAKE_DREAMS_SIZE) { i ->
        DreamDetail(
            i.toString(),
            "fake_dream$i",
            "...",
            List(i + 2, Int::toString),
            List(FAKE_DREAMS_CHALLENGES_COUNT) { challengeI ->
                Challenge(
                    "${i * FAKE_DREAMS_CHALLENGES_COUNT + challengeI}",
                    "challenge #$challengeI",
                    "...",
                    challengeI,
                    listOf(Skill("NoSkill", -1))
                )
            },
        )
    }.associateBy { it.id }

    private var isSubscribedOnDream = MutableStateFlow(Random.nextBoolean())

    override fun getDreamById(id: String): Flow<Resource<DreamDetail>> = flow {
        emit(Resource.Loading())
        delay(0.5.seconds)

        val result = FAKE_DREAMS[id]
            ?.let { Resource.Success(it) }
            ?:run { Resource.Error(NoSuchElementException()) }
        emit(result)
    }

    override fun getIsSubscribedOnDream(dreamId: String): Flow<Boolean> = isSubscribedOnDream

    override fun removeDream(dreamId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        isSubscribedOnDream.emit(false)
        emit(Resource.Success(Unit))
    }

    override fun addDream(dreamId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        isSubscribedOnDream.emit(true)
        emit(Resource.Success(Unit))
    }
}
