package com.example.dropy.ui.components.parcels.frontside.selectlocations

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun SelectLocationsForm(){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                SimpleText(
                    textSize = 15,
                    text = "select locations",
                    isUppercase = true,
                    isBold = true,
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                    ,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        imageVector = Icons.Filled.MyLocation,
                        contentDescription = "location icon",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(24.dp)
                        ,
                        tint = LightBlue
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                    ) {
                        StyledText(
                            backgroundColor = DropyYellow,
                            textSize = 15,
                            text = "pickup",
                            isUppercase = true,
                            isBold = true
                        )
                        val pickupLocation = remember{ mutableStateOf("Location") }
                        TextField(
                            value = pickupLocation.value,
                            onValueChange = {pickupLocation.value = it},
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .height(32.dp)
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                unfocusedIndicatorColor = Color.DarkGray,
                                focusedIndicatorColor = Color.Black,
                                backgroundColor = Color.White,
                                textColor = Color.DarkGray
                            ),
                            singleLine = true
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                    ,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        modifier = Modifier
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier
                            .padding(bottom = 8.dp, end = 8.dp)
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(LightBlue)
                        )
                        Spacer(modifier = Modifier
                            .padding(bottom = 8.dp, end = 8.dp)
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(LightBlue)
                        )
                        Spacer(modifier = Modifier
                            .padding(bottom = 8.dp, end = 8.dp)
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(LightBlue)
                        )
                        Spacer(modifier = Modifier
                            .padding(bottom = 8.dp, end = 8.dp)
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(LightBlue)
                        )
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "location icon",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp)
                            ,
                            tint = LightBlue
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        StyledText(
                            backgroundColor = DropyYellow,
                            textSize = 15,
                            text = "destination",
                            isUppercase = true,
                            isBold = true
                        )
                        val destinationLocation = remember{ mutableStateOf("Location") }
                        TextField(
                            value = destinationLocation.value,
                            onValueChange = {destinationLocation.value = it},
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .height(32.dp)
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                unfocusedIndicatorColor = Color.DarkGray,
                                focusedIndicatorColor = Color.Black,
                                backgroundColor = Color.White,
                                textColor = Color.DarkGray
                            ),
                            singleLine = true
                        )
                    }
                }

            }
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
        ){
            TotallyRoundedButton(
                buttonText = "continue",
                textFontSize = 15,
                textColor = Color.White,
                backgroundColor = LightBlue,
                widthFraction = 0.5,
                action = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectLocationsFormPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        SelectLocationsForm()
    }
}