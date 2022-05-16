package com.yoloroy.gameoflife.domain.bad_repository

import com.yoloroy.gameoflife.domain.model.data.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.data.Dream

interface DreamsRepository {

    suspend fun getDreams(): List<Dream>

    suspend fun getCurrentChallenges(): List<ChallengeWithDreamInfo>

    fun completeChallenge(dreamId: String)
}
