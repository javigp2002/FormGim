package com.appgim.domain.auth.repositories

import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authState: StateFlow<Boolean>
    fun signIn(email: String, password: String): Boolean

    fun forgetPassword(email: String): Boolean

}