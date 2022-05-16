package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ProfileRepository

class ResetStats(val repository: ProfileRepository) {
    operator fun invoke() = repository.resetStats()
}
