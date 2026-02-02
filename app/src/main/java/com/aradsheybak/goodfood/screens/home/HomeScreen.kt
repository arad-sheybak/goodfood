package com.aradsheybak.goodfood.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.aradsheybak.goodfood.R
import com.aradsheybak.goodfood.components.CustomTextInput
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.darkGray
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange

@Composable
fun HomeScreen(navController: NavController) {

    HomeContent()
}

@Composable
@Preview
private fun HomeContent() {
    ConstraintLayout(
        modifier = Modifier
            .background(color = crimson)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        val (notificationBg, notificationImg, countTxt, clContent) = createRefs()
        Spacer(
            modifier = Modifier
                .size(45.dp)
                .background(color = Black.copy(alpha = 0.3f), shape = RoundedCornerShape(12.dp))
                .constrainAs(notificationBg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

        Image(
            painter = painterResource(R.drawable.ic_username),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = cream),
            modifier = Modifier
                .constrainAs(notificationImg) {
                    top.linkTo(notificationBg.top)
                    start.linkTo(notificationBg.start)
                    end.linkTo(notificationBg.end)
                    bottom.linkTo(notificationBg.bottom)
                }
        )

        Text(
            text = "3",
            fontSize = 15.sp,
            fontFamily = lilita,
            color = crimson,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .size(20.dp)
                .background(color = Green, shape = CircleShape)
                .constrainAs(countTxt) {
                    top.linkTo(notificationImg.top)
                    bottom.linkTo(notificationImg.top)
                    start.linkTo(notificationImg.end, margin = (-6).dp)
                }
        )


        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .constrainAs(clContent) {
                    top.linkTo(notificationBg.bottom, margin = 16.dp)
                }
        ) {
            val (searchBar, searchBtn,searchBtnBg, searchBg) = createRefs()
            var search by remember { mutableStateOf("") }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        color = darkGray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .constrainAs(searchBg) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            )

            CustomTextInput(
                value = search,
                onValueChange = { search = it },
                placeholder = R.string.search_what_to_eat,
                cornerRadius = 8.dp,
                fontSize = 20.sp,
                fontPlaceSize = 18.sp,
                fontFamily = lilita,
                keyboardType = KeyboardType.Text,
                singleLine = true,
                enabled = true,
                focusedBorderColor = Color.Transparent,
                focusedTextColor = cream,
                unfocusedBorderColor = Color.Transparent,
                modifier = Modifier
                    .constrainAs(searchBar) {
                        top.linkTo(searchBg.top)
                        bottom.linkTo(searchBg.bottom)
                        start.linkTo(searchBg.start)
                        end.linkTo(searchBtn.start)
                    }
            )


            Spacer(modifier = Modifier
                .size(50.dp)
                .background(color = orange, shape = RoundedCornerShape(12.dp))
                .constrainAs(searchBtnBg){
                    top.linkTo(searchBg.top)
                    bottom.linkTo(searchBg.bottom)
                    end.linkTo(searchBg.end)
                    start.linkTo(searchBar.end)
                }
            )

            Image(
                painter = painterResource(R.drawable.ic_retry),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = cream),
                modifier = Modifier
                    .padding(10.dp)
                    .constrainAs(searchBtn) {
                        top.linkTo(searchBtnBg.top)
                        start.linkTo(searchBtnBg.start)
                        end.linkTo(searchBtnBg.end)
                        bottom.linkTo(searchBtnBg.bottom)
                    })


        }

    }



}