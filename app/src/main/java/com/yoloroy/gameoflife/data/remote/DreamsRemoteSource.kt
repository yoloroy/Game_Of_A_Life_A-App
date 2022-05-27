package com.yoloroy.gameoflife.data.remote

import android.util.Log
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.remote.api.DreamsApi
import com.yoloroy.gameoflife.data.remote.dto.DreamDetailDto
import com.yoloroy.gameoflife.data.remote.dto.DreamDto
import com.yoloroy.gameoflife.data.remote.util.resource
import javax.inject.Inject

class DreamsRemoteSource @Inject constructor(private val dreamsApi: DreamsApi) {

    suspend fun getDreamsByTags(tags: List<String>): Resource<List<DreamDto>> = resource {
        dreamsApi.getDreamsByTags(tags.joinToString(",")).also { Log.i("DRS", it.toString()) }
    }

    suspend fun getDreamDetail(dreamId: String): Resource<DreamDetailDto> = resource {
        dreamsApi.getDreamsDetail(dreamId).also { Log.i("DRS", it.toString()) }
    }
}
