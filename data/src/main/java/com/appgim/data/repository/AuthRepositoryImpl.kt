package com.appgim.data.repository

import com.appgim.domain.auth.repositories.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override fun signIn(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgetPassword(email: String): Boolean {
        TODO("Not yet implemented")
    }
}