package com.appgim.data.api.dto.to_back

import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.models.form.QuestionTypes
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class FormDataJson(
    val title: String,
    val description: String,
    val questions: List<QuestionJson>
)

@InternalSerializationApi
@Serializable
data class QuestionJson(
    val title: String,
    val type: Int,
    val options: List<String>? = null
)


@OptIn(InternalSerializationApi::class)
fun createJson(formData: FormData): FormDataJson {
    val questions = formData.questions.map { question ->
        when (question) {
            is QuestionTypes.TextBox -> QuestionJson(
                title = question.text,
                type = 1,
            )

            is QuestionTypes.Slider -> QuestionJson(
                title = question.text,
                type = 4
            )

            is QuestionTypes.Multiple -> QuestionJson(
                title = question.text,
                type = 2,
                options = question.multipleOptionModel.opciones
            )

            is QuestionTypes.SingleOption -> QuestionJson(
                title = question.text,
                type = 3,
                options = question.singleOptionModel.opciones
            )
        }
    }

    val formDataJson = FormDataJson(
        title = formData.title,
        description = formData.description,
        questions = questions
    )

    return formDataJson
}