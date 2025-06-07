package com.appgim.data.repository

import com.appgim.data.api.RetrofitClient.FormApi
import com.appgim.data.api.dto.to_back.SendTokenDto
import com.appgim.data.api.isUserAuthorized
import com.appgim.domain.auth.models.UserModel
import com.appgim.domain.auth.repositories.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
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

    @OptIn(InternalSerializationApi::class)
    override suspend fun signInWithServer(googleToken: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            try {
                val backResponse = FormApi.retrofitService.signInWithGoogleToken(SendTokenDto(googleToken))

                val user = UserModel(
                    id = backResponse.id,
                    name = backResponse.name,
                    surname = backResponse.surname,
                    email = backResponse.email,
                    pictureUrl = backResponse.pictureUrl,
                    isAdmin = backResponse.isAdmin == 1
                )

                Result.success(user)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override fun signIn(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgetPassword(email: String): Boolean {
        TODO("Not yet implemented")
    }
}