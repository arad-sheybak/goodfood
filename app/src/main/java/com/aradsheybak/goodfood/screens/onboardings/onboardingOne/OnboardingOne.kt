package com.aradsheybak.goodfood.screens.onboardings.onboardingOne

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aradsheybak.goodfood.R
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange

@Composable
fun OnboardingPage1(
    onNext: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    onboardingOneContent()

}

@Composable
private fun onboardingOneContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
    ) {
        val (img, title, description) = createRefs()
        val guideline = createGuidelineFromTop(0.2f)

        Image(
            painter = painterResource(R.drawable.img_onboarding_one),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .constrainAs(img) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(R.string.title_onboarding_one),
            color = cream,
            fontSize = 32.sp,
            fontWeight = FontWeight.W900,
            fontFamily = lilita,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(img.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(text=stringResource(R.string.description_onboarding_one),
            color = cream,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = lilita,
            fontWeight = FontWeight.W300,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(description){
                    top.linkTo(title.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
    }
}

@Preview
@Composable
private fun preView() {
    onboardingOneContent()
}