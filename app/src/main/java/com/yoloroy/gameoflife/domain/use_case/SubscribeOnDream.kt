package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ActivityRepository
import javax.inject.Inject

class SubscribeOnDream @Inject constructor(val repository: ActivityRepository) {
    operator fun invoke(dreamId: String) = repository.subscribeOnDream(dreamId)
}
