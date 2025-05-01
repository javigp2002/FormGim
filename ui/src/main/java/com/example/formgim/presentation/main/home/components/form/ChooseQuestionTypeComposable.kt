package com.example.formgim.presentation.main.home.components.form

import androidx.compose.runtime.Composable
import com.appgim.domain.main.home.models.form.QuestionTypes

@Composable
fun ChooseQuestionTypeComposable(questionType: QuestionTypes) {
    when (questionType) {
        is QuestionTypes.TextBox -> {
            BoxQuestion(
                questionTitle = questionType.textBoxModel.title,
                value = questionType.textBoxModel.answer,
                onTextoChange = { questionType.textBoxModel.answer = it }
            )
        }

        is QuestionTypes.Multiple -> {
            MultipleOptionQuestion(
                questionTitle = questionType.multipleOptionModel.question,
                options = questionType.multipleOptionModel.opciones,
                selected = questionType.multipleOptionModel.seleccion,
                onSeleccionChange = { questionType.multipleOptionModel.seleccion = it },
            )
        }
        is QuestionTypes.SingleOption -> {
            SingleOptionAnswerQuestion(
                questionTitle = questionType.singleOptionModel.question,
                options = questionType.singleOptionModel.opciones,
                selection = questionType.singleOptionModel.seleccion,
                onSeleccionChange = { questionType.singleOptionModel.seleccion = it },
            )
        }
        is QuestionTypes.Slider -> {
            SliderQuestion(
                questionTitle = questionType.sliderBoxModel.question,
                value = questionType.sliderBoxModel.answer,
                onValorChange = { questionType.sliderBoxModel.answer = it },
            )
        }
    }
}


