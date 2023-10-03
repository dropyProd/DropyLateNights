package com.example.dropy.di.authentication

import com.example.dropy.network.repositories.authentication.AuthenticationRepository
import com.example.dropy.network.repositories.authentication.AuthenticationRepositoryImpl
import com.example.dropy.network.services.register.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationRepositoryModule {
    @Singleton
    @Provides
    fun provideAuthenticationRepository(
       registerService: RegisterService
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            registerService = registerService
        )
    }
}