package com.appgim.data.di

import com.appgim.data.repository.AuthRepositoryImpl
import com.appgim.domain.auth.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DataModule {

    @Provides
    fun provideAuth(authRepositoryImpl: AuthRepositoryImpl): AuthRepository {
        return authRepositoryImpl
    }
}