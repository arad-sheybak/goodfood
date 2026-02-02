package com.aradsheybak.goodfood.screens.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aradsheybak.goodfood.components.dispatcher.AppDispatchers
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupCredentials
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupResult
import com.aradsheybak.goodfood.screens.signup.domain.usecase.SignupUseCase
import com.aradsheybak.goodfood.screens.signup.presentation.contract.SignupIntent
import com.aradsheybak.goodfood.screens.signup.presentation.contract.SignupViewEffect
import com.aradsheybak.goodfood.screens.signup.presentation.contract.SignupViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(
    private val signupUseCase: SignupUseCase,
    private val dispatchers: AppDispatchers
) : ViewModel() {
    // --------------------- State ---------------------
    private val _viewState = MutableStateFlow(SignupViewState())
    val viewState: StateFlow<SignupViewState> = _viewState.asStateFlow()

    // --------------------- SideEffect ---------------------

    private val _viewEffect = MutableSharedFlow<SignupViewEffect>()
    val viewEffect: SharedFlow<SignupViewEffect> = _viewEffect.asSharedFlow()

    fun processIntent(intent: SignupIntent) {
        when (intent) {

            // ----------------- State-only Intents -----------------
            is SignupIntent.FirstNameChanged,
            is SignupIntent.LastNameChanged,
            is SignupIntent.EmailChanged,
            is SignupIntent.PasswordChanged,
            is SignupIntent.ConfirmPasswordChanged,
            is SignupIntent.ClearError -> {
                _viewState.update { reduce(it, intent) }
            }

            // ----------------- Async / SideEffect Intents -----------------
            SignupIntent.SignupClicked -> {
                performSignup()
            }
        }
    }

    // --------------------- REDUCER ---------------------
    private fun reduce(state: SignupViewState, intent: SignupIntent): SignupViewState {
        return when (intent) {
            is SignupIntent.FirstNameChanged -> state.copy(firstName = intent.firstName)
            is SignupIntent.LastNameChanged -> state.copy(lastName = intent.lastName)
            is SignupIntent.EmailChanged -> state.copy(email = intent.email)
            is SignupIntent.PasswordChanged -> state.copy(password = intent.password)
            is SignupIntent.ConfirmPasswordChanged -> state.copy(confirmPassword = intent.confirmPassword)
            is SignupIntent.ClearError -> state.copy(error = null)
            else -> state
        }
    }


    private fun performSignup() {
        val currentState = _viewState.value
        val firstName = currentState.firstName
        val lastName = currentState.lastName
        val email = currentState.email
        val password = currentState.password
        val confirmPassword = currentState.confirmPassword

        // Validation before async
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _viewState.update { it.copy(error = "All the fields must be fill!") }
            return
        }
        if (password != confirmPassword) {
            _viewState.update { it.copy(error = "Password and Confirm password do not match!") }
            return
        }

        viewModelScope.launch(dispatchers.main) {

            // Loading state
            _viewState.update { it.copy(isLoading = true, error = null) }

            try {
                val result = withContext(dispatchers.io) {
                    signupUseCase(
                        SignupCredentials(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            password = password
                        )
                    )
                }

                // Stop loading
                _viewState.update { it.copy(isLoading = false) }

                // Side effects
                when (result) {
                    SignupResult.Success -> _viewEffect.emit(SignupViewEffect.NavigateToLogin)
                    SignupResult.Failure.EmailAlreadyExists ->
                        _viewState.update { it.copy(error = "Email already exists!") }
                    SignupResult.Failure.InvalidEmail ->
                        _viewState.update { it.copy(error = "Email is invalid!") }
                    SignupResult.Failure.NetworkError ->
                        _viewState.update { it.copy(error = "Network error. Please try again!") }
                    is SignupResult.Failure.Unknown ->
                        _viewState.update { it.copy(error = result.throwable?.localizedMessage ?: "Unknown error") }
                }

            } catch (e: Exception) {
                _viewState.update { it.copy(isLoading = false, error = e.localizedMessage ?: "Unknown exception") }
            }
        }
    }
}