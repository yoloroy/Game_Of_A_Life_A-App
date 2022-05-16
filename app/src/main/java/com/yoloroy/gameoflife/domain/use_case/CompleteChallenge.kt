package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ActivityRepository

class CompleteChallenge(val repository: ActivityRepository) {
    operator fun invoke(challengeId: String) = repository.completeChallenge(challengeId)
}
