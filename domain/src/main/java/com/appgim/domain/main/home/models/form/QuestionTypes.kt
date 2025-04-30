package com.appgim.domain.main.home.models.form

sealed class QuestionTypes(
    val id: Int,
    val text: String
) {
    class TextBox(textBoxModel: TextBoxModel) : QuestionTypes(textBoxModel.id, textBoxModel.title)
    class Slider(sliderBoxModel: SliderBoxModel) :
        QuestionTypes(sliderBoxModel.id, sliderBoxModel.question)

    class Multiple(multipleOptionModel: MultipleOptionModel) :
        QuestionTypes(multipleOptionModel.id, multipleOptionModel.question)

    class SingleOption(singleOptionModel: SingleOptionModel) :
        QuestionTypes(singleOptionModel.id, singleOptionModel.question)
}