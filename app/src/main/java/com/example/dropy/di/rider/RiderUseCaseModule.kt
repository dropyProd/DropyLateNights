package com.example.dropy.di.rider

import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.network.use_case.common.DirectionUseCase
import com.example.dropy.network.use_case.rider.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RiderUseCaseModule {

    @Provides
    @Singleton
    fun providereviewRiderUseCase(
       riderRepository: RiderRepository
    ): ReviewRiderUseCase {
        return ReviewRiderUseCase(riderRepository = riderRepository)
    }

    @Provides
    @Singleton
    fun providegetOngoingJobsUseCase(
       riderRepository: RiderRepository
    ): GetOngoingJobsUseCase {
        return GetOngoingJobsUseCase(riderRepository = riderRepository)
    }

    @Provides
    @Singleton
    fun provideverifyCustQrUseCase(
       riderRepository: RiderRepository
    ): VerifyCustQrUseCase {
        return VerifyCustQrUseCase(riderRepository = riderRepository)
    }

    @Provides
    @Singleton
    fun provideverifyQrUseCase(
       riderRepository: RiderRepository
    ): VerifyQrUseCase {
        return VerifyQrUseCase(riderRepository = riderRepository)
    }

    @Provides
    @Singleton
    fun provideacceptIncomingJobsUseCase(
       riderRepository: RiderRepository
    ): AcceptIncomingJobsuseCase {
        return AcceptIncomingJobsuseCase(riderRepository = riderRepository)
    }

    @Provides
    @Singleton
    fun providecancelIncomingJobUseCase(
       riderRepository: RiderRepository
    ): CancelIncomingJobUseCase {
        return CancelIncomingJobUseCase(riderRepository = riderRepository)
    }
    @Provides
    @Singleton
    fun providegetAllRiderPoolsUseCase(
       riderRepository: RiderRepository
    ): GetAllRiderPoolsUseCase {
        return GetAllRiderPoolsUseCase(riderRepository = riderRepository)
    }

    @Provides
    @Singleton
    fun providecreateRiderUseCase(
       riderRepository: RiderRepository
    ): CreateRiderUseCase {
        return CreateRiderUseCase(riderRepository = riderRepository)
    }

    @Provides
    @Singleton
    fun providegetIncomingJobsUseCase(
       riderRepository: RiderRepository
    ): GetIncomingJobsUseCase {
        return GetIncomingJobsUseCase(riderRepository = riderRepository)
    }
}