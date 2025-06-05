package com.example.formgim.presentation.main.home.components.form.data_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    Column(modifier = Modifier.padding(Constants.PaddingSizes.L.dp)) {
        Text(
            text = questionTitle, style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(Constants.PaddingSizes.M.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Lista de Valores dados:", style = MaterialTheme.typography.bodyLarge)
                values.forEachIndexed { index, value ->
                    Text(
                        text = value.toInt().toString(),
                        modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Constants.PaddingSizes.M.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Media de Valores: ${"%.3f".format(values.average())}")

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