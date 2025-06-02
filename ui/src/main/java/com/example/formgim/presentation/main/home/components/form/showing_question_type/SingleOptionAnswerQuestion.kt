package com.example.formgim.presentation.main.home.components.form.showing_question_type

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
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
    enabled: Boolean = true
) {
    Column(modifier = Modifier.padding(Constants.PaddingSizes.L.dp)) {
        Text(text = questionTitle)
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
                    enabled = enabled
                )
                Text(text = opcion)
            }
        }
    }
}

