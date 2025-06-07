package com.appgim.domain.auth.repositories
import com.appgim.domain.auth.models.UserModel

interface AuthRepository {
    suspend fun signInWithServer(googleToken : String): Result<UserModel>

    fun signIn(email: String, password: String): Boolean

    fun forgetPassword(email: String): Boolean
}