package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.DreamsRepository

class GetDreamDetail(val repository: DreamsRepository) {
    operator fun invoke(dreamId: String) = repository.getDreamDetail(dreamId)
}
