package com.example.dropy.ui.screens.rider

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.network.models.pools.RiderPoolsResponseItem
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.components.commons.Dropdown
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.rider.becomerider.AddRiderUiState
import com.example.dropy.ui.screens.rider.becomerider.BecomeRiderUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun AddRiderContent(
    uiState: BecomeRiderUiState,
    appuiState: AppUiState,
    onPlateNumberChanged: (String) -> Unit,
    onPlaceNameChanged: () -> Unit,
    onBikeModelChanged: (String) -> Unit,
    onRiderPhoneChanged: (String) -> Unit,
    onCountyQrCodeChanged: (String) -> Unit,
    onNationalIdChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onDeliveryMethodSelect: (DeliveryMethodResponseItem) -> Unit,
    onPoolsSelect: (RiderPoolsResponseItem) -> Unit,
    createRider: () -> Unit
) {

    LaunchedEffect(key1 = true, block = {
//        onDeliveryMethodSelect(uiState.deliveryMethods[0])
//        onPoolsSelect(uiState.availablePools[0])
    })


    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        ClippedHeader(title = "become a rider")

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .wrapContentHeight()

/*                .weight(1f)*/
                .padding(16.dp)

        ) {
            SimpleText(
                text = "lets create a rider",
                textSize = 14,
                isUppercase = true,
                isBold = true,
                font = Font(R.font.axiformabold)
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                    //     .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Plate Number",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.plate_number.toString(),
                        onValueChange = { onPlateNumberChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = LightBlue,
                            focusedBorderColor = LightBlue,
                        ),
                    )
                }

//                Column(
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier
//                        .weight(1f)
//                    ,
//                ) {
//                    SimpleText(
//                        textSize = 10,
//                        text = "Shop Location",
//                        isExtraBold = true
//                    )
//
//                    OutlinedTextField(
//                        value = uiState.shopLocation?.addressName ?: "",
//                        onValueChange = { },
//                        shape = RoundedCornerShape(8.dp),
//                        readOnly = true,
//                        modifier = Modifier
//                            .padding(top = 8.dp)
//                            .fillMaxWidth()                            .height(48.dp)
//                        ,
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = LightBlue,
//                            focusedBorderColor = LightBlue,
//                        ),
//                        trailingIcon = {
//                            IconButton(
//                                onClick = { onChangeShopLocation() },
//                                modifier = Modifier
//                                    .size(32.dp)
//                                    .clip(CircleShape)
//                                    .background(Color(253, 211, 19))
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Outlined.EditLocation,
//                                    contentDescription = "edit location",
//                                    modifier = Modifier
//                                        .padding(2.dp)
//                                )
//                            }
//                        }
//                    )
//                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(.45f)
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Bike model",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.bike_model.toString(),
                        onValueChange = { onBikeModelChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = LightBlue,
                            focusedBorderColor = LightBlue,
                        ),
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                    //   .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Bike color",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.description,
                        onValueChange = onDescriptionChanged,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = LightBlue,
                            focusedBorderColor = LightBlue,
                        ),
                    )
                }
            }
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Delivery method",
                        isExtraBold = true
                    )
                }
                Dropdown(
                    deliveryMethoditems = uiState.deliveryMethods,
                    onDeliveryMethodSelect = onDeliveryMethodSelect, type = "ridermethods",
                    onSelect = { _, _ ->
                    }, onShopSelect = { _, _ ->
                    }
                )
            }
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Pools",
                        isExtraBold = true
                    )
                }
                Dropdown(
                    poolitems = uiState.availablePools,
                    onPoolsSelect = onPoolsSelect, type = "riderpools",
                    onSelect = { _, _ ->
                    }, onShopSelect = { _, _ ->
                    }
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp, end = 8.dp /*top = 10.dp*/)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                    //    .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "County qr id number",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.county_qr_id_number.toString(),
                        onValueChange = onCountyQrCodeChanged,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(.45f)
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = LightBlue,
                            focusedBorderColor = LightBlue,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        /*   keyboardOptions = KeyboardOptions(
                               keyboardType = KeyboardType.Phone
                           )*/
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,

                    //    .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "National Id",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.national_id.toString(),
                        onValueChange = onNationalIdChanged,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = LightBlue,
                            focusedBorderColor = LightBlue,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                /*     Column(
                         verticalArrangement = Arrangement.Center,
                         modifier = Modifier
                             .padding(end = 8.dp)
                             .weight(1f),
                     ) {
                         SimpleText(
                             textSize = 10,
                             text = "County QR Id",
                             isExtraBold = true,
                             font = Font(R.font.axiformaextrabold)
                         )

                         OutlinedTextField(
                             value = uiState.county_qr_id_number.toString(),
                             onValueChange = onCountyQrCodeChanged,
                             shape = RoundedCornerShape(8.dp),
                             modifier = Modifier
                                 .padding(top = 8.dp)
                                 .fillMaxWidth()
                                 .height(48.dp),
                             colors = TextFieldDefaults.outlinedTextFieldColors(
                                 unfocusedBorderColor = LightBlue,
                                 focusedBorderColor = LightBlue,
                             ),
                             keyboardOptions = KeyboardOptions(
                                 keyboardType = KeyboardType.Phone
                             )
                         )
                     }*/
//                Column(
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier
//                        .weight(1f)
//                    ,
//                ) {
//                    SimpleText(
//                        textSize = 10,
//                        text = "Shop Contact 2",
//                        isExtraBold = true
//                    )
//
//                    OutlinedTextField(
//                        value = uiState.shopPhoneTwo,
//                        onValueChange = {onShopPhoneTwoChanged(it)},
//                        shape = RoundedCornerShape(8.dp),
//                        modifier = Modifier
//                            .padding(top = 8.dp)
//                            .fillMaxWidth()
//                            .height(48.dp)
//                        ,
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = LightBlue,
//                            focusedBorderColor = LightBlue,
//                        ),
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Phone
//                        )
//                    )
//                }
            }



            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 8.dp, top = 10.dp)
                //    .weight(1f),
            ) {
                SimpleText(
                    textSize = 10,
                    text = "Place name",
                    isExtraBold = true,
                    font = Font(R.font.axiformaextrabold)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(7.dp),
                            color = Color.LightGray
                        )
                    //  .zIndex(1f)
                ) {

                    /*AutoCompleteObjectSample(
                        locations = autompleteLocations,
                        value = currentAddress?.placeName?:"",
                        onValueChange = onPlaceNameChanged,
                        onClear = { *//*TODO*//* },
                    label = ""
                )*/
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(49.dp)
                            .clickable { onPlaceNameChanged() }
                    ) {

                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        SimpleText(
                            text = appuiState.myAddress?.placeName ?: "your location",
                            isUppercase = false,
                            textSize = 14,
                            fontWeight = FontWeight.Bold,
                            font = Font(R.font.axiformabold)
                        )


                    }
                    /*     OutlinedTextField(
                             value = currentAddress?.placeName ?: "",
                             onValueChange = onPlaceNameChanged,
                             // readOnly = true,
                             placeholder = {
                                 SimpleText(
                                     text = "delivery location",
                                     isUppercase = true,
                                     textSize = 10
                                 )
                             },
                             modifier = Modifier
                                 .padding(top = 8.dp, end = 8.dp)
                                 .fillMaxWidth()
                                 .height(48.dp),
                             leadingIcon = {
                                 Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "")
                             }
                         )*/


                }

                /*  OutlinedTextField(
                      value = appuiState.myAddress?.placeName.toString(),
                      onValueChange = {},
                      shape = RoundedCornerShape(8.dp),
                      modifier = Modifier
                          .clickable {
                              onPlaceNameChanged()
                          }
                          .padding(top = 8.dp)
                          .fillMaxWidth()
                          .height(48.dp),
                      colors = TextFieldDefaults.outlinedTextFieldColors(
                          unfocusedBorderColor = LightBlue,
                          focusedBorderColor = LightBlue,
                      ),
                      keyboardOptions = KeyboardOptions(
                          keyboardType = KeyboardType.Phone
                      ),
                      readOnly = true
                  )*/
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                TotallyRoundedButton(
                    buttonText = "create rider",
                    backgroundColor = DropyYellow,
                    action = { createRider() },
                    widthFraction = 0.6
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun screenDemo() {
    AddRiderContent(
        uiState = BecomeRiderUiState(),
        appuiState = AppUiState(),
        onPlaceNameChanged = {},
        onBikeModelChanged = {},
        onRiderPhoneChanged = {},
        onCountyQrCodeChanged = {},
        onNationalIdChanged = {},
        onDescriptionChanged = {},
        onDeliveryMethodSelect = {},
        onPoolsSelect = {},
        onPlateNumberChanged = {}
    ) {

    }
}