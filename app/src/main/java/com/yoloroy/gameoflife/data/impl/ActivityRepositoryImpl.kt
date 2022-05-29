package com.yoloroy.gameoflife.data.impl

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.data_source.ActivityLocalDataSource
import com.yoloroy.gameoflife.domain.model.data.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.DreamStatus
import com.yoloroy.gameoflife.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val localDataSource: ActivityLocalDataSource
) : ActivityRepository {

    override val currentDreamsDetails: Flow<Resource<List<DreamDetail>>>
        get() = localDataSource.currentDreamsDetails

    override val currentDreams: Flow<Resource<List<Dream>>>
        get() = localDataSource.currentDreams

    override val currentChallengesWithDreamInfo: Flow<Resource<List<ChallengeWithDreamInfo>>>
        get() = localDataSource.currentChallengesWithDreamInfo

    override fun getDreamStatus(dreamId: String): Flow<Resource<DreamStatus>> =
        localDataSource.getDreamStatus(dreamId.toInt())

    override fun completeChallenge(challengeId: String): Flow<Resource<Boolean>> =
        localDataSource.completeChallenge(challengeId.toInt())

    override fun subscribeOnDream(dreamId: String): Flow<Resource<Boolean>> =
        localDataSource.subscribeOnDream(dreamId.toInt())

    override fun unsubscribeFromDream(dreamId: String): Flow<Resource<Boolean>> =
        localDataSource.unsubscribeFromDream(dreamId.toInt())
}