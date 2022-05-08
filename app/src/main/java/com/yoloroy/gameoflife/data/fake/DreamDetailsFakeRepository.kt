package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.Challenge
import com.yoloroy.gameoflife.domain.model.DreamDetail
import com.yoloroy.gameoflife.domain.model.Skill
import com.yoloroy.gameoflife.domain.repository.DreamDetailsRepository
import kotlinx.coroutines.flow.Flow

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
    }

    override fun getDreamById(id: String): Flow<Resource<DreamDetail>> {
        TODO("Not yet implemented")
    }

    override fun getIsSubscribedOnDream(dreamId: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun removeDream(dreamId: String): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun addDream(dreamId: String): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }
}