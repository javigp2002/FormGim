package com.example.formgim.presentation.main.home.components.form.data_components

import QuestionDescriptionText
import QuestionWithResponses
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MultipleAnswerData(
    questionTitle: String,
    options: List<String>,
    selected: Map<Int, Int>,
    timesFormDone: Int,
) {
    QuestionWithResponses(
        title = questionTitle,
    ) {
        options.forEachIndexed { index, opcion ->
            val numOcurrences = selected[index] ?: 0

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selected.contains(index),
                    enabled = false,
                    onCheckedChange = {},
                )
                QuestionDescriptionText(opcion)
                Text(text = "$numOcurrences/$timesFormDone", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

