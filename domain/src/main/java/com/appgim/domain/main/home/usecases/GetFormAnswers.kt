package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.FormDataStats
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class GetFormAnswers @Inject constructor(
    private val formRepository: FormRepository,
) {
    suspend fun run(idForm: Int): FormDataStats {

        return formRepository.getFormAnswers(idForm)
    }
}