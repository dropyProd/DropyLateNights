package com.example.dropy.di.common

import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.commons.CommonRepositoryImpl
import com.example.dropy.network.services.commons.CommonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CommonRepositoryModule {

    @Singleton
    @Provides
    fun provideCommonRepository(
        commonService: CommonService,
        client: HttpClient
    ): CommonRepository {
        return CommonRepositoryImpl(
           commonService = commonService,
            client = client
        )
    }
}