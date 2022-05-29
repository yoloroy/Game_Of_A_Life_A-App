package com.yoloroy.gameoflife.data.local

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.data_source.DreamsLocalDataSource
import com.yoloroy.gameoflife.data.local.dao.DreamsDao
import com.yoloroy.gameoflife.data.local.dao.insertFullDream
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import javax.inject.Inject

class RoomDreamsLocalDataSourceImpl @Inject constructor(
    private val dreamsDao: DreamsDao
) : DreamsLocalDataSource {

    override suspend fun getDreamDetail(dreamId: String): Resource<DreamDetail> {
        val fullDream = dreamsDao.getFullDream(dreamId.toInt())
        val dreamDetail = fullDream?.toDreamDetail()
        return dreamDetail
            ?.let { Resource.Success(it) }
            ?: Resource.Error(NoSuchElementException())
    }

    override suspend fun addDreamDetail(dreamDetail: DreamDetail): Resource<Boolean> {
        return try {
            dreamsDao.insertFullDream(dreamDetail.toDreamFull())
            Resource.Success(true)
        } catch (e: Exception) { // TODO exception type
            Resource.Error(e)
        }
    }
}