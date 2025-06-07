package com.appgim.data.api.dto.from_back

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class BackendAuthResponse(
    val id: Int,
    val name: String,
    val surname: String,
    val pictureUrl: String,
    val email: String,
    val isAdmin: Boolean
)