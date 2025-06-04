package com.appgim.data.repository

import android.util.Log
import com.appgim.data.api.BasicFormDto
import com.appgim.data.api.RetrofitClient.FormApi
import com.appgim.data.api.dto.createJson
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
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor() : FormRepository {
    override suspend fun getActiveForms(idUser: Int): Result<List<HomeFormCard>> =
        withContext(Dispatchers.IO) {
            try {
                val newForms = FormApi.retrofitService.getNewForm(mapOf("userId" to idUser))
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


    @OptIn(InternalSerializationApi::class)
    override suspend fun getFormFromId(id: Int): FormData =
        withContext(Dispatchers.IO) {
            val result = FormApi.retrofitService.getForm(id)

            FormData(
                id = result.id,
                title = result.title,
                description = result.description,
                questions = result.questions.map { question ->
                    when (question.questionType) {
                        1 -> QuestionTypes.TextBox(TextBoxModel(id = question.id, title = question.title))
                        2 -> QuestionTypes.Multiple(
                            MultipleOptionModel(
                                id = question.id,
                                question = question.title,
                                opciones = question.options,
                            )
                        )

                        3 -> QuestionTypes.SingleOption(
                            SingleOptionModel(
                                id = question.id,
                                question = question.title,
                                opciones = question.options,
                            )
                        )

                        4 -> QuestionTypes.Slider(SliderBoxModel(id = question.id, question = question.title))
                        else -> throw IllegalArgumentException("Unknown question type: ${question.questionType}")
                    }
                }
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

    @OptIn(InternalSerializationApi::class)
    override suspend fun saveNewForm(formData: FormData): Boolean =
        withContext(Dispatchers.IO) {
            try {
                val formDataJson = createJson(formData)
                val result = FormApi.retrofitService.saveForm(formDataJson)
                return@withContext result
            } catch (e: Exception) {
                Log.e("JAVI", "Error saving form: ${e.message}")
                return@withContext false
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