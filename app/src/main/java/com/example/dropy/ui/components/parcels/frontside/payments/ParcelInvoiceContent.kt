package com.example.dropymain.presentation.components.parcels

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.ProfilePic
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ParcelInvoiceContent(){
    Column(
        modifier = Modifier
            .padding(bottom = 48.dp)
            .fillMaxHeight()
            .fillMaxWidth()
        ,
    ){
        StyledText(
            textColor = Color.Black,
            backgroundColor = DropyYellow,
            borderColor = Color.Transparent,
            textSize = 15,
            text = "package details",
            isUppercase = true,
            isBold =true
        )
        ParcelInvoice()
    }
}
@Composable
fun ParcelInvoice(){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
        ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
                            ) {
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            ) {
                                SimpleText(
                                    textSize = 15,
                                    text = "package type",
                                    textColor = Color.Black,
                                    isUppercase = true,
                                    isBold = true,
                                    isExtraBold = false,
                                    padding = 0
                                )
                                SimpleText(
                                    textSize = 12,
                                    text = "large : 3-10kg",
                                    textColor = Color.DarkGray,
                                    isUppercase = true,
                                    isBold = false,
                                    isExtraBold = false,
                                    padding = 0
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            ) {
                                SimpleText(
                                    textSize = 15,
                                    text = "delivery mode",
                                    textColor = Color.Black,
                                    isUppercase = true,
                                    isBold = true,
                                    isExtraBold = false,
                                    padding = 0
                                )
                                SimpleText(
                                    textSize = 12,
                                    text = "express ride",
                                    textColor = Color.DarkGray,
                                    isUppercase = true,
                                    isBold = false,
                                    isExtraBold = false,
                                    padding = 0
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            SimpleText(
                                textSize = 15,
                                text = "distance",
                                textColor = Color.Black,
                                isUppercase = true,
                                isBold = true,
                                isExtraBold = false,
                                padding = 0
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            SimpleText(
                                textSize = 12,
                                text = "17 km",
                                textColor = Color.DarkGray,
                                isUppercase = true,
                                isBold = false,
                                isExtraBold = false,
                                padding = 8
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
//                DELIVERY DETAILS
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            SimpleText(
                                textSize = 15,
                                text = "delivery details",
                                textColor = Color.Black,
                                isUppercase = true,
                                isBold = true,
                                isExtraBold = false,
                                padding = 0
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                SimpleText(
                                    textSize = 15,
                                    text = "from",
                                    textColor = Color.Black,
                                    isUppercase = true,
                                    isBold = true,
                                    isExtraBold = false,
                                    padding = 0
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(2f),
                                horizontalAlignment = Alignment.End
                            ) {
                                SimpleText(
                                    textSize = 12,
                                    text = "customer name",
                                    textColor = Color.DarkGray,
                                    isUppercase = true,
                                    isBold = false,
                                    isExtraBold = false,
                                    padding = 4
                                )
                                SimpleText(
                                    textSize = 12,
                                    text = "Some long location name",
                                    textColor = Color.DarkGray,
                                    isUppercase = false,
                                    isBold = false,
                                    isExtraBold = false,
                                    padding = 4
                                )
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(2.dp)
                                .fillMaxWidth(0.7f)
                                .background(Color.LightGray)
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                SimpleText(
                                    textSize = 15,
                                    text = "to",
                                    textColor = Color.Black,
                                    isUppercase = true,
                                    isBold = true,
                                    isExtraBold = false,
                                    padding = 0
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(2f),
                                horizontalAlignment = Alignment.End
                            ) {
                                SimpleText(
                                    textSize = 12,
                                    text = "customer name",
                                    textColor = Color.DarkGray,
                                    isUppercase = true,
                                    isBold = false,
                                    isExtraBold = false,
                                    padding = 4
                                )
                                SimpleText(
                                    textSize = 12,
                                    text = "Some long location name",
                                    textColor = Color.DarkGray,
                                    isUppercase = false,
                                    isBold = false,
                                    isExtraBold = false,
                                    padding = 4
                                )
                            }
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
//                DELIVERY PERSON
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                        ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            SimpleText(
                                textSize = 15,
                                text = "delivery person",
                                textColor = Color.Black,
                                isUppercase = true,
                                isBold = true,
                                isExtraBold = false,
                                padding = 0
                            )
                            SimpleText(
                                textSize = 12,
                                text = "rider name",
                                textColor = Color.DarkGray,
                                isUppercase = true,
                                isBold = false,
                                isExtraBold = false,
                                padding = 0
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            StyledText(
                                textColor = Color.White,
                                backgroundColor = LightBlue,
                                borderColor = Color.Transparent,
                                textSize = 15,
                                text = "23456",
                                isUppercase = true,
                                isBold = true
                            )
                            ProfilePic(profilePicUrl = null)
                        }
                    }
//                COSTS
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            SimpleText(
                                textSize = 15,
                                text = "insurance",
                                textColor = Color.Black,
                                isUppercase = true,
                                isBold = true,
                                isExtraBold = false,
                                padding = 0
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            SimpleText(
                                textSize = 12,
                                text = "55/-",
                                textColor = Color.DarkGray,
                                isUppercase = true,
                                isBold = false,
                                isExtraBold = false,
                                padding = 8
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            SimpleText(
                                textSize = 15,
                                text = "delivery cost",
                                textColor = Color.Black,
                                isUppercase = true,
                                isBold = true,
                                isExtraBold = false,
                                padding = 0
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            SimpleText(
                                textSize = 12,
                                text = "100/-",
                                textColor = Color.DarkGray,
                                isUppercase = true,
                                isBold = false,
                                isExtraBold = false,
                                padding = 8
                            )
                        }
                    }
//                TOTAL
                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            SimpleText(
                                textSize = 21,
                                text = "total",
                                textColor = Color.Black,
                                isUppercase = true,
                                isBold = true,
                                isExtraBold = true,
                                padding = 0
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50))
                    .background(LightBlue)
            ) {
                Text(
                    text = "783/-".uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                )
            }
        }
        TotallyRoundedButton(
            icon = null,
            buttonText = "pay",
            textFontSize = 15,
            textColor = Color.White,
            backgroundColor = LightBlue,
            widthFraction = 0.5,
            action = {}
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ParcelInvoiceContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ParcelInvoiceContent()
    }
}