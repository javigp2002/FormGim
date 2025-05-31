package com.example.formgim.presentation.main.home.components.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun SliderQuestion(
    questionTitle: String, value: Float, onValorChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
    steps: Int = 9,
    enabled: Boolean = true
) {
    Column(modifier = Modifier.padding(Constants.PaddingSizes.L.dp)) {
        Text(text = questionTitle)
        Spacer(modifier = Modifier.height(Constants.PaddingSizes.M.dp))
        Slider(
            value = value,
            onValueChange = onValorChange,
            valueRange = valueRange,
            modifier = Modifier.fillMaxWidth(),
            steps = steps,
            enabled = enabled
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(valueRange.start.toInt().toString())
            Text(text = value.toInt().toString())
            Text(valueRange.endInclusive.toInt().toString())
        }
    }
}

@Preview
@Composable
fun SliderQuestionPreview() {
    SliderQuestion(
        questionTitle = "¿Cuanto te gusta el helado?",
        value = 50f,
        onValorChange = {}
    )
}