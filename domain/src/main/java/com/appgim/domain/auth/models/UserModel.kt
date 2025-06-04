package com.appgim.domain.auth.models

data class UserModel(
    val id: Int,
    val idGoogle: String,
    val email: String,
    val name: String,
    val surname: String,
    val isAdmin: Boolean
)