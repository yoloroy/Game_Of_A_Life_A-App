package com.yoloroy.gameoflife.domain.repository

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.DreamStatus
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    val currentDreamsDetails: Flow<Resource<List<DreamDetail>>>

    val currentDreams: Flow<Resource<List<Dream>>>

    val currentChallengesWithDreamInfo: Flow<Resource<List<ChallengeWithDreamInfo>>>

    fun getDreamStatus(dreamId: String): Flow<Resource<DreamStatus>>

    fun completeChallenge(challengeId: String): Flow<Resource<Boolean>>

    fun subscribeOnDream(dreamId: String): Flow<Resource<Boolean>>

    fun unsubscribeFromDream(dreamId: String): Flow<Resource<Boolean>>
}
