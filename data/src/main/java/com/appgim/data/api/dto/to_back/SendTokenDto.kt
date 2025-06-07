package com.appgim.data.api.dto.to_back

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class SendTokenDto(
    val token: String,
)