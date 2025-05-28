package com.appgim.domain.auth.usecases

import com.appgim.domain.auth.models.UserModel
import com.appgim.domain.auth.repositories.UserRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val userRepository: UserRepository
) {

    fun run(): Result<UserModel> {
        var user = userRepository.getUser()

        user = UserModel(1, "John Doe", true) // Simulating a user for demonstration purposes

        return if (user != null) {
            Result.success(user)
        } else {
            Result.failure(Exception("No user found"))
        }
    }
}