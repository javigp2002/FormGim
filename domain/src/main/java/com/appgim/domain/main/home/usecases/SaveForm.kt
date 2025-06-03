package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class SaveForm @Inject constructor(
    private val formRepository: FormRepository
) {
    suspend fun run(formData: FormData): Boolean {
        return formRepository.saveNewForm(formData)
    }
}