package com.appgim.data.api

import com.appgim.data.api.dto.FormDataJson
import com.appgim.data.api.dto.GetFullFormDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


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

    @OptIn(InternalSerializationApi::class)
    @POST("form/save")
    suspend fun saveForm(@Body formData: FormDataJson): Boolean

    @OptIn(InternalSerializationApi::class)
    @GET("form/{id}")
    suspend fun getForm(@Path("id") id: Int): GetFullFormDto

    object FormApi {
        val retrofitService: RetrofitClient by lazy {
            retrofit.create(RetrofitClient::class.java)
        }
    }
}
