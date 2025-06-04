package com.appgim.data.local

import android.content.Context
import androidx.core.content.edit
import com.appgim.domain.auth.models.UserModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

val USER_KEY = "user_key"

class SharedPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getUser(): UserModel? {
        return UserModel(
            id = 1,
            idGoogle = "sad",
            name = "John",
            surname = "Doe",
            email = "jd@gmail.com",
            isAdmin = true
        )


        val sharedPreferences = context.getSharedPreferences("USER_KEY", Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString(USER_KEY, null)
        return if (userJson != null) Gson().fromJson(userJson, UserModel::class.java) else null
    }

    fun saveUser(user: UserModel): Boolean {
        val sharedPreferences = context.getSharedPreferences("USER_KEY", Context.MODE_PRIVATE)
        val userJson = Gson().toJson(user)
        sharedPreferences.edit(commit = true) {
            putString(USER_KEY, userJson)
        }
        return true
    }
}