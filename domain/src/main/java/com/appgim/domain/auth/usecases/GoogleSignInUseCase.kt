package com.appgim.domain.auth.usecases

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.appgim.domain.auth.repositories.AuthRepository
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException

import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun handleSignIn(result: GetCredentialResponse, context: Context) {
        val credential : Credential = result.credential

        when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        signInWithServer(googleIdTokenCredential.idToken)

                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                }
                else {
                    // Catch any unrecognized credential type here.
                    Log.e(TAG, "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e(TAG, "Unexpected type of credential")
            }
        }
    }

    private suspend fun signInWithServer(googleToken: String){
        val response = authRepository.signInWithServer(googleToken).getOrElse {throwable ->
            throw RuntimeException("Error en el backend", throwable)
        }

        val backendResponse = response
        Log.d("AuthViewModel", "Respuesta del backend: $backendResponse")

        // todo: Guarda el backendResponse.sessionToken de forma segura

    }
}