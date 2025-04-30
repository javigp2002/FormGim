package com.example.formgim.presentation.main.home.form_to_fill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.usecases.GetListOfQuestionsFromForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormToFillVm @Inject constructor(
    private val getListOfQuestionsFromForm: GetListOfQuestionsFromForm
) : ViewModel() {
    private val _listOfQuestions = MutableStateFlow<List<QuestionTypes>>(emptyList())
    val listOfQuestions: StateFlow<List<QuestionTypes>> = _listOfQuestions.asStateFlow()


    fun getListOfQuestionsFromFormId(id: Int) {
        viewModelScope.launch {
            getListOfQuestionsFromForm.run(id).let { questions ->
                _listOfQuestions.value = questions
            }
        }
    }

}