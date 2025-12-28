package com.aradsheybak.goodfood.screens.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    // --------------------- State ---------------------
    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState> = _viewState.asStateFlow()


    // --------------------- SideEffect ---------------------
    private val _viewEffect = MutableSharedFlow<LoginViewEffect>()
    val viewEffect: SharedFlow<LoginViewEffect> = _viewEffect.asSharedFlow()


    //Handle Intents
    fun processIntents(loginIntent: LoginIntent) {
        when (loginIntent) {
            is LoginIntent.UsernameChanged -> {
                _viewState.value = _viewState.value.copy(username = loginIntent.username)
            }

            is LoginIntent.PasswordChanged -> {
                _viewState.value = _viewState.value.copy(password = loginIntent.password)
            }

            is LoginIntent.SignupClicked -> {
                viewModelScope.launch {
                    _viewEffect.emit(LoginViewEffect.NavigateToSignup)
                }
            }

            is LoginIntent.LoginClicked -> {
                performLogin()

            }

            is LoginIntent.ClearError -> {
                _viewState.value = _viewState.value.copy(error = null)
            }

            else -> {}
        }
    }

    private fun performLogin() {
        val currentState = _viewState.value
        val username = currentState.username
        val password = currentState.password


        if (username.isBlank() || password.isBlank()) {
            _viewState.value = currentState.copy(error = "Username and password cannot be empty")
            return
        }

        viewModelScope.launch {
            _viewState.value = currentState.copy(isLoading = true, error = null)
            try {
                val credentials = LoginCredentials(username = username, password = password)
                val result = loginUseCase(credentials)
                when(result){
                    is LoginResult.Success -> {
                        _viewState.value=_viewState.value.copy(isLoading = false)
                        _viewEffect.emit(LoginViewEffect.NavigateToHome)
                    }
                    is LoginResult.Failure.NetworkError -> {
                        _viewState.value = _viewState.value.copy(
                            isLoading = false,
                            error = "Network error. Please try again."
                        )
                    }
                    is LoginResult.Failure.Unknown -> {
                        _viewState.value = _viewState.value.copy(
                            isLoading = false,
                            error = result.throwable?.localizedMessage ?: "Unknown error"
                        )
                    }
                    is LoginResult.Failure.InvalidCredentials -> {
                        _viewState.value = _viewState.value.copy(isLoading = false, error = "Invalid Username or Password")
                    }
                }
            } catch (e: Exception) {
                _viewState.value = _viewState.value.copy(isLoading = false, error = e.localizedMessage ?: "Unknown exception")
            }
        }

    }
}