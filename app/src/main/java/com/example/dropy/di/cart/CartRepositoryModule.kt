package com.example.dropy.di.cart

import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.repositories.cart.CartRepositoryImpl
import com.example.dropy.network.services.cart.CartService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartRepositoryModule {

    @Singleton
    @Provides
    fun provideCartRepository(
        cartService: CartService,
        client: HttpClient,
    ): CartRepository {
        return CartRepositoryImpl(
           cartService = cartService,
            client = client,
        )
    }

}