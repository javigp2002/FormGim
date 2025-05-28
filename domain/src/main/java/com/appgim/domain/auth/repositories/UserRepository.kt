package com.appgim.domain.auth.repositories

import com.appgim.domain.auth.models.UserModel

interface UserRepository {
    fun getUser(): UserModel?

    fun saveUser(user: UserModel): Boolean
}