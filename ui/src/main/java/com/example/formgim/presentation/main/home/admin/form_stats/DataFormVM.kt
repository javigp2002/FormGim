package com.example.formgim.presentation.main.home.admin.form_stats

import androidx.lifecycle.ViewModel
import com.example.formgim.presentation.main.home.admin.creation_form.states.ListCreationFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DataFormVM @Inject constructor(

) : ViewModel() {
    private val _stateOfView = MutableStateFlow<ListCreationFormState>(ListCreationFormState())
    val stateOfView: StateFlow<ListCreationFormState> = _stateOfView.asStateFlow()


}