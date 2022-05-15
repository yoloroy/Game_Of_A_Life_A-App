package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.domain.bad_repository.ProfileRepository
import com.yoloroy.gameoflife.domain.model.Dream
import com.yoloroy.gameoflife.domain.model.Profile
import com.yoloroy.gameoflife.domain.model.Skill

object ProfileRepositoryFakeImpl : ProfileRepository {
    override val profile get() = Profile(
        name = "AndroidDancer",
        level = 3,
        exp = 83,
        maxExp = 128,
        skills = listOf(
            Skill(name = "Android", level = 11),
            Skill(name = "Planning", level = 8),
            Skill(name = "Design", level = 6),
            Skill(name = "Intelligence", level = 6),
            Skill(name = "Endurance", level = 5)
        ),
        fulfilledDreams = listOf(
            Dream("-1", "Your first fulfilled dream", "...", emptyList())
        )
    )
}