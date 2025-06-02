package com.example.formgim.presentation.main.home.components.form.data_components

import androidx.compose.runtime.Composable
import com.example.formgim.presentation.main.home.components.form.showing_question_type.SingleOptionAnswerQuestion

@Composable
fun RadioData(
    questionTitle: String,
    options: List<String>,
    selection: Int,
) {
    SingleOptionAnswerQuestion(
        questionTitle = questionTitle,
        options = options,
        selection = selection,
        onSeleccionChange = {},
        isError = false,
        readonly = false
    )
}

