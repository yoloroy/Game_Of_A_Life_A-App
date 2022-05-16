package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ActivityRepository

class GetCurrentDreamsDetails(val repository: ActivityRepository) {
    operator fun invoke() = repository.currentDreamsDetails
}
