package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.EditLocation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dropy.R
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.textfield.sample.AutoCompleteObjectSample

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SelectDeliveryLocation(
    currentAddress: AddressDataClass? = null,
    taggedAddresses: List<AddressDataClass>,
    autompleteLocations: List<AddressDataClass>,
    selectTaggedAddress: (address: AddressDataClass) -> Unit,
    editTaggedLocation: (address: AddressDataClass) -> Unit,
    onMyLocationClicked: () -> Unit,
    onEditDeliveryLocationClicked: () -> Unit,
    onPlaceNameChanged: (String) -> Unit,
    openSearchmapdialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp),
    ) {
        SimpleText(
            text = "Deliver to", font = Font(R.font.axiformasemibold),
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f)
            ) {

                val (search, icn) = createRefs()
                Box(
                    modifier = Modifier
                        .constrainAs(search) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
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
                            .clickable { openSearchmapdialog() }
                    ) {

                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        SimpleText(
                            text = currentAddress?.placeName ?: "delivery location",
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
                Row(
                    modifier = Modifier
                        .offset(2.dp, (-5).dp)
                        .zIndex(.4f)
                        .size(23.dp)
                        .clickable { openSearchmapdialog() }
                        .clip(CircleShape)
                        .background(Color(253, 211, 19))
                        .constrainAs(icn) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }

                ) {
                    Icon(
                        imageVector = Icons.Outlined.EditLocation,
                        contentDescription = "edit location",
                        modifier = Modifier
                            .padding(2.dp)
                    )
                }
            }
            IconButton(
                onClick = { //onMyLocationClicked()
                    openSearchmapdialog()
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(88, 74, 255))
            ) {
                Icon(
                    imageVector = Icons.Filled.MyLocation,
                    contentDescription = "my location",
                    tint = Color.White
                )
            }
        }
/*        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            SimpleText(
                text = "My locations",
                font = Font(R.font.axiformasemibold),
                fontWeight = FontWeight.SemiBold
            )
            val selected = remember {
                mutableStateOf(0)
            }
          *//*  LazyRow {
                itemsIndexed(items = taggedAddresses, itemContent = { index, item ->
                    TaggedLocation(
                        locationName = item.locationTag ?: "tag",
                        onSelected = { *//**//*selectTaggedAddress(item)*//**//*
                                     selected.value = index},
                        onEditAddress = { editTaggedLocation(item) },
                        selectedIndex = selected.value,
                        index = index
                    )
                })
            }*//*
        }*/
    }
}

@Composable
fun TaggedLocation(
    locationName: String,
    selected: Boolean = false,
    selectedIndex: Int = 0,
    index: Int = 0,
    onSelected: () -> Unit,
    onEditAddress: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp)
                .clip(RoundedCornerShape(28.dp))
                .border(2.dp, Color(88, 74, 255), RoundedCornerShape(28.dp))
                .sizeIn(minWidth = 80.dp)
                .height(32.dp)
                .background(
                    if (selectedIndex.equals(index)) {
                        Color(88, 74, 255, 128)
                    } else Color.Transparent, RoundedCornerShape(28.dp)
                )
                .clickable { onSelected() },
            contentAlignment = Alignment.Center
        ) {
            SimpleText(
                text = locationName,
                isBold = true,
                isUppercase = true,
                textColor = if (selected) {
                    Color.White
                } else {
                    Color.Black
                }
            )
        }

        IconButton(
            onClick = { onEditAddress() },
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color(253, 211, 19))
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "edit icon",
                modifier = Modifier
                    .padding(2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectDeliveryLocationPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        val taggedAddresses = mutableListOf(
            AddressDataClass(
                latitude = 0.000,
                longitude = 0.000,
                locationTag = "home"
            ),
            AddressDataClass(
                latitude = 0.000,
                longitude = 0.000,
                locationTag = "office"
            ),
            AddressDataClass(
                latitude = 0.000,
                longitude = 0.000,
                locationTag = "other"
            )
        )

        SelectDeliveryLocation(
            taggedAddresses = taggedAddresses,
            selectTaggedAddress = {},
            editTaggedLocation = {},
            onEditDeliveryLocationClicked = {},
            onMyLocationClicked = {},
            onPlaceNameChanged = {},
            openSearchmapdialog = {},
            autompleteLocations = listOf()
        )
    }
}