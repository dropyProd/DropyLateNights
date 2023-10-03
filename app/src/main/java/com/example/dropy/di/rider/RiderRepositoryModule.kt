package com.example.dropy.di.rider

import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.network.repositories.rider.RiderRepositoryImpl
import com.example.dropy.network.services.rider.RiderService
import com.example.dropy.network.services.users.UsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RiderRepositoryModule {

    @Singleton
    @Provides
    fun provideRiderRepository(
        usersService: UsersService,
        riderService: RiderService,
        client: HttpClient,
    ): RiderRepository {
        return RiderRepositoryImpl(
            usersService = usersService,
            client = client,
            riderService = riderService
        )
    }

}