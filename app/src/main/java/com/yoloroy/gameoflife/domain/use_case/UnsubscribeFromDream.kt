package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ActivityRepository

class UnsubscribeFromDream(val repository: ActivityRepository) {
    operator fun invoke(dreamId: String) = repository.unsubscribeFromDream(dreamId)
}
