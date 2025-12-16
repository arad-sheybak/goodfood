package com.aradsheybak.goodfood.screens.onboardings.onboardingOne

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange

@Composable
fun OnboardingPage1(
    onNext: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    OnboardingOneContent(onNext=onNext,onFinish=onFinish)

}

@Composable
private fun OnboardingOneContent(  onNext: () -> Unit = {},
                                   onFinish: () -> Unit = {}) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
    ) {
        val (img, title, description, skip, next, pageIndicators) = createRefs()
        val guideline = createGuidelineFromTop(0.2f)

        // image
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

        // title
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

        // description
        Text(
            text = stringResource(R.string.description_onboarding_one),
            color = cream,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = lilita,
            fontWeight = FontWeight.W300,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(description) {
                    top.linkTo(title.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        // next button
        Button(
            onClick = {onNext()},
            enabled = true,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = crimson
            ),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(next) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        ) {
            Text(
                text = stringResource(R.string.next),
                color = cream,
                fontFamily = lilita,
                fontSize = 20.sp
            )
        }

        // skip button
        Button(
            onClick = {onFinish()},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(width = 1.dp, brush = SolidColor(crimson)),
            modifier = Modifier.constrainAs(skip) {
                top.linkTo(parent.top, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }) {
            Text(
                text = stringResource(R.string.skip),
                fontFamily = lilita,
                fontSize = 20.sp,
                color = cream,
            )
        }

        // 3 dot for showing current page
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(pageIndicators) {
                    bottom.linkTo(next.bottom)
                    top.linkTo(next.top)
                }) {
            Icon(
                painter = painterResource(R.drawable.ic_circle),
                contentDescription = null,
                tint = crimson
            )
            Icon(painter = painterResource(R.drawable.ic_circle), contentDescription = null)
            Icon(painter = painterResource(R.drawable.ic_circle), contentDescription = null)


        }

    }
}

@Preview
@Composable
private fun PreView() {
    OnboardingOneContent()
}