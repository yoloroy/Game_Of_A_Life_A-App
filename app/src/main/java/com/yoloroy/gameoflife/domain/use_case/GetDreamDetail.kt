package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.DreamsRepository
import javax.inject.Inject

class GetDreamDetail @Inject constructor(private val repository: DreamsRepository) {
    operator fun invoke(dreamId: String) = repository.getDreamDetail(dreamId)
}
