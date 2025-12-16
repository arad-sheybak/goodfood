package com.aradsheybak.goodfood.screens.onboardings

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.aradsheybak.goodfood.screens.onboardings.onboardingOne.OnboardingPage1
import com.aradsheybak.goodfood.screens.onboardings.onboardingThree.OnboardingPage3
import com.aradsheybak.goodfood.screens.onboardings.onboardingTwo.OnboardingPage2

@Composable
fun OnboardingScreenParent(onFinish: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }

    Crossfade(targetState = currentPage) { page ->
        when (page) {
            0 -> OnboardingPage1(
                onNext = { currentPage++ },
                onBack = {},
                onFinish = { onFinish() }
            )

            1 -> OnboardingPage2(
                onNext = { currentPage++ },
                onBack = { currentPage-- },
                onFinish = {}
            )

            2 -> OnboardingPage3(onFinish = { onFinish() },
                onBack = { currentPage-- }
            )
        }
    }
}