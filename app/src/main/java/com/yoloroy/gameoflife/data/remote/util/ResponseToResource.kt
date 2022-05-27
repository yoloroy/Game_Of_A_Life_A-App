package com.yoloroy.gameoflife.data.remote.util

import com.yoloroy.gameoflife.common.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

inline fun <T> resource(function: () -> Response<T>): Resource<T> {
    return try {
        val response = function()
        if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(Exception(response.message()))
        }
    } catch (e: IOException) {
        Resource.Error(e)
    } catch (e: HttpException) {
        Resource.Error(e)
    }
}
