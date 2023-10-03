package com.example.dropy.di.rider

import com.example.dropy.network.services.rider.RiderService
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
object RiderModule {
    @Singleton
    @Provides
    fun provideRiderService(retrofit: Retrofit, client: OkHttpClient): RiderService {
        return Retrofit
            .Builder()
            .baseUrl("$BaseUrL/rider/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build().create(RiderService::class.java)
    }
}