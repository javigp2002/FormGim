package com.appgim.domain.main.home.models.dataform

import com.appgim.domain.main.home.models.form.MultipleOptionModel
import com.appgim.domain.main.home.models.form.SingleOptionModel

sealed class QuestionTypesForDataForm(
    val id: Int,
    val text: String,
    val error: Boolean = false
) {
    data class TextBox(val textDataModel: TextDataModel) :
        QuestionTypesForDataForm(textDataModel.id, textDataModel.title)

    data class Slider(val sliderBoxDataModel: SliderBoxDataModel) :
        QuestionTypesForDataForm(sliderBoxDataModel.id, sliderBoxDataModel.question)

    data class Multiple(val multipleOptionModel: MultipleOptionModel) :
        QuestionTypesForDataForm(multipleOptionModel.id, multipleOptionModel.question, multipleOptionModel.error)

    data class SingleOption(val singleOptionModel: SingleOptionModel) :
        QuestionTypesForDataForm(singleOptionModel.id, singleOptionModel.question, singleOptionModel.error)
}
