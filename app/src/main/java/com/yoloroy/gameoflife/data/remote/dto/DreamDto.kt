package com.yoloroy.gameoflife.data.remote.dto

import com.yoloroy.gameoflife.domain.model.data.Dream

data class DreamDto(
    val id: String,
    val name: String,
    val description: String,
    val tags: List<String>
) {
    constructor(dreamDetailDto: DreamDetailDto) : this(
        dreamDetailDto.id,
        dreamDetailDto.name,
        dreamDetailDto.description,
        dreamDetailDto.tags
    )

    fun toModel() = Dream(id, name, description, tags)
}
