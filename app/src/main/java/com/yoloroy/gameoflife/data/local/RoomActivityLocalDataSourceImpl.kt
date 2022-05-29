package com.yoloroy.gameoflife.data.local

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.data_source.ActivityLocalDataSource
import com.yoloroy.gameoflife.data.local.dao.ActivityDao
import com.yoloroy.gameoflife.data.local.entity.ChallengeFullWithDream
import com.yoloroy.gameoflife.data.local.entity.DreamFull
import com.yoloroy.gameoflife.data.local.entity.DreamInfo
import com.yoloroy.gameoflife.data.local.entity.DreamProgress
import com.yoloroy.gameoflife.domain.model.data.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.DreamStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.yoloroy.gameoflife.domain.model.data.Dream as DreamModel

class RoomActivityLocalDataSourceImpl @Inject constructor(
    private val activityDao: ActivityDao
) : ActivityLocalDataSource {

    override val currentDreamsDetails: Flow<Resource<List<DreamDetail>>>
        get() {
            return activityDao.getCurrentDreamsDetails()
                .mapToListResource(DreamFull::toDreamDetail)
        }

    override val currentDreams: Flow<Resource<List<DreamModel>>>
        get() {
            return activityDao.getCurrentDreamInfos()
                .mapToListResource(DreamInfo::toDomainDream)
        }

    override val currentChallengesWithDreamInfo: Flow<Resource<List<ChallengeWithDreamInfo>>>
        get() {
            return activityDao.getCurrentChallengesWithDreamInfo()
                .mapToListResource(ChallengeFullWithDream::toChallengeWithDreamInfo)
        }

    override fun getDreamStatus(dreamId: Int): Flow<Resource<DreamStatus>> {
        return activityDao.getDreamProgressOrNull(dreamId)
            .mapToResource(DreamProgress?::toDreamStatus)
    }

    override fun completeChallenge(challengeId: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            activityDao.completeChallenge(challengeId)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override fun subscribeOnDream(dreamId: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            activityDao.subscribeOnDream(dreamId)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override fun unsubscribeFromDream(dreamId: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            activityDao.unsubscribeFromDream(dreamId)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}