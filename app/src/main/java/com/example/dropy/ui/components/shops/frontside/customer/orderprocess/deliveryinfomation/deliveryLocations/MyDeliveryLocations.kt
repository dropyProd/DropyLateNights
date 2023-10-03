package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliveryLocations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import com.example.dropy.R
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliveryLocationItem
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress.SingleAddressUiState

@Composable
fun MyDeliveryLocations(
    singleAddressUiState: SingleAddressUiState,
    clicked: (String) -> Unit,
    choose: (AddressDataClass) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        /*      Text(
                  text = "My Delivery Locations",
                  fontWeight = FontWeight.SemiBold,
                  modifier = Modifier
                      .fillMaxWidth(),
                  fontFamily = FontFamily(Font(R.font.axiformasemibold))
              )*/

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(21.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SimpleText(
                text = "My locations",
                font = Font(R.font.axiformasemibold),
                fontWeight = FontWeight.SemiBold
            )
            val selected = remember {
                mutableStateOf(0)
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                content = {
                    itemsIndexed(items = singleAddressUiState.locationTagList) { index, item ->
                        item.customTag?.let {
                            deliveryLocationItem(
                                text = it,
                                clicked = { clicked(item.placeName) },
                                selectedIndex = selected.value,
                                index = index,
                                choose = {
                                    choose(item)
                                }
                            )
                        }
                    }
                })

        }
    }
}

@Preview(showBackground = true)
@Composable
fun screen() {
    MyDeliveryLocations(SingleAddressUiState(), {}, {})
}