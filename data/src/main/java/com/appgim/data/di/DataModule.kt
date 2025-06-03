package com.appgim.data.di

import com.appgim.data.repository.AuthRepositoryImpl
import com.appgim.data.repository.FormRepositoryImpl
import com.appgim.data.repository.UserRepositoryImpl
import com.appgim.domain.auth.repositories.AuthRepository
import com.appgim.domain.auth.repositories.UserRepository
import com.appgim.domain.main.home.repositories.FormRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAuth(authRepositoryImpl: AuthRepositoryImpl): AuthRepository {
        return authRepositoryImpl
    }

    @Provides
    fun provideForm(formRepositoryImpl: FormRepositoryImpl): FormRepository {
        return formRepositoryImpl
    }

    @Provides
    fun provideUser(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }
}