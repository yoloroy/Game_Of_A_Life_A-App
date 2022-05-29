package com.yoloroy.gameoflife.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded

@DatabaseView(
    viewName = "dream_with_progress",
    value = """
        SELECT d.*, dp.profile_id as profile_id, dp.progress as progress
        FROM dream d
        JOIN dream_progress dp on dp.dream_id = d.dream_id
    """
)
data class DreamWithProgress(
    @Embedded
    val dream: Dream,

    @ColumnInfo(name = "profile_id")
    val profileId: Int,

    @ColumnInfo(name = "progress")
    val progress: Int
)