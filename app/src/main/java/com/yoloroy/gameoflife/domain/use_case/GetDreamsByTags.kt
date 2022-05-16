package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.DreamsRepository

class GetDreamsByTags(val repository: DreamsRepository) {
    operator fun invoke(tags: List<String>) = repository.getDreamsByTags(tags)
}
