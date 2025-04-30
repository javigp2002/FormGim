package com.example.formgim.presentation.main.home.form_to_fill.states

import com.appgim.domain.main.home.models.form.QuestionTypes

data class ListFormState(
    var isLoading: Boolean = false,
    var error: String = "",
    var forms: List<QuestionTypes> = emptyList<QuestionTypes>()
)
