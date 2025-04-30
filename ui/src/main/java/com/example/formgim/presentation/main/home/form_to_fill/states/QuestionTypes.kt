package com.example.formgim.presentation.main.home.form_to_fill.states

import com.appgim.domain.main.home.models.form.MultipleOptionModel
import com.appgim.domain.main.home.models.form.SingleOptionModel
import com.appgim.domain.main.home.models.form.SliderBoxModel
import com.appgim.domain.main.home.models.form.TextBoxModel

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