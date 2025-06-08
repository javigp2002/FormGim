package com.appgim.data.di

import com.appgim.data.api.AuthRetrofitClient
import com.appgim.data.api.MainApiService
import com.appgim.data.api.interceptor.AuthInterceptor
import com.appgim.data.api.interceptor.ResponseInterceptor
import com.appgim.data.local.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = "http://10.0.2.2:3000"

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        responseInterceptor: ResponseInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(responseInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(sharedPreferences: SharedPreferences): AuthInterceptor =
        AuthInterceptor(sharedPreferences)

    @Singleton
    @Provides
    fun provideResponseInterceptor(): ResponseInterceptor =
        ResponseInterceptor()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)

    @Singleton
    @Provides
    fun provideAuthAPIService(retrofit: Retrofit.Builder): AuthRetrofitClient =
        retrofit
            .build()
            .create(AuthRetrofitClient::class.java)

    @Singleton
    @Provides
    fun provideMainAPIService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): MainApiService =
        retrofit
            .client(okHttpClient)
            .build()
            .create(MainApiService::class.java)
}

