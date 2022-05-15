package com.yoloroy.gameoflife.domain.bad_repository

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.Dream
import kotlinx.coroutines.flow.Flow

interface DreamsLibraryRepository {
    suspend fun getDreamsByTags(tags: Collection<String>): Flow<Resource<List<Dream>>>
}
