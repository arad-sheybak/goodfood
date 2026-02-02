package com.aradsheybak.goodfood.screens.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aradsheybak.goodfood.components.dispatcher.AppDispatchers
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginCredentials
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginResult
import com.aradsheybak.goodfood.screens.login.domain.usecase.LoginUseCase
import com.aradsheybak.goodfood.screens.login.presentation.contract.LoginIntent
import com.aradsheybak.goodfood.screens.login.presentation.contract.LoginViewEffect
import com.aradsheybak.goodfood.screens.login.presentation.contract.LoginViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val loginUseCase: LoginUseCase,private val dispatchers: AppDispatchers) : ViewModel() {

    // --------------------- State ---------------------
    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState> = _viewState.asStateFlow()


    // --------------------- SideEffect ---------------------
    private val _viewEffect = MutableSharedFlow<LoginViewEffect>()
    val viewEffect: SharedFlow<LoginViewEffect> = _viewEffect.asSharedFlow()


    //Handle Intents
    fun processIntent(intent: LoginIntent) {
        when (intent) {

            // ----------------- State-only Intents -----------------
            is LoginIntent.UsernameChanged,
            is LoginIntent.PasswordChanged,
            is LoginIntent.ClearError -> {
                _viewState.update { reduce(it, intent) }
            }

            // ----------------- SideEffect / Async Intents -----------------
            LoginIntent.SignupClicked -> {
                viewModelScope.launch {
                    _viewEffect.emit(LoginViewEffect.NavigateToSignup)
                }
            }

            LoginIntent.LoginClicked -> {
                performLogin() // async handler
            }
        }
    }
    // --------------------- REDUCER (PURE FUNCTION) ---------------------
    private fun reduce(state: LoginViewState, intent: LoginIntent): LoginViewState {
        return when (intent) {
            is LoginIntent.UsernameChanged -> state.copy(username = intent.username)
            is LoginIntent.PasswordChanged -> state.copy(password = intent.password)
            is LoginIntent.ClearError -> state.copy(error = null)
            else -> state
        }
    }

    private fun performLogin() {
        val currentState = _viewState.value
        val username = currentState.username
        val password = currentState.password

        if (username.isBlank() || password.isBlank()) {
            _viewState.update { it.copy(error = "Username and Password cannot be empty") }
            return
        }

        viewModelScope.launch(dispatchers.main) {

            // Show loading
            _viewState.update { it.copy(isLoading = true, error = null) }

            try {
                val result = withContext(dispatchers.io) {
                    loginUseCase(LoginCredentials(username, password))
                }

                // Stop loading
                _viewState.update { it.copy(isLoading = false) }

                when(result) {
                    is LoginResult.Success -> {
                        _viewEffect.emit(LoginViewEffect.NavigateToHome)
                    }
                    is LoginResult.Failure.NetworkError -> {
                        _viewState.update { it.copy(error = "Network error. Please try again.") }
                    }
                    is LoginResult.Failure.Unknown -> {
                        _viewState.update { it.copy(error = result.throwable?.localizedMessage ?: "Unknown error") }
                    }
                    is LoginResult.Failure.InvalidCredentials -> {
                        _viewState.update { it.copy(error = "Invalid Username or Password") }
                    }
                }

            } catch(e: Exception) {
                _viewState.update { it.copy(isLoading = false, error = e.localizedMessage ?: "Unknown exception") }
            }
        }
    }

}