package com.example.formgim.presentation.main.home.form_to_fill

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.usecases.GetListOfQuestionsFromForm
import com.appgim.domain.main.home.usecases.SendAnswers
import com.example.formgim.presentation.main.home.form_to_fill.states.ListFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormToFillVm @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getListOfQuestionsFromForm: GetListOfQuestionsFromForm,
    private val sendAnswers: SendAnswers,
) : ViewModel() {
    private val formId: Int = savedStateHandle["formId"] ?: throw IllegalArgumentException("formId is required")

    private val _stateOfView = MutableStateFlow<ListFormState>(ListFormState())
    val stateOfView: StateFlow<ListFormState> = _stateOfView.asStateFlow()


    init {
        _stateOfView.value = _stateOfView.value.copy(
            isLoading = true
        )
        getListOfQuestionsFromFormId(formId)
    }

    fun getListOfQuestionsFromFormId(id: Int) {
        viewModelScope.launch {
            getListOfQuestionsFromForm.run(id).let { questions ->
                _stateOfView.value = _stateOfView.value.copy(
                    forms = questions.questions,
                    isLoading = false
                )

            }

        }
    }

    fun updateAnswer(index: Int, answer: String) {
        val updatedList = _stateOfView.value.forms.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.TextBox) {
            val updatedQuestion = QuestionTypes.TextBox(
                question.textBoxModel.copy(answer = answer)
            )
            updatedList[index] = updatedQuestion
        }
        _stateOfView.value = _stateOfView.value.copy(
            forms = updatedList
        )
    }

    fun updateSliderAnswer(index: Int, answer: Float) {
        val updatedList = _stateOfView.value.forms.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.Slider) {
            updatedList[index] = QuestionTypes.Slider(
                question.sliderBoxModel.copy(answer = answer)
            )
        }
        _stateOfView.value = _stateOfView.value.copy(
            forms = updatedList
        )
    }

    fun updateSingleSelection(index: Int, selected: Int) {
        val updatedList = _stateOfView.value.forms.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.SingleOption) {
            updatedList[index] = QuestionTypes.SingleOption(
                question.singleOptionModel.copy(seleccion = selected)
            )
        }
        _stateOfView.value = _stateOfView.value.copy(
            forms = updatedList
        )
    }

    fun updateMultipleSelection(index: Int, selected: Set<Int>) {
        val updatedList = _stateOfView.value.forms.toMutableList()
        val question = updatedList[index]
        if (question is QuestionTypes.Multiple) {
            updatedList[index] = QuestionTypes.Multiple(
                question.multipleOptionModel.copy(seleccion = selected)
            )
        }
        _stateOfView.value = _stateOfView.value.copy(
            forms = updatedList
        )

    }

    fun submitValues() {
        viewModelScope.launch {
            if (!checkAnswers()) {
                _stateOfView.value = _stateOfView.value.copy(error = true)
                return@launch
            }

            val result = sendAnswers.run(formId = formId, _stateOfView.value.forms)

            if (result.isSuccess) {
                _stateOfView.value = _stateOfView.value.copy(
                    showAlertDialog = true,
                    error = false
                )
            } else {
                _stateOfView.value = _stateOfView.value.copy(
                    error = true,
                    showAlertDialog = false
                )
            }

            _stateOfView.value = _stateOfView.value.copy(showAlertDialog = true)
        }
    }

    fun checkAnswers(): Boolean {
        var ok = true
        val updatedList = _stateOfView.value.forms.toMutableList()

        _stateOfView.value.forms.forEachIndexed { index, question ->
            when (question) {
                is QuestionTypes.TextBox -> {
                    var error = false
                    if (question.textBoxModel.answer.isEmpty()) {
                        error = true
                        ok = false
                    }
                    val updatedQuestion = QuestionTypes.TextBox(
                        question.textBoxModel.copy(error = error)
                    )
                    updatedList[index] = updatedQuestion
                }

                is QuestionTypes.Slider -> {}
                is QuestionTypes.SingleOption -> {
                    var error = false
                    if (question.singleOptionModel.seleccion < 0 || question.singleOptionModel.seleccion > question
                            .singleOptionModel.opciones.size
                    ) {
                        error = true
                        ok = false
                    }
                    val updatedQuestion = QuestionTypes.SingleOption(
                        question.singleOptionModel.copy(error = error)
                    )
                    updatedList[index] = updatedQuestion
                }

                is QuestionTypes.Multiple -> {
                    var error = false
                    if (question.multipleOptionModel.seleccion.isEmpty()) {
                        error = true
                        ok = false
                    }
                    val updatedQuestion = QuestionTypes.Multiple(
                        question.multipleOptionModel.copy(error = error)
                    )
                    updatedList[index] = updatedQuestion
                }
            }
        }
        _stateOfView.value = _stateOfView.value.copy(
            forms = updatedList
        )
        return ok
    }


    fun dismissDialog() {
        _stateOfView.value = _stateOfView.value.copy(error = false)
    }
}