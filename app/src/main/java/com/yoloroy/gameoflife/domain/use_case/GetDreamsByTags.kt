package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.DreamsRepository
import javax.inject.Inject

class GetDreamsByTags @Inject constructor(val repository: DreamsRepository) {
    operator fun invoke(tags: List<String>) = repository.getDreamsByTags(tags)
}
