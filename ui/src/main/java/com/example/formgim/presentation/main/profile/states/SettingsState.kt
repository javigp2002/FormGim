package com.example.formgim.presentation.main.profile.states

import com.appgim.domain.auth.models.UserModel

data class SettingState(
    val isLoading: Boolean = false,
    val error: String = "",
    val user: UserModel? = null,
)
