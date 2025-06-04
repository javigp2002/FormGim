package com.appgim.data.repository

import com.appgim.data.api.BasicFormDto
import com.appgim.data.api.RetrofitClient.FormApi
import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.models.dataform.QuestionTypesForDataForm
import com.appgim.domain.main.home.models.dataform.SliderBoxDataModel
import com.appgim.domain.main.home.models.dataform.TextDataModel
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
                val newForms = FormApi.retrofitService.getNewForm(mapOf("userId" to 2))
                val homeFormCards = formsToHomeFormCard(newForms)

                Result.success(homeFormCards)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun getDoneForms(idUser: Int): Result<List<HomeFormCard>> =
        withContext(Dispatchers.IO) {
            try {
                val doneForms = FormApi.retrofitService.getDoneForm(mapOf("userId" to idUser))
                val homeFormCards = formsToHomeFormCard(doneForms)
                Result.success(homeFormCards)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun getAuthorForms(idUser: Int): Result<List<HomeFormCard>> =
        withContext(Dispatchers.IO) {
            try {
                val doneForms = FormApi.retrofitService.getAuthorForm(mapOf("userId" to idUser))
                val homeFormCards = formsToHomeFormCard(doneForms)
                Result.success(homeFormCards)
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

    override suspend fun getAnswersFromForm(formId: Int): List<QuestionTypesForDataForm> {
        return withContext(Dispatchers.IO) {
            listOf(
                QuestionTypesForDataForm.TextBox(
                    TextDataModel(
                        id = 1,
                        title = "¿Cuál es tu nombre?",
                        answers = listOf("Juan", "Ana")
                    )
                ),
                QuestionTypesForDataForm.Multiple(
                    MultipleOptionModel(
                        id = 2,
                        question = "¿Cuál es tu color favorito?",
                        opciones = listOf("Rojo", "Verde", "Azul"),
                        seleccion = setOf(1, 2)
                    )
                ),
                QuestionTypesForDataForm.SingleOption(
                    SingleOptionModel(
                        id = 3,
                        question = "¿Cuál es tu deporte favorito?",
                        opciones = listOf("Fútbol", "Baloncesto", "Tenis"),
                        seleccion = 1
                    )
                ),
                QuestionTypesForDataForm.Slider(
                    SliderBoxDataModel(
                        id = 4,
                        question = "¿Qué tan satisfecho estás con nuestro servicio?",
                        answers = listOf(10.0f, 80.0f, 60.0f, 90.0f, 100.0f)
                    )
                )
            )
        }
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

    private fun formsToHomeFormCard(forms: List<BasicFormDto>): List<HomeFormCard> {
        return forms.map { form ->
            HomeFormCard(
                id = form.id,
                title = form.title,
                author = form.authorName,
            )
        }
    }
}