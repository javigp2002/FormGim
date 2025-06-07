package com.example.formgim.presentation.main.home.components.form.showing_question_type

import QuestionDescriptionText
import QuestionWithResponses
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MultipleOptionQuestion(
    questionTitle: String,
    options: List<String>,
    selected: Set<Int>,
    onSeleccionChange: (Set<Int>) -> Unit,
    isError: Boolean,
    enabled: Boolean = true
) {
    QuestionWithResponses(
        title = questionTitle,
    ) {
        options.forEachIndexed { index, opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selected.contains(index),
                    onCheckedChange = {
                        val nuevaSeleccion = selected.toMutableSet()
                        if (it) nuevaSeleccion.add(index) else nuevaSeleccion.remove(index)
                        onSeleccionChange(nuevaSeleccion)
                    },

                    colors = if (isError) {
                        CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.error,
                            uncheckedColor = MaterialTheme.colorScheme.error
                        )
                    } else {
                        CheckboxDefaults.colors()
                    },
                    enabled = enabled
                )
                QuestionDescriptionText(opcion)
            }
        }
    }
}

