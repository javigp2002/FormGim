package com.example.formgim.presentation.main.home.answered_form_screen.states

import com.appgim.domain.main.home.models.FormData

data class DataFormState(
    var isLoading: Boolean = false,
    var error: Boolean = false,
    val form: FormData = FormData(title = "", description = "", questions = emptyList()),
)
