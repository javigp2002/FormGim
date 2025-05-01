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

    fun updateAnswer(index: Int, answer: String) {
        val updatedList = _listOfQuestions.value.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.TextBox) {
            val updatedQuestion = QuestionTypes.TextBox(
                question.textBoxModel.copy(answer = answer)
            )
            updatedList[index] = updatedQuestion
            _listOfQuestions.value = updatedList
        }
    }

    fun updateSliderAnswer(index: Int, answer: Float) {
        val updatedList = _listOfQuestions.value.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.Slider) {
            updatedList[index] = QuestionTypes.Slider(
                question.sliderBoxModel.copy(answer = answer)
            )
            _listOfQuestions.value = updatedList
        }
    }

    fun updateSingleSelection(index: Int, selected: Int) {
        val updatedList = _listOfQuestions.value.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.SingleOption) {
            updatedList[index] = QuestionTypes.SingleOption(
                question.singleOptionModel.copy(seleccion = selected)
            )
            _listOfQuestions.value = updatedList
        }
    }

    fun updateMultipleSelection(index: Int, selected: Set<Int>) {
        val updatedList = _listOfQuestions.value.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.Multiple) {
            updatedList[index] = QuestionTypes.Multiple(
                question.multipleOptionModel.copy(seleccion = selected)
            )
            _listOfQuestions.value = updatedList
        }
    }

}