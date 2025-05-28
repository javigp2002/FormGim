package com.example.formgim.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.auth.models.UserModel
import com.appgim.domain.auth.usecases.GetCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewmodel @Inject constructor(
    private val getCurrentUser: GetCurrentUser,
) : ViewModel() {
    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin.asStateFlow()

    fun checkUser() {
        viewModelScope.launch {
            val result = getCurrentUser.run()
            if (result.isSuccess) {
                val user: UserModel? = result.getOrNull()
                _isAdmin.value = user?.isAdmin == true
            } else
                _isAdmin.value = false
        }
    }
}