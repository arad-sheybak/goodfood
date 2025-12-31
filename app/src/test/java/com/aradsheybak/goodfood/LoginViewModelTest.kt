package com.aradsheybak.goodfood

import com.aradsheybak.goodfood.components.dispatcher.TestAppDispatchers
import com.aradsheybak.goodfood.screens.login.domain.usecase.LoginUseCase
import com.aradsheybak.goodfood.screens.login.presentation.contract.LoginIntent
import com.aradsheybak.goodfood.screens.login.presentation.viewmodel.LoginViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        val fakeUseCase = mockk<LoginUseCase>(relaxed = true)
        val testDispatchers = TestAppDispatchers()
        loginViewModel = LoginViewModel(
            loginUseCase = fakeUseCase,
            dispatchers = testDispatchers
        )
    }


    @Test
    fun `UsernameChanged updates  username in state`() {
        //when
        loginViewModel.processIntents(LoginIntent.UsernameChanged("test@gmail.com"))

        //then
        val state = loginViewModel.viewState.value
        assertEquals("test@gmail.com", state.username)
    }

}
