package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.addresses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddressList(
    addressList:List<AddressListItemDataClass>,
    onEditAddressClicked:(addressId:Int)->Unit,
    onDeleteAddressClicked:(addressId:Int)->Unit

){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        addressList.forEach { item->
            AddressListItem(
                locationTag = item.locationTag,
                placeName = item.placeName,
                onDeleteClicked = {onDeleteAddressClicked(item.addressId)},
                onEditClicked = {onEditAddressClicked(item.addressId)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressListPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val addressList = mutableListOf(
            AddressListItemDataClass(
                locationTag = "tag",
                placeName = "some location name",
                addressId = 1
            ),
            AddressListItemDataClass(
                locationTag = "tag",
                placeName = "some location name",
                addressId = 1
            ),
            AddressListItemDataClass(
                locationTag = "tag",
                placeName = "some location name",
                addressId = 1
            ),
        )
        AddressList(
            addressList = addressList,
            onDeleteAddressClicked = {},
            onEditAddressClicked = {}
        )
    }
}