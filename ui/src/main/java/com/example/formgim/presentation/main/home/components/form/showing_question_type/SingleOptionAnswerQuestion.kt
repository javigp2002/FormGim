package com.example.formgim.presentation.main.home.components.form.showing_question_type

import QuestionDescriptionText
import QuestionWithResponses
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun SingleOptionAnswerQuestion(
    questionTitle: String,
    options: List<String>,
    selection: Int,
    onSeleccionChange: (Int) -> Unit,
    isError: Boolean,
    readonly: Boolean = true
) {
    QuestionWithResponses(
        title = questionTitle,
    ) {
        Spacer(modifier = Modifier.height(Constants.PaddingSizes.M.dp))
        options.forEachIndexed { index, opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selection == index,
                    onClick = { onSeleccionChange(index) },
                    colors = if (isError) {
                        RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.error,
                            unselectedColor = MaterialTheme.colorScheme.error
                        )
                    } else {
                        RadioButtonDefaults.colors()
                    },
                    enabled = !readonly
                )
                QuestionDescriptionText(opcion)
            }
        }
    }
}

