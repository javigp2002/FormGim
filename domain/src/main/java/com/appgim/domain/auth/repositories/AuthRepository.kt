package com.appgim.domain.auth.repositories

interface AuthRepository {
    fun signIn(email: String, password: String): Boolean

    fun forgetPassword(email: String): Boolean
}