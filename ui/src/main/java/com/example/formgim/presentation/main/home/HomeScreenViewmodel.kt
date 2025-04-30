package com.example.formgim.presentation.main.home

import androidx.lifecycle.ViewModel
import com.example.formgim.presentation.main.home.states.ListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewmodel : ViewModel() {
    private val _listFormsState = MutableStateFlow(ListState())
    val listFormsState: StateFlow<ListState> = _listFormsState.asStateFlow()
}