package com.yoloroy.gameoflife.data.local

import com.yoloroy.gameoflife.data.local.entity.*
import com.yoloroy.gameoflife.domain.model.data.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.DreamStatus
import com.yoloroy.gameoflife.data.local.entity.Challenge as ChallengeEntity
import com.yoloroy.gameoflife.data.local.entity.Dream as DreamEntity
import com.yoloroy.gameoflife.data.local.entity.Skill as SkillEntity
import com.yoloroy.gameoflife.domain.model.data.Challenge as DomainChallenge
import com.yoloroy.gameoflife.domain.model.data.Dream as DomainDream
import com.yoloroy.gameoflife.domain.model.data.Skill as DomainSkill

fun DreamFull.toDreamDetail() = DreamDetail(
    id = dreamInfo.dream.dreamId.toString(), // TODO?
    name = dreamInfo.dream.name,
    description = dreamInfo.dream.description,
    tags = dreamInfo.tags.map(Tag::name),
    challenges = challenges.map(ChallengeEntity::toDomainChallenge)
)

fun DreamDetail.toDreamFull() = DreamFull(
    DreamInfo(
        DreamEntity(id.toInt(), name, description),
        tags.map { Tag(id.toInt(), it) }
    ),
    challenges.map { it.toChallengeEntity(id.toInt()) }
)

fun DreamInfo.toDomainDream() = DomainDream(
    id = dream.dreamId.toString(), // TODO id
    name = dream.name,
    description = dream.description,
    tags = tags.map(Tag::name)
)

// TODO id
fun ChallengeEntity.toDomainChallenge() = DomainChallenge(challengeId.toString(), name, description, no)

fun DomainChallenge.toChallengeEntity(dreamId: Int) = ChallengeEntity(id.toInt(), dreamId, name, description, no)

fun ChallengeFullWithDream.toChallengeWithDreamInfo(): ChallengeWithDreamInfo {
    val challenge = challengeFull.challenge
    return ChallengeWithDreamInfo(
        challenge.challengeId.toString(),
        challenge.name,
        challenge.description,
        challenge.no,
        challengeFull.skills.map(SkillEntity::toDomainSkill),
        dream.dreamId.toString(),
        dream.name
    )
}

fun SkillEntity.toDomainSkill() = DomainSkill(name, level)

fun DreamProgress?.toDreamStatus(challengesCount: Int? = null): DreamStatus {
    return when (this) {
        null -> DreamStatus.None
        else -> when (progress) {
            0 -> DreamStatus.Subscribed
            challengesCount -> DreamStatus.Finished
            else -> DreamStatus.InProcess(progress)
        }
    }
}
