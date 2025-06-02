package com.example.formgim.presentation.main.home.answered_form_screen.states

import com.appgim.domain.main.home.models.form.QuestionTypes

data class ListFormState(
    var isLoading: Boolean = false,
    var error: Boolean = false,
    var forms: List<QuestionTypes> = emptyList<QuestionTypes>()
)
