package com.yoloroy.gameoflife.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded

@DatabaseView(
    viewName = "dream_with_progress",
    value = """
        SELECT d.*, ds.profile_id as profile_id, ds.progress as progress, ds.is_finished
        FROM dream d
        JOIN dream_status ds on ds.dream_id = d.dream_id
    """
)
data class DreamInfoWithProgress(
    @Embedded
    val dreamInfo: DreamInfo,

    @ColumnInfo(name = "profile_id")
    val profileId: Int,

    @ColumnInfo(name = "progress")
    val progress: Int,

    @ColumnInfo(name = "is_finished")
    val isFinished: Boolean
) {
    val dream get() = dreamInfo.dream

    val dreamProgress get() = DreamProgress(profileId, dream.dreamId, progress)

    val dreamStatus get() = DreamStatus(dreamProgress, isFinished)
}
