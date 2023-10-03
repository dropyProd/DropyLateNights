package com.example.dropy.di.cart

import com.example.dropy.network.services.cart.CartService
import com.example.dropy.network.services.shops.ShopsBackendService
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
object CartModule {
    @Singleton
    @Provides
    fun provideCartService(retrofit: Retrofit, client: OkHttpClient): CartService {
        return Retrofit
            .Builder()
            // .baseUrl("https://dropyapi.herokuapp.com/")
            .baseUrl("$BaseUrL/")
            .addConverterFactory(GsonConverterFactory.create())
            /*.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)*/
            .build().create(CartService::class.java)
    }

}