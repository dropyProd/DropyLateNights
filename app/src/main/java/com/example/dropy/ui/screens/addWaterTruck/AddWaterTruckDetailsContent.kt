package com.example.dropy.ui.screens.addWaterTruck

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.ui.components.commons.Dropdown
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopUiState
import java.util.*

@Composable
fun AddWaterTruckDetailsContent(
    uiState: AddWaterTruckUiState,
    onShopNameChanged: (shopName: String) -> Unit,
//    onChangeShopLocation:()->Unit,
    selectedTruckCapacity: (shopPhoneOne: String) -> Unit,
    onShopPhoneOneChanged: (shopPhoneOne: String) -> Unit,
    onShopPhoneTwoChanged: (shopPhoneOne: String) -> Unit,
    onModelChanged: (shopPhoneOne: String) -> Unit,
    onYearChanged: (shopPhoneOne: String) -> Unit,
    onAddShopLocation: () -> Unit,

    ) {

    /*   AppBackSideTopBar(
           onBackButtonClicked = {},
           onDashboardButtonClicked = {

           },
       )*/


/*    LaunchedEffect(key1 = true, block = {
        if (!uiState.shopCategoryList.isEmpty())
            onCategorySelected(uiState.shopCategoryList[0], 0)
    })*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {

        ClippedHeader(title = "add watertruck")

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            SimpleText(
                text = "lets create a watertruck",
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
                        text = "Truck capacity",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )
Spacer(modifier = Modifier.height(8.dp))
                    Dropdown(
                        truckCapacities = uiState.truckCapacities,
                        onTruckCapacitySelect = selectedTruckCapacity,
                        type = "waterTruck"
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

            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "License plate",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.licensePlate,
                        onValueChange = { onShopPhoneOneChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
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

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Watertruck Phone Number",
                        isExtraBold = true
                    )

                    OutlinedTextField(
                        value = uiState.shopPhoneTwo,
                        onValueChange = { onShopPhoneTwoChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Model",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.model,
                        onValueChange = { onModelChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                        ),
                        /*keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),*/
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.axiformaregular))
                        )
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Year",
                        isExtraBold = true
                    )

                    OutlinedTextField(
                        value = uiState.year,
                        onValueChange = { onYearChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        )
                    )
                }
            }

            /* AndroidView(
                 factory = { ctx ->
                     android.widget.TimePicker(ctx).apply {
                         layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                         setOnClickListener {
                             //  state.value++
                         }
                     }
                 }, *//*modifier = Modifier.padding(8.dp)*//*
            )*/

            /*        Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    ) {
                        Spacer(modifier = Modifier.padding(4.dp))
                        SimpleText(
                            textSize = 10,
                            text = "Add Managers - Type user ID number to add",
                            isExtraBold = true,
                            font = Font(R.font.axiformaextrabold)
                        )


                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .background(color = Color.Transparent)
                                .border(
                                    width = 2.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(7.dp)
                                )
                                .height(48.dp)
                                .clickable {

                                }
                                .padding(horizontal = 12.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(.9f)
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                uiState.branchManagers.forEach { name ->
                                    managerText(text = name)
                                }


                            }

                            Row(
                                modifier = Modifier
                                    .clickable { changeBranchManTextState(!uiState.branchManagerState) }
                                    .padding(6.dp)
                                    .wrapContentSize()
                                    .background(Color.Black, shape = CircleShape),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center

                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "", tint = Color.White)
                            }
                        }
                        AnimatedVisibility(
                            visible = uiState.branchManagerState,
                            enter = fadeIn(animationSpec = tween(1000)) +
                                    expandVertically(
                                        animationSpec = tween(
                                            1500*//*,
                                    easing = BounceInterpolator()*//*
                                )
                            ),
                    exit = fadeOut(animationSpec = tween(1000)) +
                            shrinkVertically(
                                animationSpec = tween(
                                    1500*//*,
                                    easing = BounceInterpolator()*//*
                                )
                            )
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = uiState.branchManager,
                            onValueChange = onBranchManagerChanged,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(.8f)
                                .height(48.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color.Black,
                                focusedBorderColor = Color.Black,
                            ),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.axiformaregular))
                            ),
                            placeholder = {

                                SimpleText(
                                    textSize = 10,
                                    text = "Enter name eg. Doe",
                                    isExtraBold = false,
                                    font = Font(R.font.axiformaregular),
                                    textColor = Color.LightGray
                                )
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { addBranchName(uiState.branchManager) })
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
                                .wrapContentWidth()
                                .height(48.dp)
                                .clickable {
                                    addBranchName(uiState.branchManager)
                                }
                                .padding(horizontal = 5.dp, vertical = 3.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SimpleText(
                                textSize = 11,
                                text = "ADD",
                                isExtraBold = true,
                                font = Font(R.font.axiformaextrabold),
                                textColor = Color.White
                            )
                        }
                    }
                }


            }*/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                TotallyRoundedButton(
                    buttonText = "next",
                    backgroundColor = Color.Black,
                    action = { onAddShopLocation() },
                    widthFraction = 0.6,
                    textColor = Color.White
                )
            }
        }

    }
}


@Composable
fun managerText(text: Int) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .wrapContentSize()
            .background(Color.Black, shape = RoundedCornerShape(9.dp))
            .padding(vertical = 4.dp, horizontal = 6.dp)
    ) {
        SimpleText(
            textSize = 10,
            text = text.toString(),
            isExtraBold = true,
            textColor = Color.White
        )
    }
}

@Preview
@Composable
fun AddShopContentPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        AddWaterTruckDetailsContent(
            uiState = AddWaterTruckUiState(),
//            onChangeShopLocation = {},
            onShopNameChanged = {},
            onShopPhoneOneChanged = {},
            onShopPhoneTwoChanged = {},
            onAddShopLocation = {},
            onModelChanged = {},
            onYearChanged = {},
            selectedTruckCapacity = {}
        )
    }
}