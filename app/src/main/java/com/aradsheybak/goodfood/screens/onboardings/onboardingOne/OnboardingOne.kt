package com.aradsheybak.goodfood.screens.onboardings.onboardingOne

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aradsheybak.goodfood.R
import com.aradsheybak.goodfood.ui.theme.orange
import com.aradsheybak.goodfood.ui.theme.semiBlack

@Composable
fun OnboardingPage1(
    onNext: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
){
    onboardingOneContent()

}

@Composable
private fun onboardingOneContent(){
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = orange)){
        val (img,title,description) = createRefs()
        Image(
            painter = painterResource(R.drawable.img_onboarding_one),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .constrainAs(img){
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        
        Text(text= stringResource(R.string.title_onboarding_one))
    }
}
@Preview
@Composable
private fun preView(){
    onboardingOneContent()
}