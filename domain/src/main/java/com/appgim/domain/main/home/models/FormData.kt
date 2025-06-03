package com.appgim.domain.main.home.models

import com.appgim.domain.main.home.models.form.QuestionTypes

data class FormData(
    val title: String,
    val description: String,
    val questions: List<QuestionTypes>,
    val id: Int? = null,
)
