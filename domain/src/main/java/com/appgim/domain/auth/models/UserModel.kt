package com.appgim.domain.auth.models

data class UserModel(
    val id: Int,
    val name: String,
    val surname: String,
    val pictureUrl: String,
    val email: String,
    val isAdmin: Boolean
)