package com.aradsheybak.goodfood.signup

import com.aradsheybak.goodfood.components.dispatcher.TestAppDispatchers
import com.aradsheybak.goodfood.screens.signup.domain.usecase.SignupUseCase
import com.aradsheybak.goodfood.screens.signup.presentation.contract.SignupIntent
import com.aradsheybak.goodfood.screens.signup.presentation.viewmodel.SignupViewModel
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class SignupViewModelTest {
    private lateinit var signupViewModel: SignupViewModel
    private lateinit var fakeUsecase: SignupUseCase

    @Before
    fun setup() {
        fakeUsecase = mockk<SignupUseCase>(relaxed = true)
        val testDispatchers = TestAppDispatchers()
        signupViewModel = SignupViewModel(fakeUsecase, testDispatchers)
    }

    @Test
    fun `firstNameChanged update firstname in state`(){
        // given -> i expect to firstnameChanged update firstname in state

        //when
        signupViewModel.processSignup(SignupIntent.FirstNameChanged("Arad"))

        //then
        val state = signupViewModel.viewState.value
        TestCase.assertEquals("Arad",state.firstName)
    }

}