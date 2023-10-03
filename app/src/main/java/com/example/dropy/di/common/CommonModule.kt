package com.example.dropy.di.common

import com.example.dropy.network.services.commons.CommonService
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
object CommonModule {
    @Singleton
    @Provides
    fun provideCommonService(retrofit: Retrofit, client: OkHttpClient): CommonService {
        return Retrofit
            .Builder()
            .baseUrl("$BaseUrL/commons/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build().create(CommonService::class.java)
    }
}