package com.aradsheybak.goodfood.screens.spalsh

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.aradsheybak.goodfood.R
import com.aradsheybak.goodfood.data.datastore.rememberPreferencesManager
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange
import com.aradsheybak.goodfood.ui.theme.semiBlack
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(navController: NavController) {
    val preferencesManager = rememberPreferencesManager()

    val TIME_OUT_SPLASH = 3000L
    LaunchedEffect(Unit) {
        delay(TIME_OUT_SPLASH)
        val isFirstLaunch = preferencesManager.isFirstLaunch.first()
        val isLoggedIn = preferencesManager.isLoggedIn.first()

        when {
            isFirstLaunch -> {
                navController.navigate("onboarding") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            isLoggedIn -> {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            else -> {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }

        }
    }
    splashContent()
}

@Composable
private fun splashContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = orange
            )
    ) {
        val (logo, appName) = createRefs()

        Image(
            painter = painterResource(R.drawable.ic_logo_good_food),
            contentDescription = null,
            colorFilter = ColorFilter.tint(semiBlack),
            modifier = Modifier
                .constrainAs(logo) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })


        Text(
            text = stringResource(R.string.app_name),
            color = semiBlack,
            fontSize = 24.sp,
            fontFamily = lilita,
            modifier = Modifier.constrainAs(appName) {
                top.linkTo(logo.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

    }
}

@Preview
@Composable
private fun PreviewSplash() {
    splashContent()
}