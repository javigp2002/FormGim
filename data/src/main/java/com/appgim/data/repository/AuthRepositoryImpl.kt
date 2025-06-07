package com.appgim.data.repository

import com.appgim.data.api.isUserAuthorized
import com.appgim.domain.auth.repositories.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    private val _isUserValid = MutableStateFlow(true)
    override val authState: StateFlow<Boolean> = _isUserValid.asStateFlow()


    init {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            isUserAuthorized.collect { isAuthorized ->
                _isUserValid.value = isAuthorized
            }
        }
    }

    override fun signIn(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgetPassword(email: String): Boolean {
        TODO("Not yet implemented")
    }
}