package com.appgim.data.api.dto.from_back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicFormDto(
    val id: Int,
    val title: String,
    val description: String,

    @SerialName(value = "author_name")
    val authorName: String,
)