package com.yoloroy.gameoflife.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded

@DatabaseView(
    viewName = "dream_status",
    value = """
        WITH dreams_sizes as (
            SELECT c.dream_id, COUNT(c.dream_id) as size
            FROM challenge c
            GROUP BY c.dream_id
        )
        SELECT dp.*, dp.progress = ds.size as is_finished
        FROM dream_progress dp
        JOIN dreams_sizes ds ON ds.dream_id = dp.dream_id
    """
)
data class DreamStatus(
    @Embedded
    val dreamProgress: DreamProgress,

    @ColumnInfo(name = "is_finished")
    val isFinished: Boolean
)
