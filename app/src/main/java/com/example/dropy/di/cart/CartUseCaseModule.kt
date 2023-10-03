package com.example.dropy.di.cart

import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.cart.*
import com.example.dropy.network.use_case.shops.frontside.GetAllShopsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CartUseCaseModule {
    @Provides
    @Singleton
    fun providegetCartItemsUseCase(
        cartRepository: CartRepository
    ): GetCartItemsUseCase {
        return GetCartItemsUseCase(cartRepository = cartRepository)
    }

    @Provides
    @Singleton
    fun providepostCartItemsUseCase(
        cartRepository: CartRepository
    ): PostCartItemsUseCase {
        return PostCartItemsUseCase(cartRepository = cartRepository)
    }

    @Provides
    @Singleton
    fun provideplaceOrderUseCase(
        cartRepository: CartRepository
    ): PlaceOrderUseCase {
        return PlaceOrderUseCase(cartRepository = cartRepository)
    }

    @Provides
    @Singleton
    fun providecancelOrderUseCase(
        cartRepository: CartRepository
    ): CancelOrderUseCase {
        return CancelOrderUseCase(cartRepository = cartRepository)
    }

    @Provides
    @Singleton
    fun providedeleteOrderUseCase(
        cartRepository: CartRepository
    ): DeleteOrderUseCase {
        return DeleteOrderUseCase(cartRepository = cartRepository)
    }

    @Provides
    @Singleton
    fun provideorderDetailsUseCase(
        cartRepository: CartRepository
    ): OrderDetailsUseCase {
        return OrderDetailsUseCase(cartRepository = cartRepository)
    }
    @Provides
    @Singleton
    fun provideprocessOrderUseCase(
        cartRepository: CartRepository
    ): ProcessOrderUseCase {
        return ProcessOrderUseCase(cartRepository = cartRepository)
    }

    @Provides
    @Singleton
    fun provideorderCheckOutUseCase(
        cartRepository: CartRepository
    ): OrderCheckOutUseCase {
        return OrderCheckOutUseCase(cartRepository = cartRepository)
    }

}