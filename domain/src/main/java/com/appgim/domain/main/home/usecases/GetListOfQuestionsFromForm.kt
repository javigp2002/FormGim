package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class GetListOfQuestionsFromForm @Inject constructor(
    private val formRepository: FormRepository
) {
    fun run(idForm: Int): List<QuestionTypes> {
        return formRepository.getListOfQuestionsFromForm()
    }
}