package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class GetListOfQuestionsFromForm @Inject constructor(
    private val formRepository: FormRepository
) {
    suspend fun run(idForm: Int): FormData {
        return formRepository.getFormFromId(idForm)
    }
}