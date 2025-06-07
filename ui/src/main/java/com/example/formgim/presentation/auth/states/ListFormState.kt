package com.example.formgim.presentation.auth.states

data class LoginState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val isSuccess: Boolean = false,
)
