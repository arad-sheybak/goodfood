package com.aradsheybak.goodfood.screens.onboardings.onboardingThree

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.aradsheybak.goodfood.data.datastore.rememberPreferencesManager
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange
import kotlinx.coroutines.launch

@Composable
fun OnboardingPage3(
    onFinish: () -> Unit,
    onBack: () -> Unit,
) {
    OnboardingThreeContent(onFinish = onFinish, onBack = onBack)
}

@Composable
private fun OnboardingThreeContent(
    onBack: () -> Unit = {},
    onFinish: () -> Unit = {}
) {
    val preferencesManager = rememberPreferencesManager()
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
    ) {
        val (img, title, description, skip, next, back, pageIndicators) = createRefs()
        val guideline = createGuidelineFromTop(0.2f)

        // image
        Image(
            painter = painterResource(R.drawable.img_onboarding_three),
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
            text = stringResource(R.string.title_onboarding_three),
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
            text = stringResource(R.string.description_onboarding_three),
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

        // finish button
        Button(
            onClick = {
                coroutineScope.launch {
                    preferencesManager.setFirstLaunch(false)
                }
                onFinish()
            },
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
                text = stringResource(R.string.finish),
                color = cream,
                fontFamily = lilita,
                fontSize = 20.sp
            )
        }

        //back button
        Button(
            onClick = { onBack() },
            enabled = true,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = crimson
            ),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(back) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        ) {
            Text(
                text = stringResource(R.string.back),
                color = cream,
                fontFamily = lilita,
                fontSize = 20.sp
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
                contentDescription = null
            )
            Icon(
                painter = painterResource(R.drawable.ic_circle),
                contentDescription = null
            )
            Icon(
                painter = painterResource(R.drawable.ic_circle),
                contentDescription = null,
                tint = crimson
            )


        }

    }
}

@Preview
@Composable
private fun PreView() {
    OnboardingThreeContent()
}