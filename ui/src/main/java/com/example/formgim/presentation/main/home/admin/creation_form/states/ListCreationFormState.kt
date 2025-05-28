package com.example.formgim.presentation.main.home.admin.creation_form.states

data class ListCreationFormState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val title: String = "",
    val description: String = "",
)
