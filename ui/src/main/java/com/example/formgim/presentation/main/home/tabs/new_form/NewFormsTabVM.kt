package com.example.formgim.presentation.main.home.tabs.new_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.usecases.GetActiveForms
import com.example.formgim.presentation.main.home.states.ListHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewFormsTabVM @Inject constructor(
    private val getActiveForms: GetActiveForms,
) : ViewModel() {
    private val _listFormsState = MutableStateFlow(ListHomeState())
    val listFormsState: StateFlow<ListHomeState> = _listFormsState.asStateFlow()

    fun updateListState() {
        viewModelScope.launch {
            val listFormCards: List<HomeFormCard> = getActiveForms.run()
            _listFormsState.value = _listFormsState.value.copy(
                forms = listFormCards,
            )
        }
    }

}