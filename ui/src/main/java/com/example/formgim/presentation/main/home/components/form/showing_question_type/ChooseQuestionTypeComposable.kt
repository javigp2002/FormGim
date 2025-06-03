package com.example.formgim.presentation.main.home.components.form.showing_question_type

import androidx.compose.runtime.Composable
import com.appgim.domain.main.home.models.form.QuestionTypes

@Composable
fun ChooseQuestionTypeComposable(
    questionType: QuestionTypes,
    onAnswerChanged: (String) -> Unit = {},
    onMultipleChanged: (Set<Int>) -> Unit = {},
    onSingleChanged: (Int) -> Unit = {},
    onSliderChanged: (Float) -> Unit = {},
    readonly: Boolean = false
) {
    when (questionType) {
        is QuestionTypes.TextBox -> {
            BoxQuestion(
                questionTitle = questionType.textBoxModel.title,
                value = questionType.textBoxModel.answer,
                onTextoChange = onAnswerChanged,
                isError = questionType.textBoxModel.error,
                readonly = readonly
            )
        }

        is QuestionTypes.Multiple -> {
            MultipleOptionQuestion(
                questionTitle = questionType.multipleOptionModel.question,
                options = questionType.multipleOptionModel.opciones,
                selected = questionType.multipleOptionModel.seleccion,
                onSeleccionChange = onMultipleChanged,
                isError = questionType.multipleOptionModel.error,
                enabled = !readonly
            )
        }
        is QuestionTypes.SingleOption -> {
            SingleOptionAnswerQuestion(
                questionTitle = questionType.singleOptionModel.question,
                options = questionType.singleOptionModel.opciones,
                selection = questionType.singleOptionModel.seleccion,
                onSeleccionChange = onSingleChanged,
                isError = questionType.singleOptionModel.error,
                readonly = readonly
            )
        }
        is QuestionTypes.Slider -> {
            SliderQuestion(
                questionTitle = questionType.sliderBoxModel.question,
                value = questionType.sliderBoxModel.answer,
                onValorChange = onSliderChanged,
                enabled = !readonly,
            )
        }
    }
}


