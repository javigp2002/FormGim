package com.example.formgim.presentation.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.auth.usecases.GetCurrentUser
import com.appgim.domain.main.home.usecases.GetActiveForms
import com.example.formgim.presentation.main.profile.states.SettingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getActiveForms: GetActiveForms,
    private val getUserUsecase: GetCurrentUser
) : ViewModel() {
    private val _settings_state = MutableStateFlow(SettingState())
    val settingState: StateFlow<SettingState> = _settings_state.asStateFlow()

    init {
        getUserIsAdmin()
    }

    fun getUserIsAdmin() {
        viewModelScope.launch {
            val user = getUserUsecase.run()
            _settings_state.value = _settings_state.value.copy(
                user = user.getOrNull()
            )
        }
    }
}