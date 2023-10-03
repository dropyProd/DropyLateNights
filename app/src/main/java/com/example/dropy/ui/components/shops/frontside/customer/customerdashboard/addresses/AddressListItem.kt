package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.addresses

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.LightBlue
import java.util.*

@Composable
fun AddressListItem(
    locationTag:String,
    placeName:String,
    onEditClicked:()->Unit,
    onDeleteClicked:()->Unit
){
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {

        SimpleText(
            text = locationTag.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            isBold = true,
            textSize = 14
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SimpleText(text = placeName)
            Row {
                Box(modifier = Modifier
                    .clickable { onEditClicked() }
                ){
                    StyledText(
                        backgroundColor = LightBlue,
                        textSize = 9,
                        text = "edit",
                        isUppercase = true,
                        textColor = Color.White
                    )
                }
                IconButton(
                    onClick = { onDeleteClicked() },
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = "cancel icon",
                        tint = Color.Red
                    )
                }
            }
        }
        Spacer(modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddressListItemPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AddressListItem(
            locationTag = "tag",
            placeName = "some long place name",
            onDeleteClicked = {},
            onEditClicked = {}
        )
    }
}