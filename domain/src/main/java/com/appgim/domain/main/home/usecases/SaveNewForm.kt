package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class SaveNewForm @Inject constructor(
    private val formRepository: FormRepository
) {
    suspend fun run(model: FormData): List<QuestionTypes> {
        return formRepository.getListOfQuestionsFromForm()
    }
}