package com.example.dropy.ui.screens.addWaterDriver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.Dropdown
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader

@Composable
fun AddWaterDriverContent(
    uiState: TruckDriverUiState,
    onLicensePlateChanged: (String) -> Unit,
    onAddTruckDriver: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {

        ClippedHeader(title = "add truck driver")

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            SimpleText(
                text = "lets create a truck driver",
                textSize = 14,
                isUppercase = true,
                isBold = true,
                font = Font(R.font.axiformabold)
            )
/*
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .padding(bottom = 24.dp)
                    .width(width = 277.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 8.dp)
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Shop Type",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )
                }
                Dropdown(
                    shoptypes = uiState.shopTypeList,
                    onSelect = { it, num ->
                    },
                    onShopTypeSelect = { it ->
                        changeShopType(it)
                    }, type = "shoptype",
                    onShopSelect = { _, _ ->
                    }
                )
            }*/
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 16.dp)
                    .fillMaxWidth()
            ) {
                /*   Column(
                       verticalArrangement = Arrangement.Center,
                       modifier = Modifier
                           .padding(end = 8.dp)
                           .weight(1f),
                   ) {
                       SimpleText(
                           textSize = 10,
                           text = "Driver id",
                           isExtraBold = true,
                           font = Font(R.font.axiformaextrabold)
                       )

                       OutlinedTextField(
                           value = uiState.driverId,
                           onValueChange = { onShopNameChanged(it) },
                           shape = RoundedCornerShape(8.dp),
                           modifier = Modifier
                               .padding(top = 8.dp)
                               .fillMaxWidth()
                               .height(48.dp),
                           colors = TextFieldDefaults.outlinedTextFieldColors(
                               unfocusedBorderColor = Color.Black,
                               focusedBorderColor = Color.Black,
                           ),
                           textStyle = TextStyle(
                               color = Color.Black,
                               fontFamily = FontFamily(Font(R.font.axiformaregular))
                           )
                       )
                   }*/
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
//                        .padding(end = 8.dp)
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Truck licence plate",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = uiState.licensePlate,
                        onValueChange = { onLicensePlateChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
//                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                        ),
                        /* keyboardOptions = KeyboardOptions(
                             keyboardType = KeyboardType.
                         ),*/
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.axiformaregular))
                        )
                    )
                }
                /*Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Shop Location",
                        isExtraBold = true
                    )

                    OutlinedTextField(
                        value = *//*uiState.shopLocation?.addressName ?:*//* "",
                        onValueChange = { },
                        shape = RoundedCornerShape(8.dp),
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                        ),
                      *//*  trailingIcon = {
                            IconButton(
                                onClick = { *//**//*onChangeShopLocation()*//**//* },
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(253, 211, 19))
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.EditLocation,
                                    contentDescription = "edit location",
                                    modifier = Modifier
                                        .padding(2.dp)
                                )
                            }
                        }*//*
                    )
                }*/
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                TotallyRoundedButton(
                    buttonText = "next",
                    backgroundColor = Color.Black,
                    action = { onAddTruckDriver() },
                    widthFraction = 0.6,
                    textColor = Color.White
                )
            }
        }

    }
}