package com.example.formgim.presentation.main.home.form_to_fill

import androidx.lifecycle.ViewModel
import com.example.formgim.presentation.main.home.form_to_fill.states.QuestionTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormToFillVm : ViewModel() {
    private val _listOfQuestions = MutableStateFlow<List<QuestionTypes>>(emptyList())
    val listOfQuestions: StateFlow<List<QuestionTypes>> = _listOfQuestions.asStateFlow()
}