package com.example.formgim.presentation.main.home.admin.creation_form

import androidx.lifecycle.ViewModel
import com.example.formgim.presentation.main.home.form_to_fill.states.ListFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreationFormVM @Inject constructor(
) : ViewModel() {
    private val _stateOfView = MutableStateFlow<ListFormState>(ListFormState())
    val stateOfView: StateFlow<ListFormState> = _stateOfView.asStateFlow()
}