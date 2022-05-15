package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.domain.bad_repository.DreamsRepository
import com.yoloroy.gameoflife.domain.model.Challenge
import com.yoloroy.gameoflife.domain.model.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.Dream
import com.yoloroy.gameoflife.domain.model.DreamDetail

object DreamsRepositoryFakeImpl : DreamsRepository {
    private val dreams = mutableListOf(
        DreamDetail(
            id = "-1",
            name = "Fake dream1",
            description = "asdasdasdasd asdsa dsa asdsdadsa asd asd.a adsa das. d asd asd as.das das",
            tags = listOf("fake", "preview"),
            challenges = listOf(
                Challenge("-1", "Fake challenge1", "asjnasjkcnsdbvsdn", 1),
                Challenge("2-", "Fake challenge2", " snakjd naskjd asksa ", 2),
                Challenge("-3-", "Fake challenge3", "asd kas djasndjnasm", 3),
                Challenge("4", "Fake challenge4", "asjnasadlasd asidj asdsa njkcnsdbvsdn", 4),
            )
        )
    )
    private val lastCompletedChallengesNos = mutableMapOf("-1" to 2)

    override suspend fun getDreams() = dreams.map(::Dream)

    override suspend fun getCurrentChallenges() = dreams
        .map { dream -> dream.challenges.find { it.no == lastCompletedChallengesNos[dream.id] } to dream }
        .mapNotNull { (challenge, dream) -> challenge?.let { it to dream } }
        .map { (challenge, dream) -> ChallengeWithDreamInfo(
            challenge.id,
            challenge.name,
            challenge.description,
            challenge.no,
            challenge.skills,
            dreamId = dream.id,
            dreamName = dream.name
        ) }

    override fun completeChallenge(dreamId: String) {
        lastCompletedChallengesNos[dreamId] = lastCompletedChallengesNos[dreamId]!! + 1
    }
}
