package com.example.lpksmartcenter.viewmodel

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class ProfileUiState(
    val email: String = "",
    val displayName: String? = null,
    val isEmailVerified: Boolean = false,
    val provider: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedOut: Boolean = false
)

class ProfileViewModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        val user = auth.currentUser
        if (user != null) {
            val providerData = user.providerData
                .firstOrNull { it.providerId != "firebase" }
                ?.providerId ?: "Email/Hasło"

            _uiState.value = _uiState.value.copy(
                email = user.email ?: "",
                displayName = user.displayName,
                isEmailVerified = user.isEmailVerified,
                provider = when (providerData) {
                    "google.com" -> "Google"
                    "password" -> "Email/Hasło"
                    else -> providerData
                }
            )
        }
    }

    fun signOut(context: Context) {
        viewModelScope.launch {
            try {
                val credentialManager = CredentialManager.create(context)
                credentialManager.clearCredentialState(ClearCredentialStateRequest())

                auth.signOut()
                _uiState.value = _uiState.value.copy(isLoggedOut = true)
            } catch (e: Exception) {
                auth.signOut()
                _uiState.value = _uiState.value.copy(isLoggedOut = true)
            }
        }
    }

    fun deleteAccount(context: Context) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                val credentialManager = CredentialManager.create(context)
                credentialManager.clearCredentialState(ClearCredentialStateRequest())

                auth.currentUser?.delete()?.await()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoggedOut = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Błąd usuwania konta: ${e.message}"
                )
            }
        }
    }
}
