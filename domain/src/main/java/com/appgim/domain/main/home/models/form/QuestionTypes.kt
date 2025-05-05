package com.appgim.domain.main.home.models.form

sealed class QuestionTypes(
    val id: Int,
    val text: String
) {
    data class TextBox(val textBoxModel: TextBoxModel) :
        QuestionTypes(textBoxModel.id, textBoxModel.title)

    data class Slider(val sliderBoxModel: SliderBoxModel) :
        QuestionTypes(sliderBoxModel.id, sliderBoxModel.question)

    data class Multiple(val multipleOptionModel: MultipleOptionModel) :
        QuestionTypes(multipleOptionModel.id, multipleOptionModel.question)

    data class SingleOption(val singleOptionModel: SingleOptionModel) :
        QuestionTypes(singleOptionModel.id, singleOptionModel.question)
}