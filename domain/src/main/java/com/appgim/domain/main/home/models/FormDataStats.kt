package com.appgim.domain.main.home.models

import com.appgim.domain.main.home.models.dataform.QuestionTypesForDataForm

data class FormDataStats(
    val title: String,
    val description: String,
    val formNumberOfFormsDone: Int,
    val questions: List<QuestionTypesForDataForm>,
    val id: Int? = null,
)
