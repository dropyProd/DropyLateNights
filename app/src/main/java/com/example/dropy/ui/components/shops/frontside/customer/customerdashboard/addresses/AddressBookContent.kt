package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.addresses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.addressbook.AddressBookUiState
import com.example.dropy.ui.theme.LightBlue

@Composable
fun AddressBookContent(
    uiState: AddressBookUiState,
    onEditAddressClicked:(addressId:Int)->Unit,
    onDeleteAddressClicked:(addressId:Int)->Unit,
    onAddAddressClicked:()->Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxHeight(0.2f),
            contentAlignment = Alignment.Center
        ){
            MapComponent()
        }
        SimpleText(
            text = "My Addresses",
            textSize = 16,
            isBold = true
        )
        Spacer(modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
        )
        AddressList(
            addressList=uiState.addressList,
            onEditAddressClicked = {onEditAddressClicked(it)},
            onDeleteAddressClicked = {onDeleteAddressClicked(it)}
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onAddAddressClicked() },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add icon",
                    tint = Color.White
                )
            }
            SimpleText(text = "Add address")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddressBookContentPreview() {
    Column(Modifier.fillMaxSize()) {
        AddressBookContent(
            uiState = AddressBookUiState(),
            onDeleteAddressClicked = {},
            onEditAddressClicked = {},
            onAddAddressClicked = {}
        )
    }
}