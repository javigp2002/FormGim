package com.appgim.data.api.dto.to_back

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class SaveAnswersDto(
    val idQuestion: Int,
    val answer: String
)

@InternalSerializationApi
@Serializable
data class SaveAnswersFromUserDto(
    val idUser: Int,
    val answers: List<SaveAnswersDto>
)
