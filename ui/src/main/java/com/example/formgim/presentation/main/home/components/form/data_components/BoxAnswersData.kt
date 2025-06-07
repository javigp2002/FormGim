package com.example.formgim.presentation.main.home.components.form.data_components

import QuestionDescriptionText
import QuestionWithResponses
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun BoxAnswersData(
    questionTitle: String,
    values: List<String>,
) {
    QuestionWithResponses(
        title = questionTitle,
    ) {
        values.forEach { value ->
            QuestionDescriptionText(value)
            Spacer(modifier = Modifier.height(Constants.PaddingSizes.S.dp))
        }
    }
}

@Preview
@Composable
fun BoxAnswersDataPreview() {
    BoxAnswersData(
        questionTitle = "Pregunta de ejemplo",
        values = listOf("Respuesta 1", "Respuesta 2", "Respuesta 3")
    )
}

