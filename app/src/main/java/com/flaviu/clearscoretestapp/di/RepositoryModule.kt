package com.flaviu.clearscoretestapp.di

import com.flaviu.clearscoretestapp.data.IMainRepository
import com.flaviu.clearscoretestapp.data.MainRepository
import com.flaviu.clearscoretestapp.network.APIRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun creditScoreRepository(creditScoreApi: APIRequest): IMainRepository {
        return MainRepository(creditScoreApi)
    }
}