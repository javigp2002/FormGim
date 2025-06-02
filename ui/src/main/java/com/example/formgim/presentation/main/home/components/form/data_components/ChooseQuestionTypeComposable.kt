package com.example.formgim.presentation.main.home.components.form.data_components

import androidx.compose.runtime.Composable
import com.appgim.domain.main.home.models.dataform.QuestionTypesForDataForm

@Composable
fun ChooseDataComposable(
    questionType: QuestionTypesForDataForm,
) {
    when (questionType) {
        is QuestionTypesForDataForm.TextBox -> {
            BoxAnswersData(
                questionTitle = questionType.textDataModel.title,
                values = questionType.textDataModel.answers
            )
        }

        is QuestionTypesForDataForm.Multiple -> {
            MultipleAnswerData(
                questionTitle = questionType.multipleOptionModel.question,
                options = questionType.multipleOptionModel.opciones,
                selected = questionType.multipleOptionModel.seleccion,
            )
        }

        is QuestionTypesForDataForm.SingleOption -> {
            RadioData(
                questionTitle = questionType.singleOptionModel.question,
                options = questionType.singleOptionModel.opciones,
                selection = questionType.singleOptionModel.seleccion,
            )
        }

        is QuestionTypesForDataForm.Slider -> {
            SliderData(
                questionTitle = questionType.sliderBoxDataModel.question,
                values = questionType.sliderBoxDataModel.answers,
            )
        }
    }
}


