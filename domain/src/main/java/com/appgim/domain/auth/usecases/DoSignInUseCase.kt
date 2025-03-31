package com.appgim.domain.auth.usecases

import com.appgim.domain.auth.models.SignInModel
import com.appgim.domain.auth.repositories.AuthRepository
import javax.inject.Inject

class DoSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun run(model: SignInModel): Result<String> {
        val success = authRepository.signIn(model.email, model.password)

        val map = mapOf<String, Any>(
            "email" to model.email,
            "password" to model.password
        )


        return if (success) Result.success(model.email) else Result.failure(Exception("Error signing in"))
    }
}