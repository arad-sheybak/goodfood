package com.aradsheybak.goodfood.components.connectivity

import android.content.Intent
import android.provider.Settings
import android.provider.Settings.ACTION_WIFI_SETTINGS
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.aradsheybak.goodfood.R
import com.aradsheybak.goodfood.components.CustomButton
import com.aradsheybak.goodfood.ui.theme.cream
import com.aradsheybak.goodfood.ui.theme.crimson
import com.aradsheybak.goodfood.ui.theme.lilita
import com.aradsheybak.goodfood.ui.theme.orange

@Composable
fun NetworkDialog(
    isConnected: Boolean,
    onDismiss: () -> Unit = {}
) {
    val context = LocalContext.current

    if (!isConnected) {
        Dialog(
            onDismissRequest = {
                if (isConnected) {
                    onDismiss()
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(20.dp),
                color = orange,
                shadowElevation = 8.dp
            ) {
                ConstraintLayout(modifier = Modifier.padding(24.dp)) {
                    val (imgLogo, titleNoInternet, messageNoInternet, btnEnableWifi, btnEnableData) = createRefs()

                    Image(
                        painter = painterResource(R.drawable.ic_no_internet),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)
                            .background(
                                color = cream.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                            .constrainAs(imgLogo) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = stringResource(R.string.title_no_internet),
                        color = crimson,
                        fontFamily = lilita,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(titleNoInternet) {
                                top.linkTo(imgLogo.bottom, margin = 8.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = stringResource(R.string.no_internet_message),
                        color = cream,
                        fontFamily = lilita,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(messageNoInternet) {
                                top.linkTo(titleNoInternet.bottom, margin = 8.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    CustomButton(
                        title = R.string.enable_wifi,
                        onClick = {
                            val i = Intent(ACTION_WIFI_SETTINGS)
                            context.startActivity(i)
                        },
                        fontSize = 16.sp,
                        fontFamily = lilita,
                        modifier = Modifier
                            .fillMaxWidth(0.44f)
                            .constrainAs(btnEnableWifi) {
                            top.linkTo(messageNoInternet.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(btnEnableData.start)
                        }
                    )
                    CustomButton(
                        title = R.string.enable_mobile_data,
                        onClick = {
                            val networkIntent = Intent(Settings.ACTION_DATA_USAGE_SETTINGS)
                            if (networkIntent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(networkIntent)
                            } else {
                                val settingsIntent = Intent(Settings.ACTION_SETTINGS)
                                context.startActivity(settingsIntent)
                            }},
                        fontSize = 14.sp,
                        fontFamily = lilita,
                        modifier = Modifier
                            .fillMaxWidth(0.44f)
                            .constrainAs(btnEnableData) {
                            top.linkTo(messageNoInternet.bottom, margin = 16.dp)
                            end.linkTo(parent.end)
                            start.linkTo(btnEnableWifi.end)
                        }
                    )


                }

            }
        }
    }
}
