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
    suspend fun getFormAnsweredFromId(id: Int, idUser: Int): FormData


    suspend fun sendAnswers(formId: Int, idUser: Int, answers: List<QuestionTypes>): Result<Boolean>

    suspend fun saveNewForm(formData: FormData): Boolean
}