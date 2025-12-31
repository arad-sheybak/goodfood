package com.aradsheybak.goodfood

import com.aradsheybak.goodfood.components.dispatcher.TestAppDispatchers
import com.aradsheybak.goodfood.screens.login.domain.entity.AuthenticatedUser
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginResult
import com.aradsheybak.goodfood.screens.login.domain.usecase.LoginUseCase
import com.aradsheybak.goodfood.screens.login.presentation.contract.LoginIntent
import com.aradsheybak.goodfood.screens.login.presentation.contract.LoginViewEffect
import com.aradsheybak.goodfood.screens.login.presentation.viewmodel.LoginViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var fakeUseCase: LoginUseCase

    @Before
    fun setup() {
        fakeUseCase = mockk<LoginUseCase>(relaxed = true)
        val testDispatchers = TestAppDispatchers()
        loginViewModel = LoginViewModel(
            loginUseCase = fakeUseCase,
            dispatchers = testDispatchers
        )
    }


    @Test
    fun `UsernameChanged updates username in state`() {
        //when
        loginViewModel.processIntents(LoginIntent.UsernameChanged("test@gmail.com"))

        //then
        val state = loginViewModel.viewState.value
        assertEquals("test@gmail.com", state.username)
    }

    @Test
    fun `PasswordChanged updates password in state`() {
        //when
        loginViewModel.processIntents(LoginIntent.PasswordChanged("Abc123456"))

        //then
        val state = loginViewModel.viewState.value
        assertEquals("Abc123456", state.password)

    }

    @Test
    fun `Error cleared in state`() {
        //when
        loginViewModel.processIntents(LoginIntent.ClearError)

        //then
        val state = loginViewModel.viewState.value
        assertEquals(null, state.error)

    }

    @Test
    fun `LoginClicked with empty fields sets error and does not call loginUseCase`() {
        //given -> what i expect
        // username=""
        // password=""

        //when
        loginViewModel.processIntents(LoginIntent.LoginClicked)

        //then
        val state = loginViewModel.viewState.value
        assertEquals("Username and Password cannot be empty", state.error)
        assertEquals(false, state.isLoading)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `LoginClicked with valid credentials emits NavigateToHome`() = runTest {
        val fakeUser = AuthenticatedUser(
            id = 1,
            firstName = "test",
            lastName = "test",
            email = "test@gmail.com",
            profileImageUrl = null,
            authToken = "auth123456789"
        )

        //Given -> what i expected
        coEvery {
            fakeUseCase.invoke(any())
        } returns LoginResult.Success(
            user = fakeUser
        )

        loginViewModel.processIntents(
            LoginIntent.UsernameChanged("test@gmail.com")
        )
        loginViewModel.processIntents(LoginIntent.PasswordChanged("123456"))

        val effects = mutableListOf<LoginViewEffect>()
        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            loginViewModel.viewEffect.collect {
                effects.add(it)
            }
        }

        //when
        loginViewModel.processIntents(LoginIntent.LoginClicked)
        advanceUntilIdle()

        //then
        coVerify(exactly = 1) {
            fakeUseCase.invoke(
                credentials = any()
            )
        }

        val state = loginViewModel.viewState.value
        assertEquals(false, state.isLoading)
        assertEquals(null, state.error)

        assertTrue(effects.contains(LoginViewEffect.NavigateToHome))

        job.cancel()
    }
}
