package com.example.dropy.di.authentication

import com.example.dropy.network.repositories.authentication.AuthenticationRepository
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.use_case.authentication.CheckIfUserExistUseCase
import com.example.dropy.network.use_case.authentication.RegisteruserUseCase
import com.example.dropy.network.use_case.common.DirectionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationUseCaseModule {

    @Provides
    @Singleton
    fun providecheckIfUserExistUseCase(
       authenticationRepository: AuthenticationRepository
    ): CheckIfUserExistUseCase {
        return CheckIfUserExistUseCase(authenticationRepository = authenticationRepository)
    }

    @Provides
    @Singleton
    fun provideregisterUserUseCase(
       authenticationRepository: AuthenticationRepository
    ): RegisteruserUseCase {
        return RegisteruserUseCase(authenticationRepository = authenticationRepository)
    }
}