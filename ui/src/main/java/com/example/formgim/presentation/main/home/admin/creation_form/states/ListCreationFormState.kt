package com.example.formgim.presentation.main.home.admin.creation_form.states

import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.models.form.QuestionTypes

data class ListCreationFormState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val isAddingNewQuestion: Boolean = true,
    val form: FormData = FormData(
        title = "",
        description = "",
        questions = emptyList<QuestionTypes>()
    ),

    val showAlertDialog: Boolean = false,
    val hasBeenSavedProperly: Boolean = false
)
