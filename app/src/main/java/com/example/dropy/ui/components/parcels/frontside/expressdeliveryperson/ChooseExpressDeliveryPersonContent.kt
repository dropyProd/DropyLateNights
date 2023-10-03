package com.example.dropy.ui.components.parcels.frontside.expressdeliveryperson

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.*
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.theme.LightBlue


@Composable
fun ChooseExpressDeliveryPersonContent(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.weight(1f)
        ){
            MapComponent()
        }
        val selectedDeliveryPerson = remember {
            mutableStateOf<DeliveryPersonDataClass?>(null)
        }

        SelectedDeliveryPerson(selectedDeliveryPerson = selectedDeliveryPerson.value
        )
        DeliveryPersonList(
            selectDeliveryPerson = {
                selectedDeliveryPerson.value = it
            }
        )
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    Toast
                        .makeText(
                            context, "you selected ${selectedDeliveryPerson.value?.firstName}",
                            Toast.LENGTH_LONG
                        ).show()
                }
        ){
            TotallyRoundedButton(
                icon = null,
                buttonText = "continue",
                textFontSize = 12,
                textColor = Color.White,
                backgroundColor = LightBlue,
                widthFraction = 0.5,
                action = {}
            )
        }
    }
}
@Composable
fun SelectedDeliveryPerson(selectedDeliveryPerson: DeliveryPersonDataClass?){
    if (selectedDeliveryPerson != null){
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedDeliveryPerson.userProfile == null){
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "profile photo",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )
                    }else{
                        Image(
                            painter = selectedDeliveryPerson.userProfile,
                            contentDescription = "profile photo",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    SimpleText(
                        textSize = 15,
                        text = "${selectedDeliveryPerson.firstName} ${selectedDeliveryPerson.lastName}",
                        isUppercase = true,
                        isBold = true,
                        padding = 2
                    )
                    SimpleText(
                        textSize = 15,
                        text = selectedDeliveryPerson.vehicleDescription,
                        textColor = LightBlue,
                        isUppercase = true,
                        isBold = true,
                        isExtraBold = false,
                        padding = 2
                    )
                    SimpleText(
                        textSize = 15,
                        text = "${selectedDeliveryPerson.totalRides} rides",
                        textColor = LightBlue,
                        isUppercase = true,
                        isBold = true,
                        isExtraBold = false,
                        padding = 2
                    )
                    SimpleText(
                        textSize = 12,
                        text = "${selectedDeliveryPerson.totalKilometres} km covered",
                        textColor = Color.LightGray,
                        isUppercase = false,
                        isBold = true,
                        isExtraBold = false,
                        padding = 2
                    )
                }
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    VerticalRating(rating = selectedDeliveryPerson.averageRating )
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                    ){
                        Time(timeInMin = selectedDeliveryPerson.etaInMinutes.toString())
                    }
                }
            }
            Box(
                modifier = Modifier
                    .offset(y = (-8).dp, x = 16.dp)
            ){
                StyledText(
                    backgroundColor = Color.White,
                    borderColor = Color.LightGray,
                    textSize = 12,
                    text = "selected",
                    isUppercase = true,
                    isBold = false
                )
            }
        }
    }else{
        SimpleText(
            textSize = 15,
            text = "please select a delivery person",
            textColor = LightBlue,
            isUppercase = true,
            isBold = true,
            isExtraBold = false,
            padding = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseExpressDeliveryPersonContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ChooseExpressDeliveryPersonContent()
    }
}
