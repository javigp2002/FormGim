package com.example.formgim.presentation.main.home.answered_form_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.main.home.usecases.GetListOfQuestionsFromForm
import com.example.formgim.presentation.main.home.form_to_fill.states.ListFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneFormVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getListOfQuestionsFromForm: GetListOfQuestionsFromForm,
) : ViewModel() {
    private val formId: Int = savedStateHandle["formId"] ?: throw IllegalArgumentException("formId is required")

    private val _stateOfView = MutableStateFlow<ListFormState>(ListFormState())
    val stateOfView: StateFlow<ListFormState> = _stateOfView.asStateFlow()


    fun getListOfQuestionsFromFormId(id: Int) {
        viewModelScope.launch {
            getListOfQuestionsFromForm.run(id).let { questions ->
                _stateOfView.value = _stateOfView.value.copy(forms = questions)
            }
        }
    }
}