package com.example.formgim.presentation.auth

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.auth.usecases.GoogleSignInUseCase
import com.example.formgim.BuildConfig
import com.example.formgim.presentation.auth.states.LoginState
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase : GoogleSignInUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun signIn(context: Context, credentialManager: CredentialManager) =
        viewModelScope.launch {
            var result = false
            val signInWithGoogleOption: GetSignInWithGoogleOption =
                GetSignInWithGoogleOption.Builder(BuildConfig.WEB_CLIENT_ID).build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(signInWithGoogleOption)
                .build()


            try {
                val credentials = credentialManager.getCredential(context, request)
                Log.e("JAVI", "Credentials: $credentials")
                result = signInUseCase.handleSignIn(credentials)
                Log.e("JAVI", "SignIn Result: $result")

            } catch (e: GetCredentialException) {
                Log.e("SignInError", "GetCredentialException: ", e)
            }

            _loginState.value = loginState.value.copy(
                isLoading = false,
                isSuccess = result,
                error = result
            )
        }
}