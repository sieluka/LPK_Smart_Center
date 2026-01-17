package com.example.lpksmartcenter.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
)

class LoginViewModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null,
            errorMessage = null
        )
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null,
            errorMessage = null
        )
    }

    private fun validateEmail(): Boolean {
        val email = _uiState.value.email
        return when {
            email.isBlank() -> {
                _uiState.value = _uiState.value.copy(emailError = "Email jest wymagany")
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _uiState.value = _uiState.value.copy(emailError = "Nieprawidłowy format email")
                false
            }
            else -> true
        }
    }

    private fun validatePassword(): Boolean {
        val password = _uiState.value.password
        return when {
            password.isBlank() -> {
                _uiState.value = _uiState.value.copy(passwordError = "Hasło jest wymagane")
                false
            }
            password.length < 6 -> {
                _uiState.value = _uiState.value.copy(passwordError = "Hasło musi mieć minimum 6 znaków")
                false
            }
            else -> true
        }
    }

    fun signIn() {
        if (!validateEmail() || !validatePassword()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                auth.signInWithEmailAndPassword(_uiState.value.email, _uiState.value.password).await()
                _uiState.value = _uiState.value.copy(isLoading = false, isLoginSuccessful = true)
            } catch (e: Exception) {
                val errorMsg = when (e) {
                    is FirebaseAuthInvalidUserException -> "Nieprawidłowy email lub hasło"
                    is FirebaseAuthInvalidCredentialsException -> "Nieprawidłowy email lub hasło"
                    else -> "Błąd logowania: ${e.message}"
                }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = errorMsg
                )
            }
        }
    }

    fun signUp() {
        if (!validateEmail() || !validatePassword()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                val result = auth.createUserWithEmailAndPassword(_uiState.value.email, _uiState.value.password).await()

                // Wyślij email weryfikacyjny
                result.user?.sendEmailVerification()?.await()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoginSuccessful = true,
                    errorMessage = "Konto utworzone. Sprawdź email i potwierdź adres."
                )
            } catch (e: Exception) {
                val errorMsg = when (e) {
                    is FirebaseAuthWeakPasswordException -> "Hasło jest zbyt słabe"
                    is FirebaseAuthInvalidCredentialsException -> "Nieprawidłowy format email"
                    is FirebaseAuthUserCollisionException -> "Użytkownik z tym emailem już istnieje"
                    else -> "Błąd rejestracji: ${e.message}"
                }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = errorMsg
                )
            }
        }
    }

    fun resetPassword() {
        if (!validateEmail()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                auth.sendPasswordResetEmail(_uiState.value.email).await()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Link do resetowania hasła został wysłany na email"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Błąd wysyłania emaila: ${e.message}"
                )
            }
        }
    }

    fun signInWithGoogle(idToken: String?) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                if (idToken == null) {
                    throw Exception("Token ID jest pusty")
                }

                val credential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(credential).await()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoginSuccessful = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Błąd logowania Google: ${e.message}"
                )
            }
        }
    }

    fun resetLoginSuccess() {
        _uiState.value = _uiState.value.copy(isLoginSuccessful = false)
    }
}