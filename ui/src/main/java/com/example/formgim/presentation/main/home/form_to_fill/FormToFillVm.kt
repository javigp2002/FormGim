package com.example.formgim.presentation.main.home.form_to_fill

import androidx.lifecycle.ViewModel
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.usecases.GetListOfQuestionsFromForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FormToFillVm @Inject constructor(
    private val getListOfQuestionsFromForm: GetListOfQuestionsFromForm
) : ViewModel() {
    private val _listOfQuestions = MutableStateFlow<List<QuestionTypes>>(emptyList())
    val listOfQuestions: StateFlow<List<QuestionTypes>> = _listOfQuestions.asStateFlow()


    fun getListOfQuestionsFromFormId(id: Int) {
        getListOfQuestionsFromForm.run(id).let { questions ->
            _listOfQuestions.value = questions
        }
    }
}