package com.aradsheybak.goodfood.screens.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.aradsheybak.goodfood.R
import com.aradsheybak.goodfood.components.CustomButton
import com.aradsheybak.goodfood.components.CustomTextInput
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange

@Composable
fun LoginScreen(navController: NavController) {
    ContentLogin()
}

@Composable
private fun ContentLogin() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    ConstraintLayout(

        modifier = Modifier
            .fillMaxSize()
               .background(color = orange)
    ) {
        val (imgLogin,
            welcome,
            titleUsername,
            titlePassword,
            usernameInput,
            passwordInput,
            signup,
            btnLogin) = createRefs()

        Image(
            painter = painterResource(R.drawable.img_login),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(imgLogin) {
                    top.linkTo(parent.top, margin = 36.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Text(
            text = stringResource(R.string.login_hint),
            color = cream,
            fontSize = 36.sp,
            textAlign = TextAlign.Start,
            fontFamily = lilita,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(welcome) {
                    top.linkTo(imgLogin.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Text(
            text = stringResource(R.string.title_username),
            color = cream,
            fontFamily = lilita,
            fontSize = 24.sp,
            modifier = Modifier
                .constrainAs(titleUsername) {
                    top.linkTo(welcome.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })

        CustomTextInput(
            value = username,
            onValueChange = { username = it },
            placeholder = (R.string.hint_username),
            keyboardType = KeyboardType.Email,
            focusedBorderColor = crimson,
            unfocusedBorderColor = cream,
            focusedTextColor = crimson,
            leadingIcon = R.drawable.ic_username,
            cornerRadius = 12.dp,
            fontSize = 20.sp,
            fontFamily = lilita,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(usernameInput) {
                    top.linkTo(titleUsername.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(R.string.title_password),
            color = cream,
            fontFamily = lilita,
            fontSize = 24.sp,
            modifier = Modifier
                .constrainAs(titlePassword) {
                    top.linkTo(usernameInput.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
        CustomTextInput(
            value = password,
            onValueChange = { password = it },
            placeholder = (R.string.hint_password),
            keyboardType = KeyboardType.Password,
            focusedBorderColor = crimson,
            unfocusedBorderColor = cream,
            focusedTextColor = crimson,
            leadingIcon = R.drawable.ic_password,
            cornerRadius = 12.dp,
            fontSize = 20.sp,
            fontFamily = lilita,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(passwordInput) {
                    top.linkTo(titlePassword.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CustomButton(
            title = R.string.title_login,
            onClick = {},
            textColor = cream,
            fontSize = 26.sp,
            fontFamily = lilita,
            containerColor = crimson,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp)
                .constrainAs(btnLogin) {
                top.linkTo(passwordInput.bottom, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        )


    }
}

@Composable
@Preview
private fun Preview() {
    ContentLogin()
}