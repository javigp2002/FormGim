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
        is QuestionTypes.Slider -> TODO()
    }
}


