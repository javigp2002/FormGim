package com.appgim.domain.main.home.repositories

import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.models.form.QuestionTypes

interface FormRepository {
    suspend fun getActiveForms(): Result<List<HomeFormCard>>

    suspend fun getListOfQuestionsFromForm(): List<QuestionTypes>
}