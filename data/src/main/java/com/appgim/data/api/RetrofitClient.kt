package com.appgim.data.api

import com.appgim.data.api.dto.from_back.BasicFormDto
import com.appgim.data.api.dto.from_back.GetFullFormDto
import com.appgim.data.api.dto.to_back.FormDataJson
import com.appgim.data.api.dto.to_back.SaveAnswersFromUserDto
import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface MainApiService {

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

    @OptIn(InternalSerializationApi::class)
    @GET("form/{id}/answered/{idUser}")
    suspend fun getFormAnswered(@Path("id") id: Int, @Path("idUser") idUser: Int): GetFullFormDto

    @OptIn(InternalSerializationApi::class)
    @GET("form/{id}/answers")
    suspend fun getFormAnswers(@Path("id") id: Int): GetFullFormDto

    @OptIn(InternalSerializationApi::class)
    @POST("form/{id}/save_answers")
    suspend fun saveAnswers(@Path("id") idForm: Int, @Body value: SaveAnswersFromUserDto): Boolean
}
