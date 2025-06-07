package com.example.formgim.presentation.auth

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgim.domain.auth.usecases.GoogleSignInUseCase
import com.example.formgim.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase : GoogleSignInUseCase
) : ViewModel() {
//    private lateinit var credentialManager: CredentialManager

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
//        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
//            .setFilterByAuthorizedAccounts(true)
//            .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
//            .setAutoSelectEnabled(true)
//            //Todo: add Nonce to improve security
////                .setNonce(<nonce string to use when generating a Google ID token>)
//            .build()

        val signInWithGoogleOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption.Builder(BuildConfig.WEB_CLIENT_ID).build()


        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(context, request)
                android.widget.Toast.makeText(context, "after get cred", android.widget.Toast.LENGTH_SHORT).show()

                signInUseCase.handleSignIn(result, context)
                Log.e("ism", result.toString())

            } catch (e: GetCredentialException) {
                Log.e("SignInError", "GetCredentialException: ", e)
            }
        }
    }
}