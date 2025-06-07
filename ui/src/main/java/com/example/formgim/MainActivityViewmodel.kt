package com.example.formgim

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.auth.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewmodel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _isUserAuth = MutableLiveData(true)
    val isUserAuth: LiveData<Boolean> = _isUserAuth

    init {
        viewModelScope.launch {
            authRepository.authState.collect { isAuthorized ->
                _isUserAuth.value = isAuthorized
            }
        }
    }
}