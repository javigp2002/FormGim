package com.appgim.domain.main.home.usecases

import com.appgim.domain.auth.repositories.UserRepository
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class SendAnswers @Inject constructor(
    private val formRepository: FormRepository,
    private val userRepository: UserRepository
) {
    suspend fun run(formId: Int, answers: List<QuestionTypes>): Result<Boolean> {
        val userId = userRepository.getUser()?.id ?: return Result.failure(Exception("User not found"))
        return formRepository.sendAnswers(formId, userId, answers)
    }
}