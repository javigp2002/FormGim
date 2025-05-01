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

        is QuestionTypes.Multiple -> TODO()
        is QuestionTypes.SingleOption -> TODO()
        is QuestionTypes.Slider -> {
            SliderQuestion(
                questionTitle = questionType.sliderBoxModel.question,
                value = questionType.sliderBoxModel.answer,
                onValorChange = { questionType.sliderBoxModel.answer = it },
            )
        }
    }
}


