package com.yoloroy.gameoflife.data.remote.api

import com.yoloroy.gameoflife.data.remote.dto.DreamDetailDto
import com.yoloroy.gameoflife.data.remote.dto.DreamDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DreamsApi {

    @GET("dreams")
    suspend fun getDreamsByTags(@Query("tags") tags: String) : Response<List<DreamDto>>

    @GET("dream/{dreamId}")
    suspend fun getDreamsDetail(@Path("dreamId") dreamId: String) : Response<DreamDetailDto>
}
