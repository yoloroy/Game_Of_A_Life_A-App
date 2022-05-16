package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.domain.model.data.*
import kotlinx.coroutines.flow.MutableStateFlow

internal object FakeSource {
    private var idCounter = Int.MIN_VALUE
    private val id get() = idCounter++.toString()

    var dreams: List<DreamDetail>

    val profileDetailsFlow: MutableStateFlow<ProfileDetails>

    private val _ongoingDreamsIdsProgress = mutableListOf<DreamIdProgress>()
    var ongoingDreamsIdsProgress: List<DreamIdProgress>
        get() = _ongoingDreamsIdsProgress
        set(value) {
            _ongoingDreamsIdsProgress.apply {
                clear()
                addAll(value.filterNot { (id, no) -> no >= getDreamDetailById(id).challenges.size })
                fulfilledDreamsIds.addAll(value.filter { (id, no) -> no >= getDreamDetailById(id).challenges.size }.map { id })
            }
            profileDetailsFlow.value = getProfileDetails()
        }
    val ongoingDreamsIds get() = _ongoingDreamsIdsProgress.map(DreamIdProgress::dreamId)
    val fulfilledDreamsIds = mutableListOf<String>()

    fun getDreamProgressById(dreamId: String) = getDreamProgressByIdOrNull(dreamId)!!

    fun getDreamProgressByIdOrNull(dreamId: String) = _ongoingDreamsIdsProgress
        .find { it.dreamId == dreamId }

    fun getDreamDetailById(dreamId: String) = getDreamDetailByIdOrNull(dreamId)!!

    fun getDreamDetailByIdOrNull(dreamId: String) = dreams.find { it.id == dreamId }

    fun getDreamIdByChallengeId(challengeId: String) = dreams
        .find { dream ->
            dream.challenges.any { it.id == challengeId }
        }!!.id

    data class DreamIdProgress(
        val dreamId: String,
        val no: Int
    )

    init {
        val ongoingDreamsIdsProgress = mutableListOf<DreamIdProgress>()
        dreams = listOf(
            DreamDetail(
                id.also { ongoingDreamsIdsProgress += DreamIdProgress(it, 1) },
                "Goal tutorial",
                "Learning how to use Goal app",
                tags = listOf("Getting started"),
                challenges = listOf(
                    Challenge(
                        "$idCounter",
                        "Your first challenge",
                        "To complete your challenge, you need to click on ‘done’ button at bottom right corner of challenge card",
                        1
                    ),
                    Challenge(
                        "$idCounter",
                        "Your second challenge",
                        "You can see your fulfilled dreams in Profile tab",
                        2
                    ),
                    Challenge("$idCounter", "...", "...", 3)
                )
            ),
            DreamDetail(
                id.also { fulfilledDreamsIds += it },
                "Your first fulfilled dream",
                "It just part of tutorial",
                tags = listOf("Getting started"),
                challenges = listOf()
            )
        ) + List(10) { i ->
            DreamDetail(
                id,
                "fake_dream$i",
                "...",
                tags = (10 downTo i).toList().map { "__$it" },
                challenges = listOf(
                    Challenge(id, "fake challenge_$i", "fake for test", 1)
                )
            )
        }
        this.ongoingDreamsIdsProgress = ongoingDreamsIdsProgress

        profileDetailsFlow = MutableStateFlow(getProfileDetails())
    }

    private fun getProfileDetails() = ProfileDetails(
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
        fulfilledDreams = fulfilledDreamsIds.map(::getDreamDetailById).map { Dream(it) },
        ongoingDreams = ongoingDreamsIds.map(::getDreamDetailById).map(::Dream)
    )
}

internal var FakeSource.profileDetails: ProfileDetails by FakeSource.profileDetailsFlow::value
