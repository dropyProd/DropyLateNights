package com.example.dropy.di.water

import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepositoryImpl
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.network.repositories.waterpoint.WaterRepositoryImpl
import com.example.dropy.network.services.payment.PaymentService
import com.example.dropy.network.services.shops.ShopsFrontService
import com.example.dropy.network.services.water.WaterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WaterRepositoryModule {

    @Singleton
    @Provides
    fun provideWaterRepository(
        client: HttpClient,
        waterService: WaterService
    ): WaterRepository {
        return WaterRepositoryImpl(
            client = client, waterService = waterService
        )
    }
}