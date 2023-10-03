package com.example.dropy.di.shop

import com.example.dropy.network.services.shops.ShopsBackendService
import com.example.dropy.network.services.shops.ShopsFrontService
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ShopModule {

    @Singleton
    @Provides
    fun provideShopFrontService(retrofit: Retrofit, client: OkHttpClient): ShopsFrontService {
        return Retrofit
            .Builder()
            //.baseUrl("https://dropyapi.herokuapp.com/")
            .baseUrl("$BaseUrL/")
            .addConverterFactory(GsonConverterFactory.create())
            /*.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)*/
            .build().create(ShopsFrontService::class.java)
    }

    @Singleton
    @Provides
    fun provideShopBackService(retrofit: Retrofit, client: OkHttpClient): ShopsBackendService {
        return Retrofit
            .Builder()
            // .baseUrl("https://dropyapi.herokuapp.com/")
            .baseUrl("${BaseUrL}/")
            .addConverterFactory(GsonConverterFactory.create())
            /*.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)*/
            .build().create(ShopsBackendService::class.java)
    }

}