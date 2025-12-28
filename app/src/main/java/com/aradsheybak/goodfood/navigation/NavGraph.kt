package com.aradsheybak.goodfood.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aradsheybak.goodfood.screens.home.HomeScreen
import com.aradsheybak.goodfood.screens.login.presentation.ui.LoginScreen
import com.aradsheybak.goodfood.screens.onboardings.OnboardingScreenParent
import com.aradsheybak.goodfood.screens.signup.SignupScreen
import com.aradsheybak.goodfood.screens.spalsh.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.splash.route,
        modifier = modifier
    ) {

        composable(Screen.splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.onboarding.route) {
            OnboardingScreenParent(onFinish = {
                navController.navigate(Screen.login.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            })
        }

        composable(Screen.login.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.signup.route) {
            SignupScreen(navController=navController)
        }

    }


}