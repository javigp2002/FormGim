package com.appgim.data.api

import com.appgim.data.api.dto.from_back.BackendAuthResponse
import com.appgim.data.api.dto.to_back.SendTokenDto
import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthRetrofitClient {
    @OptIn(InternalSerializationApi::class)
    @POST("login")
    suspend fun signInWithGoogleToken(@Body googleToken: SendTokenDto): BackendAuthResponse
}
