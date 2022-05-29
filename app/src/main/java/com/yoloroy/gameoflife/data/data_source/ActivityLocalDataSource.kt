package com.yoloroy.gameoflife.data.data_source

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.DreamStatus
import kotlinx.coroutines.flow.Flow

interface ActivityLocalDataSource {

    val currentDreamsDetails: Flow<Resource<List<DreamDetail>>>

    val currentDreams: Flow<Resource<List<Dream>>>

    val currentChallengesWithDreamInfo: Flow<Resource<List<ChallengeWithDreamInfo>>>

    fun getDreamStatus(dreamId: Int): Flow<Resource<DreamStatus>>

    fun completeChallenge(challengeId: Int): Flow<Resource<Boolean>>

    fun subscribeOnDream(dreamId: Int): Flow<Resource<Boolean>>

    fun unsubscribeFromDream(dreamId: Int): Flow<Resource<Boolean>>
}
