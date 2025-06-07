package com.example.formgim.presentation.main.home.components.form.showing_question_type

import MyOutlinedTextField
import QuestionWithResponses
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.formgim.R

@Composable
fun BoxQuestion(
    questionTitle: String, value: String, onTextoChange: (String) -> Unit, isError: Boolean,
    readonly: Boolean = true
) {
    QuestionWithResponses(
        title = questionTitle,
    ) {
        MyOutlinedTextField(
            value = value,
            onValueChange = onTextoChange,
            label = { Text(stringResource(R.string.cajaTexto)) },
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            readonly = readonly,
        )
    }
}

