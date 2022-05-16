package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.fake.FakeSource.getDreamIdByChallengeId
import com.yoloroy.gameoflife.data.fake.FakeSource.profileDetailsFlow
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.DreamStatus
import com.yoloroy.gameoflife.domain.repository.ActivityRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object ActivityRepositoryFakeImpl : ActivityRepository {

    override val currentDreamsDetails: Flow<Resource<List<DreamDetail>>> = flow {
        emit(Resource.Loading())
        profileDetailsFlow.collect { profile ->
            emit(
                Resource.Success(
                    profile.ongoingDreams
                        .asSequence()
                        .map(Dream::id)
                        .map(FakeSource::getDreamDetailById)
                        .toList()
                )
            )
        }
    }

    override fun getDreamStatus(dreamId: String): Flow<Resource<DreamStatus>> = flow {
        emit(Resource.Loading())
        delay(0.5.seconds)

        val status = when (
            val progress = FakeSource.getDreamProgressByIdOrNull(dreamId)?.no
        ) {
            null -> DreamStatus.None
            1 -> DreamStatus.Subscribed
            progress.takeIf { it <= FakeSource.getDreamDetailById(dreamId).challenges.size } -> DreamStatus.InProcess(progress)
            else -> DreamStatus.Finished
        }
        emit(Resource.Success(status))
    }

    override fun completeChallenge(challengeId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        val dreamId = getDreamIdByChallengeId(challengeId)
        FakeSource.ongoingDreamsIdsProgress = FakeSource.ongoingDreamsIdsProgress.run {
            val element = find { it.dreamId == dreamId }!!
            this - element + element.copy(no = element.no + 1)
        }

        emit(Resource.Success(true))
    }

    override fun subscribeOnDream(dreamId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        FakeSource.ongoingDreamsIdsProgress += FakeSource.DreamIdProgress(dreamId, 1)

        emit(Resource.Success(true))
    }

    override fun unsubscribeFromDream(dreamId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        FakeSource.ongoingDreamsIdsProgress -= FakeSource.DreamIdProgress(dreamId, 1)

        emit(Resource.Success(true))
    }
}
