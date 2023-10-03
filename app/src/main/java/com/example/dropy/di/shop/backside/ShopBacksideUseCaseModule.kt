package com.example.dropy.di.shop.backside

import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.use_case.cart.ProcessOrderUseCase
import com.example.dropy.network.use_case.shops.backside.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShopBacksideUseCaseModule {

    @Provides
    @Singleton
    fun providegetShopQrUseCase(
        shopBackendRepository: ShopBackendRepository
    ): GetShopQrUseCase {
        return GetShopQrUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun providegetShopProductCategoriesUseCase(
        shopBackendRepository: ShopBackendRepository
    ): GetShopProductCategoriesUseCase {
        return GetShopProductCategoriesUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun provideaddShopProductCategoriesUseCase(
        shopBackendRepository: ShopBackendRepository
    ): AddShopProductCategoriesUseCase {
        return AddShopProductCategoriesUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun provideaddShopProductCategoryUseCase(
        shopBackendRepository: ShopBackendRepository
    ): AddShopProductCategoryUseCase {
        return AddShopProductCategoryUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun provideaddShopUseCase(
        shopBackendRepository: ShopBackendRepository
    ): AddShopUseCase {
        return AddShopUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun provideaddProductUseCase(
        shopBackendRepository: ShopBackendRepository
    ): AddProductUseCase {
        return AddProductUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun providedeleteProductUseCase(
        shopBackendRepository: ShopBackendRepository
    ): DeleteProductUseCase {
        return DeleteProductUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun providedeleteProductCategoryUseCase(
        shopBackendRepository: ShopBackendRepository
    ): DeleteProductCategoryUseCase {
        return DeleteProductCategoryUseCase(shopBackendRepository = shopBackendRepository)
    }



    @Provides
    @Singleton
    fun providegetShopPendingOrdersUseCase(
        shopBackendRepository: ShopBackendRepository
    ): GetShopPendingOrdersUseCase {
        return GetShopPendingOrdersUseCase(shopBackendRepository = shopBackendRepository)
    }

    @Provides
    @Singleton
    fun providegetShopCompletedOrdersUseCase(
        shopBackendRepository: ShopBackendRepository
    ): GetShopCompletedOrdersUseCase {
        return GetShopCompletedOrdersUseCase(shopBackendRepository = shopBackendRepository)
    }


}