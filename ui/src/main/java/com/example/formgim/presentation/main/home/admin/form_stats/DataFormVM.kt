package com.example.formgim.presentation.main.home.admin.form_stats

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.main.home.usecases.GetFormAnswers
import com.example.formgim.presentation.main.home.admin.form_stats.states.ListDataFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataFormVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getFormAnswers: GetFormAnswers
) : ViewModel() {
    private val formId: Int = savedStateHandle["formId"] ?: throw IllegalArgumentException("formId is required")

    private val _stateOfView = MutableStateFlow<ListDataFormState>(ListDataFormState())
    val stateOfView: StateFlow<ListDataFormState> = _stateOfView.asStateFlow()

    init {
        _stateOfView.value = _stateOfView.value.copy(
            isLoading = true
        )
        getFormFromId(formId)
    }

    fun getFormFromId(id: Int) {
        viewModelScope.launch {
            getFormAnswers.run(id).let { form ->
                _stateOfView.value = _stateOfView.value.copy(
                    dataStats = form,
                    isLoading = false
                )
            }
        }
    }
}