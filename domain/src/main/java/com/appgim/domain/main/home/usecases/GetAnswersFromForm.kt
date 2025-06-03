package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.dataform.QuestionTypesForDataForm
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class GetAnswersFromForm @Inject constructor(
    private val formRepository: FormRepository
) {
    suspend fun run(idForm: Int): List<QuestionTypesForDataForm> {
        return formRepository.getAnswersFromForm(idForm)
    }
}