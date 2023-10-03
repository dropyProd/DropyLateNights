package com.example.dropy.ui.components.shops.shopscommons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun ProductAndShopSearchBar(
    isClicked: (() -> Unit)? = null,
    width: Float = .99f,
    searchItem: ((String) -> Unit)?=null
) {
    val searchText = remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier
            .clickable {
                if (isClicked != null) {
                    isClicked()
                }
            }
            .fillMaxWidth(width),


        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(
                    1.dp,
                    Color.LightGray.copy(.4f),
                    shape = RoundedCornerShape(50)
                )
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isClicked != null) {
                Row(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    searchMic(searchClicked = {
                        if (!searchText.value.text.equals("")) {
                            searchItem?.let { it(searchText.value.text) }
                        }
                    })
                    SimpleText(
                        text = searchText.value.text,
                        fontWeight = FontWeight.Normal,
                        font = Font(R.font.axiformaregular),
                        textColor = Color.Black,
                        textSize = 12
                    )
                    scancamera()
                }
            } else {
                TextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    leadingIcon = {
                        searchMic(searchClicked = {
                            if (!searchText.value.text.equals("")) {
                                searchItem?.let { it(searchText.value.text) }
                            }
                        })
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        if (!searchText.value.text.equals("")) {
                            searchItem?.let { it(searchText.value.text) }
                        }
                    }),
                    trailingIcon = {
                        scancamera()
                    },
                    textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular)))
                )
            }
        }
    }

}

@Composable
private fun searchMic(searchClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
//                        Color(255, 204, 0),
                        Color.Transparent,
                        Color.White,
                    )
                )
            )
            .wrapContentWidth()
            .fillMaxHeight()
            .padding(start = 15.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {

        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            modifier = Modifier
                .height(height = 19.dp)
                .clickable {
                    searchClicked()
                }
        )


        Row(
            modifier = Modifier
                .width(1.dp)
                .height(25.dp)
                .background(Color.LightGray)
        ) {

        }

        Icon(
            imageVector = Icons.Filled.Mic,
            contentDescription = "",
            modifier = Modifier
                .height(19.dp)
        )

    }
}

@Composable
private fun scancamera() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 15.dp)
            .wrapContentSize()
    ) {
//        Icon(
//            Icons.Filled.PhotoCamera,
//            contentDescription = "",
//            modifier = Modifier.size(20.dp, 17.dp)
//        )
        Row(
            modifier = Modifier
                .width(1.dp)
                .height(25.dp)
                .background(Color.LightGray)
        ) {

        }
        Icon(
            Icons.Filled.QrCodeScanner,
            contentDescription = "",
            modifier = Modifier.size(20.dp, 17.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    DropyTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            ProductAndShopSearchBar() {}
        }
    }
}