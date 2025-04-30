package com.appgim.data.repository

import com.appgim.domain.main.home.models.form.MultipleOptionModel
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.models.form.SingleOptionModel
import com.appgim.domain.main.home.models.form.SliderBoxModel
import com.appgim.domain.main.home.models.form.TextBoxModel
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor() : FormRepository {
    override fun getListOfQuestionsFromForm(): List<QuestionTypes> {
        return listOf(
            QuestionTypes.Multiple(
                MultipleOptionModel(
                    id = 1,
                    question = "¿Cuál es tu color favorito?",
                    opciones = listOf("Rojo", "Verde", "Azul"),
                )
            ),
            QuestionTypes.TextBox(
                TextBoxModel(
                    id = 2,
                    title = "¿Cuál es tu nombre?",
                )
            ),
            QuestionTypes.SingleOption(
                SingleOptionModel(
                    id = 3,
                    question = "¿Cuál es tu deporte favorito?",
                    opciones = listOf("Fútbol", "Baloncesto", "Tenis"),
                )
            ),

            QuestionTypes.Slider(
                SliderBoxModel(
                    id = 4,
                    question = "¿Qué tan satisfecho estás con nuestro servicio?",
                ),
            )
        )
    }


}