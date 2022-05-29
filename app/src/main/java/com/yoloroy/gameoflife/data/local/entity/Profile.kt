package com.yoloroy.gameoflife.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey
    @ColumnInfo(name = "profile_id")
    val profileId: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @NonNull
    @ColumnInfo(name = "name")
    val name: String,

    @NonNull
    @ColumnInfo(name = "level", defaultValue = "${Default.level}")
    val level: Int,

    @NonNull
    @ColumnInfo(name = "exp", defaultValue = "${Default.exp}")
    val exp: Int,

    @NonNull
    @ColumnInfo(name = "max_exp", defaultValue = "${Default.maxExp}")
    val maxExp: Int
) {
    object Default {
        const val localProfileId = -1
        const val level = 1
        const val exp = 0
        const val maxExp = 25

        val profile get() = Profile(localProfileId, null, "New Guru", level, exp, maxExp)
    }
}
