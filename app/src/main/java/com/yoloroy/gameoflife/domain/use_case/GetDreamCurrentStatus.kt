package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ActivityRepository

class GetDreamCurrentStatus(val repository: ActivityRepository) {
    operator fun invoke(dreamId: String) = repository.getDreamStatus(dreamId)
}
