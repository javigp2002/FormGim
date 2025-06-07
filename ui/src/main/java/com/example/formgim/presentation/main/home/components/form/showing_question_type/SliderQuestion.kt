package com.example.formgim.presentation.main.home.components.form.showing_question_type

import QuestionDescriptionText
import QuestionWithResponses
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SliderQuestion(
    questionTitle: String, value: Float, onValorChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
    steps: Int = 9,
    enabled: Boolean = true
) {
    QuestionWithResponses(
        title = questionTitle,
    ) {

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
            QuestionDescriptionText(valueRange.start.toInt().toString())
            QuestionDescriptionText(value.toInt().toString())
            QuestionDescriptionText(valueRange.endInclusive.toInt().toString())
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