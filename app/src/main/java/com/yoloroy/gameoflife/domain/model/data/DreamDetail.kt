package com.yoloroy.gameoflife.domain.model.data

data class DreamDetail(
    val id: String,
    val name: String,
    val description: String,
    val tags: List<String>,
    val challenges: List<Challenge>
) {
    fun getChallenge(no: Int) = challenges.first { it.no == no }

    fun getChallengeWithDreamInfo(no: Int) = getChallenge(no).withDreamInfo(this)
}
