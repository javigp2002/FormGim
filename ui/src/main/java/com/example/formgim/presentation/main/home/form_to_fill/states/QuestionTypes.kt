package com.example.formgim.presentation.main.home.form_to_fill.states

import com.appgim.domain.main.home.models.form.TextBoxModel

sealed class QuestionTypes(
    val id: Int,
    val text: String
) {
    class TextBox(textBoxModel: TextBoxModel) : QuestionTypes(textBoxModel.id, textBoxModel.title)
}