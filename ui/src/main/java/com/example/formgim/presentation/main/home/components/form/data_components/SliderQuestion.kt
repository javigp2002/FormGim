package com.example.formgim.presentation.main.home.components.form.data_components

import QuestionDescriptionText
import QuestionWithResponses
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun SliderData(
    questionTitle: String,
    values: List<Float>,
) {
    QuestionWithResponses(
        title = questionTitle,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Respuestas:", style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                values.forEachIndexed { index, value ->
                    QuestionDescriptionText(value.toInt().toString())
                }
            }

            VerticalDivider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(horizontal = Constants.PaddingSizes.M.dp)
                    .fillMaxHeight()
                    .height(40.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Media: \n${"%.2f".format(values.average())}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun SliderDataPreview() {
    SliderData(
        questionTitle = "Pregunta de Slider",
        values = listOf(1.0f, 2.5f, 3.0f, 4.5f, 5.0f)
    )
}