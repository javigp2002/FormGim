package com.appgim.domain.main.home.repositories

import com.appgim.domain.main.home.models.FormData
import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.models.dataform.QuestionTypesForDataForm
import com.appgim.domain.main.home.models.form.QuestionTypes

interface FormRepository {
    suspend fun getActiveForms(idUser: Int): Result<List<HomeFormCard>>
    suspend fun getDoneForms(idUser: Int): Result<List<HomeFormCard>>
    suspend fun getAuthorForms(idUser: Int): Result<List<HomeFormCard>>

    suspend fun getFormFromId(id: Int): FormData
    suspend fun getAnswersFromForm(idForm: Int): List<QuestionTypesForDataForm>

    suspend fun sendAnswers(formId: Int, answers: List<QuestionTypes>): Boolean

    suspend fun saveNewForm(formData: FormData): Boolean
}