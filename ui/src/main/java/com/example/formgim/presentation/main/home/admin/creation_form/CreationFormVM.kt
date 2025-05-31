package com.example.formgim.presentation.main.home.admin.creation_form

import androidx.lifecycle.ViewModel
import com.appgim.domain.main.home.models.form.QuestionTypes
import com.appgim.domain.main.home.models.form.TypeQuestionCreationForm
import com.example.formgim.presentation.main.home.admin.creation_form.states.ListCreationFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreationFormVM @Inject constructor(
) : ViewModel() {
    private val _stateOfView = MutableStateFlow<ListCreationFormState>(ListCreationFormState())
    val stateOfView: StateFlow<ListCreationFormState> = _stateOfView.asStateFlow()


    fun onTitleChange(title: String) {
        _stateOfView.value = _stateOfView.value.copy(
            title = title
        )
    }

    fun onDescriptionChange(string: String) {
        _stateOfView.value = _stateOfView.value.copy(
            description = string
        )
    }

    fun onSaveClick(values: List<String>, questionTitle: String, type: TypeQuestionCreationForm) {
        val questionType = QuestionTypes.Creation.createQuestionType(
            id = -1, questionTitle = questionTitle,
            type = type, values = values
        )

        _stateOfView.value = _stateOfView.value.copy(listQuestion = _stateOfView.value.listQuestion + questionType)
    }
}