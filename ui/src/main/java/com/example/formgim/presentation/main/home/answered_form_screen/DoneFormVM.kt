package com.example.formgim.presentation.main.home.answered_form_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.main.home.usecases.GetAnswersFromForm
import com.example.formgim.presentation.main.home.answered_form_screen.states.DataFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneFormVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getListOfAnswers: GetAnswersFromForm,
) : ViewModel() {
    private val formId: Int = savedStateHandle["formId"] ?: throw IllegalArgumentException("formId is required")

    private val _stateOfView = MutableStateFlow<DataFormState>(DataFormState())
    val stateOfView: StateFlow<DataFormState> = _stateOfView.asStateFlow()


    fun getListOfQuestionsFromFormId(id: Int) {
        viewModelScope.launch {
            getListOfAnswers.run(id).let { questions ->
                _stateOfView.value = _stateOfView.value.copy(forms = questions)
            }
        }
    }
}