package com.example.formgim.presentation.main.home.answered_form_screen.states

import com.appgim.domain.main.home.models.dataform.QuestionTypesForDataForm

data class DataFormState(
    var isLoading: Boolean = false,
    var error: Boolean = false,
    var forms: List<QuestionTypesForDataForm> = emptyList<QuestionTypesForDataForm>()
)
