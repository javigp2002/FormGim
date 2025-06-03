package com.example.formgim.presentation.main.home.components.form.data_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun BoxAnswersData(
    questionTitle: String,
    values: List<String>,
) {
    Column(modifier = Modifier.padding(Constants.PaddingSizes.L.dp)) {
        Text(
            text = questionTitle,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(Constants.PaddingSizes.M.dp))

        values.forEach { value ->
            Text(value)
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

