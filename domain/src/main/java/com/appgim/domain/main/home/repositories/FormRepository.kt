package com.appgim.domain.main.home.repositories

import com.appgim.domain.main.home.models.form.QuestionTypes

interface FormRepository {
    fun getListOfQuestionsFromForm(): List<QuestionTypes>
}