package com.example.dropy.di.shop.frontside

import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.shops.frontside.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShopFrontSideUseCasesModule {

    @Provides
    @Singleton
    fun providegetAllShopsUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): GetAllShopsUseCase {
        return GetAllShopsUseCase(shopFrontendRepository = shopFrontendRepository)
    }

    @Provides
    @Singleton
    fun providegetAllShopCategoriesUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): GetAllShopCategoriesUseCase {
        return GetAllShopCategoriesUseCase(shopFrontendRepository = shopFrontendRepository)
    }

   @Provides
    @Singleton
    fun providegetProductDetailsUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): GetProductDetailsUseCase {
        return GetProductDetailsUseCase(shopFrontendRepository = shopFrontendRepository)
    }

    @Provides
    @Singleton
    fun providegetShopDetailsUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): GetShopDetailsUseCase {
        return GetShopDetailsUseCase(shopFrontendRepository = shopFrontendRepository)
    }

    @Provides
    @Singleton
    fun providefavouriteShopUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): FavouriteShopUseCase {
        return FavouriteShopUseCase(shopFrontendRepository = shopFrontendRepository)
    }

    @Provides
    @Singleton
    fun providegetFavouriteShopsUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): GetFavouriteShopsUseCase {
        return GetFavouriteShopsUseCase(shopFrontendRepository = shopFrontendRepository)
    }

    @Provides
    @Singleton
    fun providegetCustomerQrUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): GetCustomerQrUseCase {
        return GetCustomerQrUseCase(shopFrontendRepository = shopFrontendRepository)
    }

    @Provides
    @Singleton
    fun providegetCustomerOrdersUseCase(
        shopFrontendRepository: ShopFrontendRepository
    ): GetCustomerOrdersUseCase {
        return GetCustomerOrdersUseCase(shopFrontendRepository = shopFrontendRepository)
    }


}