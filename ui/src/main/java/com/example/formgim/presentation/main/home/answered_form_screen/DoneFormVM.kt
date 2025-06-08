package com.example.formgim.presentation.main.home.answered_form_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.main.home.usecases.GetFormAnsweredByUser
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
    private val getListOfAnswers: GetFormAnsweredByUser,
) : ViewModel() {
    private val formId: Int = savedStateHandle["formId"] ?: throw IllegalArgumentException("formId is required")

    private val _stateOfView = MutableStateFlow<DataFormState>(DataFormState())
    val stateOfView: StateFlow<DataFormState> = _stateOfView.asStateFlow()


    init {
        _stateOfView.value = _stateOfView.value.copy(
            isLoading = true
        )
        getFormFromId(formId)
    }

    fun getFormFromId(id: Int) {
        viewModelScope.launch {
            getListOfAnswers.run(id).let { form ->
                _stateOfView.value = _stateOfView.value.copy(
                    form = form,
                    isLoading = false
                )
            }
        }
    }
}