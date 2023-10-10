package com.example.dropy.ui.screens.myTruckEditDetails

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.frontside.dropdownRounded
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.myTrucks.MyTrucksUiState
import com.example.dropy.ui.theme.LightBlue

@Composable
fun MyTruckEditDetailsContent(
    myTrucksUiState: MyTrucksUiState,
    myTruckEditDetailsUiState: MyTruckEditDetailsUiState,
    changeActiveState: (Boolean) -> Unit,
    onAddShopCoverPhoto: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ClippedHeader(title = "TRUCK DETAILS")

            Text(
                text = "STATUS",
                color = Color.Black,
                fontSize = 18.sp,
//                        fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                letterSpacing = (-0.48).sp,
                lineHeight = 35.sp
            )
        }


        val backgroundColorClose by animateColorAsState(
            if (myTruckEditDetailsUiState.active) Color.LightGray else Color.Black
        )
        val backgroundColorOpen by animateColorAsState(
            if (myTruckEditDetailsUiState.active) Color.Black else Color.LightGray
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 31.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var mExpanded by remember { mutableStateOf(false) }
                var text by remember {
                    mutableStateOf("")
                }

                val list: MutableList<String> = mutableListOf()


                Text(
                    text = "TRUCK",
                    color = Color.Black,
                    fontSize = 18.sp,
//                        fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 35.sp
                )


                dropdownRounded(
                    text = "KBB 374K",
                    clicked = {
                        mExpanded = true
                    },
                    color = Color(0xFFC2F8FF),
                    bordercolor = Color.Transparent,
                    contentColor = Color.Black,
                    spacearound = 12,
                    modifier = Modifier.padding(start = 8.dp)
                )
                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(200.dp)
                    /*   .verticalScroll(rememberScrollState())*/
                ) {
                    list.forEach { label ->
                        DropdownMenuItem(
                            onClick = {
                                text = label
                                mExpanded = false
                            }, modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                        ) {
                            label.let {
                                androidx.compose.material3.Text(
                                    text = (it).toUpperCase(),
                                    fontSize = 9.sp,
                                    modifier = Modifier.padding(bottom = 7.dp),
                                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                    }
                }


            }
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
                    checked = myTruckEditDetailsUiState.active,
                    onCheckedChange = changeActiveState,
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
            modifier = Modifier
                .padding(bottom = 32.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(LightBlue)
                    .fillMaxWidth()
                    .aspectRatio(2 / 1f)
            ){
                if (myTruckEditDetailsUiState.shopCoverPhoto == null){
                    LoadImage()
                }else{
                    Image(
                        bitmap = myTruckEditDetailsUiState.shopCoverPhoto,
                        contentDescription = "image logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                IconButton(
                    onClick = { onAddShopCoverPhoto() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2 / 1f)
                        .background(Color(255, 255, 255, 77))
                    ,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add icon",
                        modifier = Modifier
                            .size(24.dp)
                        ,
                        tint = Color.White
                    )
                }
            }
        /*    SimpleText(
                textSize = 10,
                text = "Upload your watertruck feature photo",
                isUppercase = false,
                isBold = true,
                padding = 8,
                font = Font(R.font.axiformabold)
            )*/
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
                    value = myTruckEditDetailsUiState.licensePlate,
                    onValueChange = {/* onShopPhoneOneChanged(it)*/ },
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
        }
    }
}

@Preview
@Composable
fun demo() {
    MyTruckEditDetailsContent(
        myTrucksUiState = MyTrucksUiState(),
        myTruckEditDetailsUiState = MyTruckEditDetailsUiState(),
        changeActiveState = {},
        onAddShopCoverPhoto = {}
    )
}