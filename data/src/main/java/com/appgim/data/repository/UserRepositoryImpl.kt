package com.appgim.data.repository

import com.appgim.data.local.SharedPreferences
import com.appgim.domain.auth.models.UserModel
import com.appgim.domain.auth.repositories.UserRepository
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserRepository {
    override fun getUser(): UserModel? {
        return sharedPreferences.getUser()
    }

    override fun saveUser(user: UserModel): Boolean {
        return sharedPreferences.saveUser(user)
    }

    override fun saveToken(token: String) {
        return sharedPreferences.saveToken(token)
    }

    override fun getToken(): String? {
        return sharedPreferences.getToken()
    }

}