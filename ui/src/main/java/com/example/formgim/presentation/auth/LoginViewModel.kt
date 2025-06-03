package com.example.formgim.presentation.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.formgim.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.android.identity.credential.CredentialRequest
import androidx.credentials.CustomCredential
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.viewModelScope
import com.appgim.domain.auth.usecases.GoogleSignInUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
//    private lateinit var credentialManager: CredentialManager
    private lateinit var signInUseCase : GoogleSignInUseCase

    init {
        // Initialize CredentialManager and SignInUseCase here
//        credentialManager = CredentialManager.create(context = this)
//        signInUseCase = GoogleSignInUseCase(/* provide repository */)
    }

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun signIn(context: Context, credentialManager: CredentialManager) {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
            .setAutoSelectEnabled(true)
            //Todo: add Nonce to improve security
//                .setNonce(<nonce string to use when generating a Google ID token>)
            .build()

        android.widget.Toast.makeText(context, "FUNCION Google Sign In clicked", android.widget.Toast.LENGTH_SHORT).show()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(context, request)
                signInUseCase.handleSignIn(result)
            } catch (e: GetCredentialException) {
                Log.e("SignInError", "GetCredentialException: ", e)
            }
        }
    }
}