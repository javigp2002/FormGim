package com.appgim.data.local

import android.content.Context
import androidx.core.content.edit
import com.appgim.domain.auth.models.UserModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

val USER_KEY = "user_key"
val TOKEN_KEY = "token_key"
val SHARED_PREFERENCES_NAME = "appgim_shared_preferences"

class SharedPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getUser(): UserModel? {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString(USER_KEY, null)
        return if (userJson != null) Gson().fromJson(userJson, UserModel::class.java) else null
    }

    fun saveUser(user: UserModel): Boolean {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val userJson = Gson().toJson(user)
        sharedPreferences.edit(commit = true) {
            putString(USER_KEY, userJson)
        }
        return true
    }

    fun saveToken(token: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit(commit = true) {
            putString(TOKEN_KEY, token)
        }
    }

    fun getToken(): String? {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(TOKEN_KEY, null)
    }
}