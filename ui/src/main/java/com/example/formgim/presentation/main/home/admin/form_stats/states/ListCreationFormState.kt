package com.example.formgim.presentation.main.home.admin.form_stats.states

import com.appgim.domain.main.home.models.form.QuestionTypes

data class ListDataFormState(
    val isLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val listQuestion: List<QuestionTypes> = emptyList<QuestionTypes>(),

    val showAlertDialog: Boolean = false,
    val hasBeenSavedProperly: Boolean = false
)
