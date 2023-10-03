package com.example.dropy.ui.screens.servicesDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dropy.R

@Composable
fun ServicesDialog(
    onDismissDialog: ((Boolean) -> Unit)? = null,
    onClicked: ((String) -> Unit)? = null
) {
    Dialog(
        onDismissRequest = {
            if (onDismissDialog != null) {
                onDismissDialog(false)
            }
        },
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, RoundedCornerShape(14.dp))
                .padding(vertical = 14.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            Text(
                text = "Register as",
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.5).sp,
                lineHeight = 17.sp,
                modifier = Modifier
                    .fillMaxWidth()


            )
            Column(
                modifier = Modifier.clickable {
                    if (onClicked != null) {
                        onClicked("Customer")
                    }
                }
            ) {
                Text(
                    text = "Customer",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.5).sp,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()

                )

                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .height(1.dp)
                        .background(Color.Black)
                ) {

                }
            }

            Column(
                modifier = Modifier.clickable {
                    if (onClicked != null) {
                        onClicked("Logistics Truck Express")
                    }
                }
            ) {
                Text(
                    text = "Logistics Truck Express",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.5).sp,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .height(1.dp)
                        .background(Color.Black)
                ) {

                }
            }


            Column(
                modifier = Modifier.clickable {
                    if (onClicked != null) {
                        onClicked("TukTuk Rider")
                    }
                }
            ) {
                Text(
                    text = "TukTuk Rider",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.5).sp,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .height(1.dp)
                        .background(Color.Black)
                ) {

                }
            }

            Column(
                modifier = Modifier.clickable {
                    if (onClicked != null) {
                        onClicked("Riders Express")
                    }
                }
            ) {
                Text(
                    text = "Riders Express",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.5).sp,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .height(1.dp)
                        .background(Color.Black)
                ) {

                }
            }

            Column(
                modifier = Modifier.clickable {
                    if (onClicked != null) {
                        onClicked("Couriers Express")
                    }
                }
            ) {
                Text(
                    text = "Couriers Express",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.5).sp,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()

                )

                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .height(1.dp)
                        .background(Color.Black)
                ) {

                }
            }
            Text(
                text = "Logistics Truck Drivers",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.5).sp,
                lineHeight = 17.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (onClicked != null) {
                            onClicked("Logistics Truck Drivers")
                        }
                    }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun demo() {
    ServicesDialog()
}