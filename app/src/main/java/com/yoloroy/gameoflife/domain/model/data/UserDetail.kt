package com.yoloroy.gameoflife.domain.model.data

data class UserDetail(
    val userId: String,
    val name: String,
    val characteristics: Map<String, Int>
)
