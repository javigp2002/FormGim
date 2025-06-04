package com.appgim.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST


private const val BASE_URL =
    "http://10.0.2.2:3000"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface RetrofitClient {

    @POST("new_forms")
    suspend fun getNewForm(@Body requestBody: Map<String, Int>): List<BasicFormDto>

    @POST("done_forms")
    suspend fun getDoneForm(@Body requestBody: Map<String, Int>): List<BasicFormDto>

    @POST("author_forms")
    suspend fun getAuthorForm(@Body requestBody: Map<String, Int>): List<BasicFormDto>

    object FormApi {
        val retrofitService: RetrofitClient by lazy {
            retrofit.create(RetrofitClient::class.java)
        }
    }
}
