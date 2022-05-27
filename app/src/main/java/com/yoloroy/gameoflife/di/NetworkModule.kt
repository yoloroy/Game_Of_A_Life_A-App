package com.yoloroy.gameoflife.di

import com.yoloroy.gameoflife.data.remote.DreamsRemoteSource
import com.yoloroy.gameoflife.data.remote.api.DreamsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = "http://192.168.0.96:8080/" // TODO

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideDreamsApi(retrofit: Retrofit): DreamsApi = retrofit.create(DreamsApi::class.java)

    @Provides
    @Singleton
    fun provideDreamsRemoteSource(dreamsApi: DreamsApi) = DreamsRemoteSource(dreamsApi)
}
