package com.example.dropy.ui.components.parcels.frontside.parceldetails


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Phone
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
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ParcelDetailsContent(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        ){
            MapComponent()
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReceiverDetailsForm()
            ParcelSizeList(selectedSize = {})
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { }
            ){
                TotallyRoundedButton(
                    icon = null,
                    buttonText = "continue",
                    textFontSize = 12,
                    textColor = Color.White,
                    backgroundColor = LightBlue,
                    widthFraction = 0.5,
                    action = {}
                )
            }
        }
    }

}

@Composable
fun ReceiverDetailsForm(){
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            SimpleText(
                textSize = 15,
                text = "receiver details",
                textColor = Color.Black,
                isUppercase = true,
                isBold = true
            )
            val id = remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = id.value,
                onValueChange = {id.value = it},
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                ,
                label = {
                    SimpleText(
                        textSize = 9,
                        text = "receiver id number",
                        textColor = Color.Black,
                        isUppercase = true,
                        isBold = true
                    )

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = ""
                    )
                }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                ,
                label = {
                    SimpleText(
                        textSize = 9,
                        text = "receiver phone",
                        isUppercase = true,
                        isBold = true
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = ""
                    )
                }
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)

            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f)
                    ,
                    label = {
                        SimpleText(
                            textSize = 9,
                            text = "first name",
                            textColor = Color.Black,
                            isUppercase = true,
                            isBold = true
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = ""
                        )
                    }
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color.Black
                    ),
                    modifier = Modifier
                        .weight(1f)
                    ,
                    label = {
                        SimpleText(
                            textSize = 9,
                            text = "first name",
                            isUppercase = true,
                            isBold = true
                        )
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ParcelDetailsContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ParcelDetailsContent()
    }
}