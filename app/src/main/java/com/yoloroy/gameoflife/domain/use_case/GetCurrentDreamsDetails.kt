package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ActivityRepository
import javax.inject.Inject

class GetCurrentDreamsDetails @Inject constructor(val repository: ActivityRepository) {
    operator fun invoke() = repository.currentDreamsDetails
}

