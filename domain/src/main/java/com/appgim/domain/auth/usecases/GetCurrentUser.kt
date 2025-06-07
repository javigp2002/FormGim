package com.appgim.domain.auth.usecases

import com.appgim.domain.auth.models.UserModel
import com.appgim.domain.auth.repositories.UserRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val userRepository: UserRepository
) {

    fun run(): Result<UserModel> {
        var user = userRepository.getUser()

        user = UserModel(
            id = 1,
            name = "John",
            surname = "Doe",
            pictureUrl = "https://example.com/john_doe.jpg",
            email = "jd@gmail.com",
            isAdmin = true
        )



        return if (user != null) {
            Result.success(user)
        } else {
            Result.failure(Exception("No user found"))
        }
    }
}