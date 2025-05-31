package com.example.formgim.presentation.main.home.admin.creation_form.states

import com.appgim.domain.main.home.models.form.QuestionTypes

data class ListCreationFormState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val isAddingNewQuestion: Boolean = false,
    val title: String = "",
    val description: String = "",
    val listQuestion: List<QuestionTypes> = emptyList<QuestionTypes>()
)
