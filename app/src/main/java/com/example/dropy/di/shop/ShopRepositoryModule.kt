package com.example.dropy.di.shop

import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.repositories.shop.back.ShopBackendRepositoryImpl
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepositoryImpl
import com.example.dropy.network.services.payment.PaymentService
import com.example.dropy.network.services.shops.ShopsBackendService
import com.example.dropy.network.services.shops.ShopsFrontService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ShopRepositoryModule {

    @Singleton
    @Provides
    fun provideShopFrontEndRepository(
        shopsFrontService: ShopsFrontService,
        client: HttpClient,
        paymentService: PaymentService
    ): ShopFrontendRepository {
        return ShopFrontendRepositoryImpl(
            shopsFrontService = shopsFrontService,
            client = client,
            paymentService = paymentService
        )
    }

    @Singleton
    @Provides
    fun provideShopBackEndRepository(
        shopsBackendService: ShopsBackendService,
        client: HttpClient,
    ): ShopBackendRepository {
        return ShopBackendRepositoryImpl(
            shopsBackendService = shopsBackendService,
            client = client
        )
    }
}