package com.example.dropy.ui.screens.addWaterVendor

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
fun AddWaterVendorDetailsContent(
    uiState: AddWaterVendorUiState,
    onShopNameChanged: (shopName: String) -> Unit,
    onDescriptionChanged: (shopName: String) -> Unit,
//    onChangeShopLocation:()->Unit,
    onShopPhoneOneChanged: (shopPhoneOne: String) -> Unit,
    onShopPhoneTwoChanged: (shopPhoneOne: String) -> Unit,
    onBranchManagerChanged: (branchname: String) -> Unit,
    addBranchName: (branchname: String) -> Unit,
    changeBranchManTextState: (state: Boolean) -> Unit,
//    onShopPhoneTwoChanged:(shopPhoneTwo:String)->Unit,
    onCategorySelected: (category: ShopCategoriesResponseItem, index: Int) -> Unit,
    onAddShopLocation: () -> Unit,
    changeOperationHrState: (Boolean) -> Unit,
    changeSaturdayState: (Boolean) -> Unit,
    changeSundayState: (Boolean) -> Unit,
    changeHolidayState: (Boolean) -> Unit,
    changeWeekdayOpeningTime: (String) -> Unit,
    changeWeekdayClosingTime: (String) -> Unit,
    changeSaturdayOpeningTime: (String) -> Unit,
    changeSaturdayClosingTime: (String) -> Unit,
    changeHolidayOpeningTime: (String) -> Unit,
    changeHolidayClosingTime: (String) -> Unit,
    changeSundayOpeningTime: (String) -> Unit,
    changeSundayClosingTime: (String) -> Unit,
    changeShopType: (String) -> Unit
) {

     /*   AppBackSideTopBar(
            onBackButtonClicked = {},
            onDashboardButtonClicked = {

            },
        )*/


    LaunchedEffect(key1 = true, block = {
        if (!uiState.shopCategoryList.isEmpty())
            onCategorySelected(uiState.shopCategoryList[0], 0)
    })
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        ClippedHeader(title = "add watervendor")

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            SimpleText(
                text = "lets create a watervendor",
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
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Watervendor Name",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.name,
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

          /*  Row(
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 16.dp)
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
                        text = "Watervendor description",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.description,
                        onValueChange = { onDescriptionChanged(it) },
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
                }
                *//*Column(
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
                        value = *//**//*uiState.shopLocation?.addressName ?:*//**//* "",
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
                      *//**//*  trailingIcon = {
                            IconButton(
                                onClick = { *//**//**//**//*onChangeShopLocation()*//**//**//**//* },
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
                        }*//**//*
                    )
                }*//*
            }*/

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
                        text = "Watervendor Phone Number 1",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.shopPhoneOne,
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
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),
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
                        text = "Watervendor Phone Number 2",
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
              /*  Column(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(.64f)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    ) {
                        SimpleText(
                            textSize = 10,
                            text = "waterpoint Category",
                            isExtraBold = true,
                            font = Font(R.font.axiformaextrabold)
                        )
                    }
                    Dropdown(
                        shopcategoryitems = uiState.shopCategoryList,
                        onSelect = { it, num ->
                        },
                        onShopSelect = { it, num ->
                            onCategorySelected(it, num)
                        }, type = "Addshop"
                    )
                }
*/
                Column(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .padding(bottom = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )

                    {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        Switch(
                            checked = uiState.operationHrState,
                            onCheckedChange = changeOperationHrState,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White,
                                checkedTrackColor = Color.Black,
                                uncheckedTrackColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        SimpleText(
                            textSize = 8,
                            text = "OPEN 24 hrs",
                            isExtraBold = false,
                            font = Font(R.font.axiformablack),
                            textColor = Color.Black
                        )
                    }
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Saturday",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    val backgroundColorClose by animateColorAsState(
                        if (uiState.saturdayState) Color.LightGray else Color.Black
                    )
                    val backgroundColorOpen by animateColorAsState(
                        if (uiState.saturdayState) Color.Black else Color.LightGray
                    )

                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SimpleText(
                            textSize = 8,
                            text = "CLOSED",
                            isExtraBold = false,
                            font = Font(R.font.axiformablack),
                            textColor = backgroundColorClose
                        )
                        Switch(
                            checked = uiState.saturdayState,
                            onCheckedChange = changeSaturdayState,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White,
                                checkedTrackColor = Color.Black,
                                uncheckedTrackColor = Color.Transparent
                            )
                        )
                        SimpleText(
                            textSize = 8,
                            text = "OPEN",
                            isExtraBold = false,
                            font = Font(R.font.axiformablack),
                            textColor = backgroundColorOpen
                        )
                    }

                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Sunday",
                        isExtraBold = true
                    )

                    val backgroundColorClose by animateColorAsState(
                        if (uiState.sundayState) Color.LightGray else Color.Black
                    )
                    val backgroundColorOpen by animateColorAsState(
                        if (uiState.sundayState) Color.Black else Color.LightGray
                    )

                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SimpleText(
                            textSize = 8,
                            text = "CLOSED",
                            isExtraBold = false,
                            font = Font(R.font.axiformablack),
                            textColor = backgroundColorClose
                        )
                        Switch(
                            checked = uiState.sundayState,
                            onCheckedChange = changeSundayState,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White,
                                checkedTrackColor = Color.Black,
                                uncheckedTrackColor = Color.Transparent
                            )
                        )
                        SimpleText(
                            textSize = 8,
                            text = "OPEN",
                            isExtraBold = false,
                            font = Font(R.font.axiformablack),
                            textColor = backgroundColorOpen
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Holidays",
                        isExtraBold = true
                    )

                    val backgroundColorClose by animateColorAsState(
                        if (uiState.holidayState) Color.LightGray else Color.Black
                    )
                    val backgroundColorOpen by animateColorAsState(
                        if (uiState.holidayState) Color.Black else Color.LightGray
                    )

                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SimpleText(
                            textSize = 8,
                            text = "CLOSED",
                            isExtraBold = false,
                            font = Font(R.font.axiformablack),
                            textColor = backgroundColorClose
                        )
                        Switch(
                            checked = uiState.holidayState,
                            onCheckedChange = changeHolidayState,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White,
                                checkedTrackColor = Color.Black,
                                uncheckedTrackColor = Color.Transparent
                            )
                        )
                        SimpleText(
                            textSize = 8,
                            text = "OPEN",
                            isExtraBold = false,
                            font = Font(R.font.axiformablack),
                            textColor = backgroundColorOpen
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 16.dp)
                    .fillMaxWidth()
            ) {

                val context = LocalContext.current
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Weekdays Opening Time",
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
                                // on below line we are getting
                                // the instance of our calendar.
                                val c = Calendar.getInstance()

                                // on below line we are getting our hour, minute.
                                val hour = c.get(Calendar.HOUR_OF_DAY)
                                val minute = c.get(Calendar.MINUTE)

                                // on below line we are initializing
                                // our Time Picker Dialog
                                val timePickerDialog = TimePickerDialog(
                                    context,
                                    { view, hourOfDay, minute ->
                                        // on below line we are setting selected
                                        // time in our text view.
                                        val timeseet = "$hourOfDay:$minute"

                                        // TODO Auto-generated method stub
                                        // TODO Auto-generated method stub
                                        var hours = hourOfDay
                                        val minutes = minute
                                        var timeSet = ""

                                        Log.d(
                                            "huiop",
                                            "AddShopDetailsContent: $hours     $hourOfDay"
                                        )
                                        if (hourOfDay > 12) {
                                            hours -= 12
                                            timeSet = "PM"
                                        } else if (hours === 0) {
                                            hours += 12
                                            timeSet = "AM"
                                        } else if (hours === 12) {
                                            timeSet = "PM"
                                        } else {
                                            timeSet = "AM"
                                        }

                                        var min: String? = ""
                                        if (minutes < 10) min = "0$minutes" else min =
                                            java.lang.String.valueOf(minutes)

                                        // Append in a StringBuilder

                                        // Append in a StringBuilder
                                        changeWeekdayOpeningTime(
                                            /*        StringBuilder()
                                                        .append(hours)
                                                        .append(':')
                                                        .append(min)
                                                        .append(" ")
                                                        .append(timeSet)
                                                        .toString()*/timeseet
                                        )

                                        /*        Toast
                                                    .makeText(context, aTime.value, Toast.LENGTH_SHORT)
                                                    .show()*/
                                    },
                                    hour,
                                    minute,
                                    false
                                )
                                // at last we are calling show to
                                // display our time picker dialog.
                                timePickerDialog.show()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SimpleText(
                            textSize = 11,
                            text = uiState.weekday_opening_time,
                            isExtraBold = true,
                            textColor = Color.Black
                        )
                    }

                    // widget.Button
//                    AndroidView(
//                        factory = { ctx ->
//                            android.widget.TimePicker(ctx).apply {
//                                layoutParams = LinearLayout.LayoutParams(140, 140)
//                                setOnClickListener {
//                                    //  state.value++
//                                }
//                            }
//                        }, /*modifier = Modifier.padding(8.dp)*/
//                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "Closing Time",
                        isExtraBold = true
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
                                // on below line we are getting
                                // the instance of our calendar.
                                val c = Calendar.getInstance()

                                // on below line we are getting our hour, minute.
                                val hour = c.get(Calendar.HOUR_OF_DAY)
                                val minute = c.get(Calendar.MINUTE)

                                // on below line we are initializing
                                // our Time Picker Dialog
                                val timePickerDialog = TimePickerDialog(
                                    context,
                                    { view, hourOfDay, minute ->
                                        // on below line we are setting selected
                                        // time in our text view.
                                        val timeseet = "$hourOfDay:$minute"

                                        // TODO Auto-generated method stub
                                        // TODO Auto-generated method stub
                                        var hours = hourOfDay
                                        val minutes = minute
                                        var timeSet = ""

                                        Log.d(
                                            "huiop",
                                            "AddShopDetailsContent: $hours     $hourOfDay"
                                        )
                                        if (hourOfDay > 12) {
                                            hours -= 12
                                            timeSet = "PM"
                                        } else if (hours === 0) {
                                            hours += 12
                                            timeSet = "AM"
                                        } else if (hours === 12) {
                                            timeSet = "PM"
                                        } else {
                                            timeSet = "AM"
                                        }

                                        var min: String? = ""
                                        if (minutes < 10) min = "0$minutes" else min =
                                            java.lang.String.valueOf(minutes)

                                        // Append in a StringBuilder
                                        changeWeekdayClosingTime(
                                            /*        StringBuilder()
                                                        .append(hours)
                                                        .append(':')
                                                        .append(min)
                                                        .append(" ")
                                                        .append(timeSet)
                                                        .toString()*/timeseet
                                        )

                                        // Append in a StringBuilder

                                    },
                                    hour,
                                    minute,
                                    false
                                )
                                // at last we are calling show to
                                // display our time picker dialog.
                                timePickerDialog.show()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SimpleText(
                            textSize = 11,
                            text = uiState.weekday_closing_time,
                            isExtraBold = true,
                            textColor = Color.Black
                        )
                    }

                    /* // widget.Button
                     AndroidView(
                         factory = { ctx ->
                             android.widget.TimePicker(ctx).apply {
                                 layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                                 setOnClickListener {
                                     //  state.value++
                                 }
                             }
                         }, *//*modifier = Modifier.padding(8.dp)*//*
                    )*/
                }
            }
            AnimatedVisibility(
                visible = uiState.holidayState,
                enter = fadeIn(animationSpec = tween(1000)) +
                        expandVertically(
                            animationSpec = tween(
                                1500/*,
                                    easing = BounceInterpolator()*/
                            )
                        ),
                exit = fadeOut(animationSpec = tween(1000)) +
                        shrinkVertically(
                            animationSpec = tween(
                                1500/*,
                                    easing = BounceInterpolator()*/
                            )
                        )
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 24.dp, top = 16.dp)
                        .fillMaxWidth()
                ) {

                    val context = LocalContext.current
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f),
                    ) {
                        SimpleText(
                            textSize = 10,
                            text = "Holidays Opening Time",
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
                                    // on below line we are getting
                                    // the instance of our calendar.
                                    val c = Calendar.getInstance()

                                    // on below line we are getting our hour, minute.
                                    val hour = c.get(Calendar.HOUR_OF_DAY)
                                    val minute = c.get(Calendar.MINUTE)

                                    // on below line we are initializing
                                    // our Time Picker Dialog
                                    val timePickerDialog = TimePickerDialog(
                                        context,
                                        { view, hourOfDay, minute ->
                                            // on below line we are setting selected
                                            // time in our text view.
                                            val timeseet = "$hourOfDay:$minute"

                                            // TODO Auto-generated method stub
                                            // TODO Auto-generated method stub
                                            var hours = hourOfDay
                                            val minutes = minute
                                            var timeSet = ""

                                            Log.d(
                                                "huiop",
                                                "AddShopDetailsContent: $hours     $hourOfDay"
                                            )
                                            if (hourOfDay > 12) {
                                                hours -= 12
                                                timeSet = "PM"
                                            } else if (hours === 0) {
                                                hours += 12
                                                timeSet = "AM"
                                            } else if (hours === 12) {
                                                timeSet = "PM"
                                            } else {
                                                timeSet = "AM"
                                            }

                                            var min: String? = ""
                                            if (minutes < 10) min = "0$minutes" else min =
                                                java.lang.String.valueOf(minutes)

                                            // Append in a StringBuilder
                                            changeHolidayOpeningTime(
                                                /*         StringBuilder()
                                                             .append(hours)
                                                             .append(':')
                                                             .append(min)
                                                             .append(" ")
                                                             .append(timeSet)
                                                             .toString()*/timeseet
                                            )
                                            // Append in a StringBuilder

                                        },
                                        hour,
                                        minute,
                                        false
                                    )
                                    // at last we are calling show to
                                    // display our time picker dialog.
                                    timePickerDialog.show()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SimpleText(
                                textSize = 11,
                                text = uiState.holiday_opening_time,
                                isExtraBold = true,
                                textColor = Color.Black
                            )
                        }

                        // widget.Button
//                    AndroidView(
//                        factory = { ctx ->
//                            android.widget.TimePicker(ctx).apply {
//                                layoutParams = LinearLayout.LayoutParams(140, 140)
//                                setOnClickListener {
//                                    //  state.value++
//                                }
//                            }
//                        }, /*modifier = Modifier.padding(8.dp)*/
//                    )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        SimpleText(
                            textSize = 10,
                            text = "Closing Time",
                            isExtraBold = true
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
                                    // on below line we are getting
                                    // the instance of our calendar.
                                    val c = Calendar.getInstance()

                                    // on below line we are getting our hour, minute.
                                    val hour = c.get(Calendar.HOUR_OF_DAY)
                                    val minute = c.get(Calendar.MINUTE)

                                    // on below line we are initializing
                                    // our Time Picker Dialog
                                    val timePickerDialog = TimePickerDialog(
                                        context,
                                        { view, hourOfDay, minute ->
                                            // on below line we are setting selected
                                            // time in our text view.
                                            val timeseet = "$hourOfDay:$minute"

                                            // TODO Auto-generated method stub
                                            // TODO Auto-generated method stub
                                            var hours = hourOfDay
                                            val minutes = minute
                                            var timeSet = ""

                                            Log.d(
                                                "huiop",
                                                "AddShopDetailsContent: $hours     $hourOfDay"
                                            )
                                            if (hourOfDay > 12) {
                                                hours -= 12
                                                timeSet = "PM"
                                            } else if (hours === 0) {
                                                hours += 12
                                                timeSet = "AM"
                                            } else if (hours === 12) {
                                                timeSet = "PM"
                                            } else {
                                                timeSet = "AM"
                                            }

                                            var min: String? = ""
                                            if (minutes < 10) min = "0$minutes" else min =
                                                java.lang.String.valueOf(minutes)

                                            // Append in a StringBuilder
                                            changeHolidayClosingTime(
                                                /*    StringBuilder()
                                                        .append(hours)
                                                        .append(':')
                                                        .append(min)
                                                        .append(" ")
                                                        .append(timeSet)
                                                        .toString()*/timeseet
                                            )

                                            // Append in a StringBuilder

                                        },
                                        hour,
                                        minute,
                                        false
                                    )
                                    // at last we are calling show to
                                    // display our time picker dialog.
                                    timePickerDialog.show()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SimpleText(
                                textSize = 11,
                                text = uiState.holiday_closing_time,
                                isExtraBold = true,
                                textColor = Color.Black
                            )
                        }

                        /* // widget.Button
                         AndroidView(
                             factory = { ctx ->
                                 android.widget.TimePicker(ctx).apply {
                                     layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                                     setOnClickListener {
                                         //  state.value++
                                     }
                                 }
                             }, *//*modifier = Modifier.padding(8.dp)*//*
                    )*/
                    }
                }
            }

            AnimatedVisibility(
                visible = uiState.saturdayState,
                enter = fadeIn(animationSpec = tween(1000)) +
                        expandVertically(
                            animationSpec = tween(
                                1500/*,
                                    easing = BounceInterpolator()*/
                            )
                        ),
                exit = fadeOut(animationSpec = tween(1000)) +
                        shrinkVertically(
                            animationSpec = tween(
                                1500/*,
                                    easing = BounceInterpolator()*/
                            )
                        )
            ) {

                Row(
                    modifier = Modifier
                        .padding(bottom = 24.dp, top = 16.dp)
                        .fillMaxWidth()
                ) {

                    val context = LocalContext.current
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f),
                    ) {
                        SimpleText(
                            textSize = 10,
                            text = "Saturday Opening Time",
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
                                    // on below line we are getting
                                    // the instance of our calendar.
                                    val c = Calendar.getInstance()

                                    // on below line we are getting our hour, minute.
                                    val hour = c.get(Calendar.HOUR_OF_DAY)
                                    val minute = c.get(Calendar.MINUTE)

                                    // on below line we are initializing
                                    // our Time Picker Dialog
                                    val timePickerDialog = TimePickerDialog(
                                        context,
                                        { view, hourOfDay, minute ->
                                            // on below line we are setting selected
                                            // time in our text view.
                                            val timeseet = "$hourOfDay:$minute"

                                            // TODO Auto-generated method stub
                                            // TODO Auto-generated method stub
                                            var hours = hourOfDay
                                            val minutes = minute
                                            var timeSet = ""

                                            Log.d(
                                                "huiop",
                                                "AddShopDetailsContent: $hours     $hourOfDay"
                                            )
                                            if (hourOfDay > 12) {
                                                hours -= 12
                                                timeSet = "PM"
                                            } else if (hours === 0) {
                                                hours += 12
                                                timeSet = "AM"
                                            } else if (hours === 12) {
                                                timeSet = "PM"
                                            } else {
                                                timeSet = "AM"
                                            }

                                            var min: String? = ""
                                            if (minutes < 10) min = "0$minutes" else min =
                                                java.lang.String.valueOf(minutes)

                                            // Append in a StringBuilder
                                            changeSaturdayOpeningTime(
                                                /*     StringBuilder()
                                                         .append(hours)
                                                         .append(':')
                                                         .append(min)
                                                         .append(" ")
                                                         .append(timeSet)
                                                         .toString()*/timeseet
                                            )
                                            // Append in a StringBuilder

                                        },
                                        hour,
                                        minute,
                                        false
                                    )
                                    // at last we are calling show to
                                    // display our time picker dialog.
                                    timePickerDialog.show()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SimpleText(
                                textSize = 11,
                                text = uiState.saturday_opening_time,
                                isExtraBold = true,
                                textColor = Color.Black
                            )
                        }

                        // widget.Button
//                    AndroidView(
//                        factory = { ctx ->
//                            android.widget.TimePicker(ctx).apply {
//                                layoutParams = LinearLayout.LayoutParams(140, 140)
//                                setOnClickListener {
//                                    //  state.value++
//                                }
//                            }
//                        }, /*modifier = Modifier.padding(8.dp)*/
//                    )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        SimpleText(
                            textSize = 10,
                            text = "Closing Time",
                            isExtraBold = true
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
                                    // on below line we are getting
                                    // the instance of our calendar.
                                    val c = Calendar.getInstance()

                                    // on below line we are getting our hour, minute.
                                    val hour = c.get(Calendar.HOUR_OF_DAY)
                                    val minute = c.get(Calendar.MINUTE)

                                    // on below line we are initializing
                                    // our Time Picker Dialog
                                    val timePickerDialog = TimePickerDialog(
                                        context,
                                        { view, hourOfDay, minute ->
                                            // on below line we are setting selected
                                            // time in our text view.
                                            val timeseet = "$hourOfDay:$minute"

                                            // TODO Auto-generated method stub
                                            // TODO Auto-generated method stub
                                            var hours = hourOfDay
                                            val minutes = minute
                                            var timeSet = ""

                                            Log.d(
                                                "huiop",
                                                "AddShopDetailsContent: $hours     $hourOfDay"
                                            )
                                            if (hourOfDay > 12) {
                                                hours -= 12
                                                timeSet = "PM"
                                            } else if (hours === 0) {
                                                hours += 12
                                                timeSet = "AM"
                                            } else if (hours === 12) {
                                                timeSet = "PM"
                                            } else {
                                                timeSet = "AM"
                                            }

                                            var min: String? = ""
                                            if (minutes < 10) min = "0$minutes" else min =
                                                java.lang.String.valueOf(minutes)

                                            // Append in a StringBuilder
                                            changeSaturdayClosingTime(
                                                /*       StringBuilder()
                                                           .append(hours)
                                                           .append(':')
                                                           .append(min)
                                                           .append(" ")
                                                           .append(timeSet)
                                                           .toString()*/timeseet
                                            )

                                            // Append in a StringBuilder

                                        },
                                        hour,
                                        minute,
                                        false
                                    )
                                    // at last we are calling show to
                                    // display our time picker dialog.
                                    timePickerDialog.show()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SimpleText(
                                textSize = 11,
                                text = uiState.saturday_closing_time,
                                isExtraBold = true,
                                textColor = Color.Black
                            )
                        }

                        /* // widget.Button
                         AndroidView(
                             factory = { ctx ->
                                 android.widget.TimePicker(ctx).apply {
                                     layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                                     setOnClickListener {
                                         //  state.value++
                                     }
                                 }
                             }, *//*modifier = Modifier.padding(8.dp)*//*
                    )*/
                    }
                }
            }

            AnimatedVisibility(
                visible = uiState.sundayState,
                enter = fadeIn(animationSpec = tween(1000)) +
                        expandVertically(
                            animationSpec = tween(
                                1500/*,
                                    easing = BounceInterpolator()*/
                            )
                        ),
                exit = fadeOut(animationSpec = tween(1000)) +
                        shrinkVertically(
                            animationSpec = tween(
                                1500/*,
                                    easing = BounceInterpolator()*/
                            )
                        )
            ) {

                Row(
                    modifier = Modifier
                        .padding(bottom = 24.dp, top = 16.dp)
                        .fillMaxWidth()
                ) {

                    val context = LocalContext.current
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f),
                    ) {
                        SimpleText(
                            textSize = 10,
                            text = "Sunday Opening Time",
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
                                    // on below line we are getting
                                    // the instance of our calendar.
                                    val c = Calendar.getInstance()

                                    // on below line we are getting our hour, minute.
                                    val hour = c.get(Calendar.HOUR_OF_DAY)
                                    val minute = c.get(Calendar.MINUTE)

                                    // on below line we are initializing
                                    // our Time Picker Dialog
                                    val timePickerDialog = TimePickerDialog(
                                        context,
                                        { view, hourOfDay, minute ->
                                            // on below line we are setting selected
                                            // time in our text view.
                                            val timeseet = "$hourOfDay:$minute"

                                            // TODO Auto-generated method stub
                                            // TODO Auto-generated method stub
                                            var hours = hourOfDay
                                            val minutes = minute
                                            var timeSet = ""

                                            Log.d(
                                                "huiop",
                                                "AddShopDetailsContent: $hours     $hourOfDay"
                                            )
                                            if (hourOfDay > 12) {
                                                hours -= 12
                                                timeSet = "PM"
                                            } else if (hours === 0) {
                                                hours += 12
                                                timeSet = "AM"
                                            } else if (hours === 12) {
                                                timeSet = "PM"
                                            } else {
                                                timeSet = "AM"
                                            }

                                            var min: String? = ""
                                            if (minutes < 10) min = "0$minutes" else min =
                                                java.lang.String.valueOf(minutes)

                                            // Append in a StringBuilder
                                            changeSundayOpeningTime(
                                                /* StringBuilder()
                                                     .append(hours)
                                                     .append(':')
                                                     .append(min)
                                                     .append(" ")
                                                     .append(timeSet)
                                                     .toString()*/timeseet
                                            )
                                            // Append in a StringBuilder

                                        },
                                        hour,
                                        minute,
                                        false
                                    )
                                    // at last we are calling show to
                                    // display our time picker dialog.
                                    timePickerDialog.show()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SimpleText(
                                textSize = 11,
                                text = uiState.sunday_opening_time,
                                isExtraBold = true,
                                textColor = Color.Black
                            )
                        }

                        // widget.Button
//                    AndroidView(
//                        factory = { ctx ->
//                            android.widget.TimePicker(ctx).apply {
//                                layoutParams = LinearLayout.LayoutParams(140, 140)
//                                setOnClickListener {
//                                    //  state.value++
//                                }
//                            }
//                        }, /*modifier = Modifier.padding(8.dp)*/
//                    )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        SimpleText(
                            textSize = 10,
                            text = "Closing Time",
                            isExtraBold = true
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
                                    // on below line we are getting
                                    // the instance of our calendar.
                                    val c = Calendar.getInstance()

                                    // on below line we are getting our hour, minute.
                                    val hour = c.get(Calendar.HOUR_OF_DAY)
                                    val minute = c.get(Calendar.MINUTE)

                                    // on below line we are initializing
                                    // our Time Picker Dialog
                                    val timePickerDialog = TimePickerDialog(
                                        context,
                                        { view, hourOfDay, minute ->
                                            // on below line we are setting selected
                                            // time in our text view.
                                            val timeseet = "$hourOfDay:$minute"

                                            // TODO Auto-generated method stub
                                            // TODO Auto-generated method stub
                                            var hours = hourOfDay
                                            val minutes = minute
                                            var timeSet = ""

                                            Log.d(
                                                "huiop",
                                                "AddShopDetailsContent: $hours     $hourOfDay"
                                            )
                                            if (hourOfDay > 12) {
                                                hours -= 12
                                                timeSet = "PM"
                                            } else if (hours === 0) {
                                                hours += 12
                                                timeSet = "AM"
                                            } else if (hours === 12) {
                                                timeSet = "PM"
                                            } else {
                                                timeSet = "AM"
                                            }

                                            var min: String? = ""
                                            if (minutes < 10) min = "0$minutes" else min =
                                                java.lang.String.valueOf(minutes)

                                            // Append in a StringBuilder
                                            changeSundayClosingTime(
                                                /*  StringBuilder()
                                                      .append(hours)
                                                      .append(':')
                                                      .append(min)
                                                      .append(" ")
                                                      .append(timeSet)
                                                      .toString()*/timeseet
                                            )

                                            // Append in a StringBuilder

                                        },
                                        hour,
                                        minute,
                                        false
                                    )
                                    // at last we are calling show to
                                    // display our time picker dialog.
                                    timePickerDialog.show()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SimpleText(
                                textSize = 11,
                                text = uiState.sunday_closing_time,
                                isExtraBold = true,
                                textColor = Color.Black
                            )
                        }

                        /* // widget.Button
                         AndroidView(
                             factory = { ctx ->
                                 android.widget.TimePicker(ctx).apply {
                                     layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                                     setOnClickListener {
                                         //  state.value++
                                     }
                                 }
                             }, *//*modifier = Modifier.padding(8.dp)*//*
                    )*/
                    }
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
/*
            Column(
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

@Preview(showBackground = true)
@Composable
fun AddShopContentPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        AddWaterVendorDetailsContent(
            uiState = AddWaterVendorUiState(),
            onCategorySelected = { _, _ -> },
//            onChangeShopLocation = {},
            onShopNameChanged = {},
            onShopPhoneOneChanged = {},
            onShopPhoneTwoChanged = {},
            onAddShopLocation = {},
            onBranchManagerChanged = {},
            changeBranchManTextState = {},
            addBranchName = {},
            changeSaturdayState = {},
            changeSundayState = {},
            changeHolidayState = {},
            changeOperationHrState = {},
            changeHolidayClosingTime = {},
            changeHolidayOpeningTime = {},
            changeWeekdayOpeningTime = {},
            changeWeekdayClosingTime = {},
            changeSaturdayOpeningTime = {},
            changeSaturdayClosingTime = {},
            changeSundayOpeningTime = {},
            changeSundayClosingTime = {},
            changeShopType = {},
            onDescriptionChanged = {}
        )
    }
}