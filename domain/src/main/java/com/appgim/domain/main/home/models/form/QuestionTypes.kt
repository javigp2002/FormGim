package com.appgim.domain.main.home.models.form

sealed class QuestionTypes(
    val id: Int,
    val text: String,
    val error: Boolean = false
) {
    data class TextBox(val textBoxModel: TextBoxModel) :
        QuestionTypes(textBoxModel.id, textBoxModel.title, textBoxModel.error)

    data class Slider(val sliderBoxModel: SliderBoxModel) :
        QuestionTypes(sliderBoxModel.id, sliderBoxModel.question, sliderBoxModel.error)

    data class Multiple(val multipleOptionModel: MultipleOptionModel) :
        QuestionTypes(multipleOptionModel.id, multipleOptionModel.question, multipleOptionModel.error)

    data class SingleOption(val singleOptionModel: SingleOptionModel) :
        QuestionTypes(singleOptionModel.id, singleOptionModel.question, singleOptionModel.error)
}