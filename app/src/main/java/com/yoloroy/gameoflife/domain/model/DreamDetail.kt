package com.yoloroy.gameoflife.domain.model

data class DreamDetail(
    val id: String,
    val name: String,
    val description: String,
    val tags: List<String>,
    val challenges: List<Challenge>
)
