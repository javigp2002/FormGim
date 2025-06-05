package com.appgim.domain.main.home.usecases

import com.appgim.domain.auth.repositories.UserRepository
import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class GetFormAnswered @Inject constructor(
    private val formRepository: FormRepository,
    private val userRepository: UserRepository
) {
    suspend fun run(idForm: Int): FormData {
        val user = userRepository.getUser()
            ?: return FormData(id = idForm, title = "Error", description = "User not found", questions = emptyList())

        return formRepository.getFormAnsweredFromId(idForm, user.id)
    }
}