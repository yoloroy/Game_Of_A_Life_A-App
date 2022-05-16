package com.yoloroy.gameoflife.domain.model.data

data class Dream(
    val id: String,
    val name: String,
    val description: String,
    val tags: List<String>
) {
    constructor(dreamDetail: DreamDetail) : this(
        dreamDetail.id,
        dreamDetail.name,
        dreamDetail.description,
        dreamDetail.tags
    )
}
