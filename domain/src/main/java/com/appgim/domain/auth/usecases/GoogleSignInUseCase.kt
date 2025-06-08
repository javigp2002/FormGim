package com.appgim.domain.auth.usecases

import android.content.ContentValues.TAG
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.appgim.domain.auth.repositories.AuthRepository
import com.appgim.domain.auth.repositories.UserRepository
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    suspend fun handleSignIn(result: GetCredentialResponse): Boolean {
        val credential : Credential = result.credential

        when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        return signInWithServer(googleIdTokenCredential.idToken)
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

        return false
    }

    private suspend fun signInWithServer(googleToken: String): Boolean {
        val response = authRepository.signInWithServer(googleToken).getOrElse {
            return false
        }

        userRepository.saveUser(response)
        userRepository.saveToken(googleToken)

        return true
    }
}