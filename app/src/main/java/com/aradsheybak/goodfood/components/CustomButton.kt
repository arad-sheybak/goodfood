package com.aradsheybak.goodfood.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.lilita

@Composable

fun CustomButton(
    title: Int,
    modifier: Modifier,
    onClick: () -> Unit,
    textColor: Color = cream,
    fontSize: TextUnit,
    fontFamily: FontFamily? = lilita,
    containerColor: Color = crimson,

    ) {

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Text(
            text = stringResource(title),
            fontSize = fontSize,
            fontFamily = fontFamily,
            color = textColor
        )
    }
}