package com.appgim.data.repository

import com.appgim.data.api.RetrofitClient.FormApi
import com.appgim.domain.auth.models.UserModel
import com.appgim.domain.auth.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    @OptIn(InternalSerializationApi::class)
    override suspend fun signInWithServer(googleToken: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            try {
                val backResponse = FormApi.retrofitService.signInWithGoogleToken(googleToken)

                val user = UserModel(
                    id = backResponse.id,
                    name = backResponse.name,
                    surname = backResponse.surname,
                    email = backResponse.email,
                    pictureUrl = backResponse.pictureUrl,
                    isAdmin = backResponse.isAdmin
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