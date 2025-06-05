package com.appgim.data.api.dto.from_back

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class GetFullFormDto(
    val id: Int,
    val title: String,
    val description: String,
    val authorName: String,
    val timesFormDone: Int,
    val questions: List<QuestionDto>
)

@InternalSerializationApi
@Serializable
data class QuestionDto(
    val id: Int,
    val title: String,
    val questionType: Int,
    val options: List<String>,
    val answers: List<String> = emptyList()
)