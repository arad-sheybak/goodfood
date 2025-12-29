package com.aradsheybak.goodfood.screens.signup.presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aradsheybak.goodfood.R
import com.aradsheybak.goodfood.components.CustomButton
import com.aradsheybak.goodfood.components.CustomTextInput
import com.aradsheybak.goodfood.navigation.Screen
import com.aradsheybak.goodfood.screens.signup.presentation.contract.SignupIntent
import com.aradsheybak.goodfood.screens.signup.presentation.contract.SignupViewEffect
import com.aradsheybak.goodfood.screens.signup.presentation.viewmodel.SignupViewModel
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupScreen(navController: NavController) {
    val signupViewModel: SignupViewModel = koinViewModel()
    val state by signupViewModel.viewState.collectAsStateWithLifecycle()
    val context: Context = LocalContext.current
    LaunchedEffect(Unit) {
        signupViewModel.viewEffect.collect { effect ->
            when (effect) {

                SignupViewEffect.NavigateToLogin -> {
                    navController.navigate(Screen.login.route) {
                        popUpTo(Screen.signup.route) {
                            inclusive = true
                        }
                    }
                }

                is SignupViewEffect.ShowErrorToast -> {
                    Toast.makeText(
                        context,
                        effect.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }


    ContentSignup(
        firstName = state.firstName,
        lastName = state.lastName,
        email = state.email,
        password = state.password,
        confirmPassword = state.confirmPassword,
        onFirstNameChanged = { value ->
            signupViewModel.processSignup(SignupIntent.FirstNameChanged(value))
        },
        onLastNameChanged = { value ->
            signupViewModel.processSignup(SignupIntent.LastNameChanged(value))
        },
        onEmailChanged = { value ->
            signupViewModel.processSignup(SignupIntent.EmailChanged(value))
        },
        onPasswordChanged = { value ->
            signupViewModel.processSignup(SignupIntent.PasswordChanged(value))

        },
        onConfirmPasswordChanged = { value ->
            signupViewModel.processSignup(SignupIntent.ConfirmPasswordChanged(value))

        },
        onSignupClicked = { signupViewModel.processSignup(SignupIntent.SignupClicked) }
    )

}

@Composable
private fun ContentSignup(
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    confirmPassword: String,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onSignupClicked: () -> Unit = {}
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .verticalScroll(rememberScrollState())
            .imePadding()

    ) {
        val (img_signup,
            createAccount,
            title_firstName,
            title_lastName,
            title_email,
            title_password,
            title_confirmPassword,
            input_firstName,
            input_lastName,
            input_email,
            input_password,
            input_confirmPassword,
            btnSignup) = createRefs()


        //logo
        Image(
            painter = painterResource(R.drawable.img_signup),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(
                    img_signup
                ) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        //signup text
        Text(
            text = stringResource(R.string.hint_signup),
            color = cream,
            fontSize = 36.sp,
            textAlign = TextAlign.Start,
            fontFamily = lilita,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(createAccount) {
                    top.linkTo(img_signup.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )
        //first name
        Text(
            text = stringResource(R.string.title_first_name),
            color = cream,
            fontFamily = lilita,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(title_firstName) {
                    top.linkTo(createAccount.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })

        CustomTextInput(
            value = firstName,
            onValueChange = onFirstNameChanged ,
            placeholder = (R.string.hint_first_name),
            keyboardType = KeyboardType.Text,
            focusedBorderColor = crimson,
            unfocusedBorderColor = cream,
            focusedTextColor = crimson,
            leadingIcon = R.drawable.ic_username,
            cornerRadius = 12.dp,
            fontPlaceSize = 14.sp,
            fontSize = 24.sp,
            fontFamily = lilita,
            singleLine = true,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(0.45f)
                .constrainAs(input_firstName) {
                    top.linkTo(title_firstName.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )
        //last name
        Text(
            text = stringResource(R.string.title_last_name),
            color = cream,
            fontFamily = lilita,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(title_lastName) {
                    top.linkTo(createAccount.bottom, margin = 16.dp)
                    start.linkTo(input_firstName.end, margin = 8.dp)
                })

        CustomTextInput(
            value = lastName,
            onValueChange =  onLastNameChanged ,
            placeholder = (R.string.hint_last_name),
            keyboardType = KeyboardType.Text,
            focusedBorderColor = crimson,
            unfocusedBorderColor = cream,
            focusedTextColor = crimson,
            leadingIcon = R.drawable.ic_username,
            cornerRadius = 12.dp,
            fontSize = 24.sp,
            fontPlaceSize = 14.sp,
            fontFamily = lilita,
            singleLine = true,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(0.45f)
                .constrainAs(input_lastName) {
                    top.linkTo(title_lastName.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )
        //email
        Text(
            text = stringResource(R.string.title_email),
            color = cream,
            fontFamily = lilita,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(title_email) {
                    top.linkTo(input_firstName.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
        CustomTextInput(
            value = email,
            onValueChange = onEmailChanged ,
            placeholder = (R.string.hint_email),
            keyboardType = KeyboardType.Email,
            focusedBorderColor = crimson,
            unfocusedBorderColor = cream,
            focusedTextColor = crimson,
            leadingIcon = R.drawable.ic_email,
            cornerRadius = 12.dp,
            fontPlaceSize = 14.sp,
            fontSize = 24.sp,
            fontFamily = lilita,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(input_email) {
                    top.linkTo(title_email.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        )

        //password
        Text(
            text = stringResource(R.string.title_password),
            color = cream,
            fontFamily = lilita,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(title_password) {
                    top.linkTo(input_email.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
        CustomTextInput(
            value = password,
            onValueChange = onPasswordChanged ,
            placeholder = (R.string.hint_password),
            keyboardType = KeyboardType.Password,
            focusedBorderColor = crimson,
            unfocusedBorderColor = cream,
            focusedTextColor = crimson,
            leadingIcon = R.drawable.ic_password,
            cornerRadius = 12.dp,
            fontPlaceSize = 14.sp,
            fontSize = 24.sp,
            fontFamily = lilita,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(input_password) {
                    top.linkTo(title_password.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        )

        //confirm password
        Text(
            text = stringResource(R.string.title_confirm_password),
            color = cream,
            fontFamily = lilita,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(title_confirmPassword) {
                    top.linkTo(input_password.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
        CustomTextInput(
            value = confirmPassword,
            onValueChange =  onConfirmPasswordChanged ,
            placeholder = (R.string.hint_confirm_password),
            keyboardType = KeyboardType.Password,
            focusedBorderColor = crimson,
            unfocusedBorderColor = cream,
            focusedTextColor = crimson,
            leadingIcon = R.drawable.ic_password,
            cornerRadius = 12.dp,
            fontPlaceSize = 14.sp,
            fontSize = 24.sp,
            fontFamily = lilita,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(input_confirmPassword) {
                    top.linkTo(title_confirmPassword.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        )

        // signup button
        CustomButton(
            title = R.string.title_signup,
            onClick = {
                onSignupClicked()
            },
            textColor = cream,
            fontSize = 26.sp,
            fontFamily = lilita,
            containerColor = crimson,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp)
                .constrainAs(btnSignup) {
                    top.linkTo(input_confirmPassword.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 50.dp)
                }

        )

    }
}

@Composable
@Preview
private fun Preview() {
    ContentSignup(
        firstName = "arad",
        lastName = "sheybak",
        email = "arad@gmail.com",
        password = "123",
        confirmPassword = "123",
        onFirstNameChanged = {},
        onLastNameChanged = {},
        onEmailChanged = {},
        onPasswordChanged = {},
        onConfirmPasswordChanged = {},
        onSignupClicked = {}
    )
}