package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class SendAnswers @Inject constructor(
    private val formRepository: FormRepository
) {
    suspend fun run(formId: Int, answers: List<QuestionTypes>): Boolean {
        return formRepository.sendAnswers(formId, answers)
    }
}