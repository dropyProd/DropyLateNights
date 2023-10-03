package com.example.dropy.ui.components.parcels.frontside.expressdeliveryperson

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun DeliveryPersonList(
    selectDeliveryPerson:(DeliveryPersonDataClass)->Unit
){
    @Suppress("RemoveExplicitTypeArguments")val deliveryPersons = mutableListOf<DeliveryPersonDataClass>(
        DeliveryPersonDataClass(
            userProfile = null,
            firstName = "claud1",
            lastName = "odhis",
            vehicleDescription = "blue boxer motorcycle",
            totalRides = 234,
            totalKilometres = 1234,
            averageRating = 4.3,
            etaInMinutes = 30
        ),
        DeliveryPersonDataClass(
            userProfile = null,
            firstName = "claud2",
            lastName = "odhis",
            vehicleDescription = "blue boxer motorcycle",
            totalRides = 234,
            totalKilometres = 1234,
            averageRating = 4.3,
            etaInMinutes = 30
        ),
        DeliveryPersonDataClass(
            userProfile = null,
            firstName = "claud3",
            lastName = "odhis",
            vehicleDescription = "blue boxer motorcycle",
            totalRides = 234,
            totalKilometres = 1234,
            averageRating = 4.3,
            etaInMinutes = 30
        ),
        DeliveryPersonDataClass(
            userProfile = null,
            firstName = "claud4",
            lastName = "odhis",
            vehicleDescription = "blue boxer motorcycle",
            totalRides = 234,
            totalKilometres = 1234,
            averageRating = 4.3,
            etaInMinutes = 30
        ),
    )

    val selected = remember {
        mutableStateOf(-1)
    }

    fun selectPerson(index:Int){
        val deliveryPerson = deliveryPersons[index]
        selectDeliveryPerson(deliveryPerson)
        selected.value = index
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        StyledText(
            backgroundColor = DropyYellow,
            textSize = 15,
            text = "my favourites",
            isUppercase = true,
            isBold = true
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ){
            items(items = deliveryPersons, itemContent = {item->
                val index = deliveryPersons.indexOf(item)
                Box(
                    modifier = Modifier
                        .clickable { selectPerson(index = index) }
                ) {
                    DeliveryPerson(
                        userProfile = item.userProfile,
                        name = item.firstName,
                        etaInMinutes = item.etaInMinutes ,
                        isSelected = index == selected.value
                    )
                }
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DeliveryPersonListPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        DeliveryPersonList(
            selectDeliveryPerson = {}
        )
    }
}