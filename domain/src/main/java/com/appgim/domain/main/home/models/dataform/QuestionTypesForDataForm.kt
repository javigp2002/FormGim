package com.appgim.domain.main.home.models.dataform

sealed class QuestionTypesForDataForm(
    val id: Int,
    val text: String,
    val error: Boolean = false
) {
    data class TextBox(val textDataModel: TextDataModel) :
        QuestionTypesForDataForm(textDataModel.id, textDataModel.title)

    data class Slider(val sliderBoxDataModel: SliderBoxDataModel) :
        QuestionTypesForDataForm(sliderBoxDataModel.id, sliderBoxDataModel.question)

    data class Multiple(val multipleOptionModel: MultipleOptionStatsModel) :
        QuestionTypesForDataForm(multipleOptionModel.id, multipleOptionModel.question)

    data class SingleOption(val singleOptionModel: SingleOptionStatsModel) :
        QuestionTypesForDataForm(singleOptionModel.id, singleOptionModel.question)
}
