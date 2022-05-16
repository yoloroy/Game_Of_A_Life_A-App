package com.yoloroy.gameoflife.domain.model.data

sealed class DreamStatus {
    object Subscribed : DreamStatus()
    object Finished : DreamStatus()
    class InProcess(val currentChallengeNo: Int) : DreamStatus()
    object None : DreamStatus()
}
