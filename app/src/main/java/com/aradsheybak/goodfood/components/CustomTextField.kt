package com.aradsheybak.goodfood.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.lilita

@Composable
fun CustomTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: Int? = null,
    placeholder: Int? = null,
    leadingIcon: Int? = null,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 20.sp,
    fontFamily: FontFamily = lilita,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isError: Boolean = false,
    enabled: Boolean = true,
    focusedBorderColor : Color = crimson,
    unfocusedBorderColor: Color = cream,
    focusedTextColor: Color = cream

) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label?.let {
            {
                Text(
                    text = stringResource(it),
                    fontFamily = lilita,
                    color = cream
                )
            }
        },
        placeholder = placeholder?.let {
            {
                Text(
                    stringResource(it),
                    fontFamily = lilita,
                    color = cream
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine,
        shape = RoundedCornerShape(cornerRadius),
        textStyle = TextStyle.Default.copy(fontSize = fontSize, fontFamily = fontFamily),
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null, tint = cream
                )
            }
        },
        isError = isError,

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            focusedTextColor = focusedTextColor

        ),
        modifier = modifier,
        enabled = enabled
    )


}