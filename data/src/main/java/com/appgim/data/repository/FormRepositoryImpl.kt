package com.appgim.data.repository

import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.models.form.MultipleOptionModel
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.models.form.SingleOptionModel
import com.appgim.domain.main.home.models.form.SliderBoxModel
import com.appgim.domain.main.home.models.form.TextBoxModel
import com.appgim.domain.main.home.repositories.FormRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor() : FormRepository {
    override suspend fun getActiveForms(): Result<List<HomeFormCard>> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(
                    listOf(
                        HomeFormCard(
                            id = 1,
                            title = "Encuesta de satisfacción",
                            author = "Woser Woser?",
                        ),
                        HomeFormCard(
                            id = 2,
                            title = "Registro de usuario",
                            author = "Juan Pérez",
                        ),
                    )
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun getListOfQuestionsFromForm(): List<QuestionTypes> =
        withContext(Dispatchers.IO) {
            listOf(
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

    override suspend fun sendAnswers(formId: Int, answers: List<QuestionTypes>): Boolean =
        withContext(Dispatchers.IO) {
            println("Sending answers: $answers")

            true
        }

    override suspend fun saveNewForm(formData: FormData): Boolean {
        return withContext(Dispatchers.IO) {
            println("Saving new form: $formData")
            // Here you would typically save the form data to a database or remote server
            true // Return true if the save operation was successful
        }
    }
}