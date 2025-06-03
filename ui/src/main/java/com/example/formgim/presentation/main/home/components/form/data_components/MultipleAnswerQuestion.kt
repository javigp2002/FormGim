package com.example.formgim.presentation.main.home.components.form.data_components

import androidx.compose.runtime.Composable
import com.example.formgim.presentation.main.home.components.form.showing_question_type.MultipleOptionQuestion

@Composable
fun MultipleAnswerData(
    questionTitle: String,
    options: List<String>,
    selected: Set<Int>,
) {
    MultipleOptionQuestion(
        questionTitle = questionTitle,
        options = options,
        selected = selected,
        onSeleccionChange = {},
        isError = false,
        enabled = false
    )
}

