package com.example.formgim.presentation.main.home.form_to_fill.states

import com.appgim.domain.main.home.models.FormData

data class ListFormState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val form: FormData = FormData(
        title = "",
        description = "",
        questions = emptyList(),
    ),
    val showAlertDialog: Boolean = false,
)
