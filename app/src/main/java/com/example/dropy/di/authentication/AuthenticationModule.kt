package com.example.dropy.di.authentication

import com.example.dropy.network.services.register.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationModule {
    @Singleton
    @Provides
    fun provideRegisterService(retrofit: Retrofit) = retrofit.create(RegisterService::class.java)
}