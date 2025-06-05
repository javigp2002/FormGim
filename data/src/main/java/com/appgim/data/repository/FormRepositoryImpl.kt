package com.appgim.data.repository

import android.util.Log
import com.appgim.data.api.RetrofitClient.FormApi
import com.appgim.data.api.dto.from_back.BasicFormDto
import com.appgim.data.api.dto.to_back.SaveAnswersDto
import com.appgim.data.api.dto.to_back.SaveAnswersFromUserDto
import com.appgim.data.api.dto.to_back.createJson
import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.models.FormDataStats
import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.models.dataform.MultipleOptionStatsModel
import com.appgim.domain.main.home.models.dataform.QuestionTypesForDataForm
import com.appgim.domain.main.home.models.dataform.SingleOptionStatsModel
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

    @OptIn(InternalSerializationApi::class)
    override suspend fun getFormAnsweredFromId(id: Int, idUser: Int): FormData {
        try {
            val result = FormApi.retrofitService.getFormAnswered(id, idUser)
            return FormData(
                id = result.id,
                title = result.title,
                description = result.description,
                questions = result.questions.map { question ->
                    when (question.questionType) {
                        1 -> QuestionTypes.TextBox(
                            TextBoxModel(
                                id = question.id, title = question.title,
                                answer = question.answers.first()
                            )
                        )

                        2 -> QuestionTypes.Multiple(
                            MultipleOptionModel(
                                id = question.id,
                                question = question.title,
                                opciones = question.options,
                                seleccion = question.answers.map { question.options.indexOf(it) }.toSet()
                            )
                        )

                        3 -> QuestionTypes.SingleOption(
                            SingleOptionModel(
                                id = question.id,
                                question = question.title,
                                opciones = question.options,
                                seleccion = question.options.indexOf(question.answers.firstOrNull() ?: "")
                            )
                        )

                        4 -> QuestionTypes.Slider(
                            SliderBoxModel(
                                id = question.id, question = question.title,
                                answer = question.answers.firstOrNull()?.toFloatOrNull() ?: 0f
                            )
                        )

                        else -> throw IllegalArgumentException("Unknown question type: ${question.questionType}")
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("JAVI", "Error getting answered form: ${e.message}")
            return FormData(
                id = id,
                title = "Error al cargar el formulario",
                description = "No se pudo obtener el formulario respondido.",
                questions = emptyList()
            )
        }
    }

    @OptIn(InternalSerializationApi::class)
    override suspend fun getFormAnswers(id: Int): FormDataStats {
        try {
            val result = FormApi.retrofitService.getFormAnswers(id)
            return FormDataStats(
                id = result.id,
                title = result.title,
                description = result.description,
                formNumberOfFormsDone = result.timesFormDone,
                questions = result.questions.map { question ->
                    when (question.questionType) {
                        1 -> QuestionTypesForDataForm.TextBox(
                            TextDataModel(
                                id = question.id,
                                title = question.title,
                                answers = question.answers
                            )
                        )

                        2 -> QuestionTypesForDataForm.Multiple(
                            MultipleOptionStatsModel(
                                id = question.id,
                                question = question.title,
                                opciones = question.options,
                                seleccion = question.answers.groupingBy { question.options.indexOf(it) }.eachCount()
                            )
                        )

                        3 -> QuestionTypesForDataForm.SingleOption(
                            SingleOptionStatsModel(
                                id = question.id,
                                question = question.title,
                                opciones = question.options,
                                seleccion = question.answers.groupingBy { question.options.indexOf(it) }.eachCount()
                            )
                        )

                        4 -> QuestionTypesForDataForm.Slider(
                            SliderBoxDataModel(
                                id = question.id,
                                question = question.title,
                                answers = question.answers.mapNotNull { it.toFloatOrNull() }
                            )
                        )

                        else -> throw IllegalArgumentException("Unknown question type: ${question.questionType}")
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("JAVI", "Error getting answered form: ${e.message}")
            return FormDataStats(
                id = id,
                title = "Error al cargar el formulario",
                description = "No se pudo obtener el formulario respondido.",
                questions = emptyList(),
                formNumberOfFormsDone = 0
            )
        }
    }


    @OptIn(InternalSerializationApi::class)
    override suspend fun sendAnswers(formId: Int, idUser: Int, answers: List<QuestionTypes>): Result<Boolean> =
        withContext(Dispatchers.IO) {
            val answersData: MutableList<SaveAnswersDto> = mutableListOf()

            for (answersQuestionTypes in answers) {
                when (answersQuestionTypes) {
                    is QuestionTypes.TextBox -> {
                        answersData.add(
                            SaveAnswersDto(
                                idQuestion = answersQuestionTypes.textBoxModel.id,
                                answer = answersQuestionTypes.textBoxModel.answer
                            )
                        )
                    }

                    is QuestionTypes.Slider -> {
                        answersData.add(
                            SaveAnswersDto(
                                idQuestion = answersQuestionTypes.sliderBoxModel.id,
                                answer = answersQuestionTypes.sliderBoxModel.answer.toString()
                            )
                        )
                    }

                    is QuestionTypes.Multiple -> {
                        for (option in answersQuestionTypes.multipleOptionModel.seleccion) {
                            answersData.add(
                                SaveAnswersDto(
                                    idQuestion = answersQuestionTypes.multipleOptionModel.id,
                                    answer = answersQuestionTypes.multipleOptionModel.opciones[option]
                                )
                            )
                        }
                    }

                    is QuestionTypes.SingleOption -> {
                        answersData.add(
                            SaveAnswersDto(
                                idQuestion = answersQuestionTypes.singleOptionModel.id,
                                answer = answersQuestionTypes.singleOptionModel.opciones[answersQuestionTypes.singleOptionModel.seleccion]
                            )
                        )
                    }
                }
            }


            Result.success(
                FormApi.retrofitService.saveAnswers(
                    formId, SaveAnswersFromUserDto(
                        idUser = idUser,
                        answers = answersData
                    )
                )
            )
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