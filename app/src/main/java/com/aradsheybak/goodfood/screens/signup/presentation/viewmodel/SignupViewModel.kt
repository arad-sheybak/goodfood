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

    fun processSignup(signupIntent: SignupIntent) {
        when (signupIntent) {
            is SignupIntent.FirstNameChanged -> {
                _viewState.value = _viewState.value.copy(firstName = signupIntent.firstName)

            }

            is SignupIntent.LastNameChanged -> {
                _viewState.value = _viewState.value.copy(lastName = signupIntent.lastName)
            }

            is SignupIntent.EmailChanged -> {
                _viewState.value = _viewState.value.copy(email = signupIntent.email)
            }

            is SignupIntent.PasswordChanged -> {
                _viewState.value = _viewState.value.copy(password = signupIntent.password)
            }

            is SignupIntent.ConfirmPasswordChanged -> {
                _viewState.value =
                    _viewState.value.copy(confirmPassword = signupIntent.confirmPassword)
            }

            SignupIntent.SignupClicked -> {
                performSignup()
            }

            SignupIntent.ClearError -> {
                _viewState.value = _viewState.value.copy(error = null)
            }
        }
    }

    private fun performSignup() {
        val currentState = _viewState.value
        val firstName = currentState.firstName
        val lastName = currentState.lastName
        val email = currentState.email
        val password = currentState.password
        val confirmPassword = currentState.confirmPassword

        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _viewState.value = currentState.copy(error = "All the fields must be fill!")
            return
        } else {
            if (password != confirmPassword) {
                _viewState.value =
                    currentState.copy(error = "Password and Confirm password is no same!")
                return
            }
        }

        viewModelScope.launch(dispatchers.main) {
            _viewState.value = currentState.copy(isLoading = true, error = null)
            try {
                val result = withContext(dispatchers.io) {
                    val credentials = SignupCredentials(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        password = password
                    )
                    signupUseCase(credentials = credentials)
                }
                when (result) {
                    SignupResult.Failure.EmailAlreadyExists -> {
                        _viewState.value = _viewState.value.copy(
                            isLoading = false,
                            error = "Email is already exists!"
                        )
                    }

                    SignupResult.Failure.InvalidEmail -> {
                        _viewState.value =
                            _viewState.value.copy(
                                isLoading = false,
                                error = "Email is invalid!"
                            )

                    }

                    SignupResult.Failure.NetworkError -> {
                        _viewState.value = _viewState.value.copy(
                            isLoading = false,
                            error = "Network error. Please try again.!"
                        )
                    }

                    is SignupResult.Failure.Unknown -> {
                        _viewState.value = _viewState.value.copy(
                            isLoading = false,
                            error = result.throwable?.localizedMessage ?: "Unknown error"
                        )
                    }

                    SignupResult.Success -> {
                        _viewState.value =
                            _viewState.value.copy(isLoading = false, error = null)
                        _viewEffect.emit(SignupViewEffect.NavigateToLogin)

                    }
                }

            } catch (e: Exception) {
                _viewState.value = _viewState.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown exception"
                )

            }

        }
    }
}