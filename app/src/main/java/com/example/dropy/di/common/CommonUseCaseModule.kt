package com.example.dropy.di.common

import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.common.DirectionMineUseCase
import com.example.dropy.network.use_case.common.DirectionUseCase
import com.example.dropy.network.use_case.common.GetDeliveryMethodsUseCase
import com.example.dropy.network.use_case.shops.frontside.GetAllShopsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CommonUseCaseModule {

    @Provides
    @Singleton
    fun providedirectionUseCase(
       commonRepository: CommonRepository
    ): DirectionUseCase {
        return DirectionUseCase(commonRepository = commonRepository)
    }

    @Provides
    @Singleton
    fun providedirectionMineUseCase(
       commonRepository: CommonRepository
    ): DirectionMineUseCase {
        return DirectionMineUseCase(commonRepository = commonRepository)
    }

    @Provides
    @Singleton
    fun providegetDeliveryMethodsUseCase(
       commonRepository: CommonRepository
    ): GetDeliveryMethodsUseCase {
        return GetDeliveryMethodsUseCase(commonRepository = commonRepository)
    }
}