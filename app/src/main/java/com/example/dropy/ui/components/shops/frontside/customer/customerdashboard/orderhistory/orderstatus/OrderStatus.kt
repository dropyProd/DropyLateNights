package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory.orderstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.ProfilePic
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue


@Composable
fun OrderStatus(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        OrderDetails(
            orderNumber = "1234",
            eta = 15
        )
        Box(modifier = Modifier.fillMaxHeight(0.5f)
        ){ MapComponent() }

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp, top = 10.dp)

                .clip(shape = RoundedCornerShape(20.dp))

              

        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Card(elevation = 1.dp) {
                Column(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, start = 2.dp)) {
                    TrackingStatus(
                        message = "Your delivery guy is heading to pick up your order",
                        buttonText = "on route to pickup",
                        success = true
                    )
                    TrackingStatus(
                        message = "Your delivery guy picked up your order",
                        buttonText = "order picked",
                        success = true
                    )
                    TrackingStatus(
                        message = "Your delivery guy has arrived",
                        buttonText = "order has arrived",
                        success = false
                    )

                }


            }
            

        }
        DeliveryGuyContact(
            name = "name",
            onCallDeliveryPerson = {}
        )
    }
}

@Composable
fun OrderDetails(
    orderNumber:String,
    eta:Int,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            SimpleText(
                text = "Track your order",
                textSize = 21,
                isUppercase = true,
                isExtraBold = true,

            )

            Spacer(modifier = Modifier.height(10.dp))
            SimpleText(
                text = "order # $orderNumber",
                textSize = 14,
                isUppercase = true,
                isExtraBold = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            SimpleText(
                text = "Realtime tracking of your order",
            )
        }
        Column(
            modifier = Modifier
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SimpleText(
                text = "eta",
                isUppercase = true,
                textSize = 21,
                isExtraBold = true
            )
            Text(
                text = "Estimated time of arrival",
                fontSize = 9.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .sizeIn(maxWidth = 80.dp)
                ,
            )
            StyledText(
                backgroundColor = DropyYellow,
                textSize = 12,
                text = "$eta minutes",
                textColor = Color.Black,
                fontFamily = R.font.axiformablack

            )
        }
    }
}



@Composable
fun DeliveryGuyContact(
    name:String,
    profilePicUrl:String? = null,
    onCallDeliveryPerson:()->Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(255, 204, 0, 77))
        ,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (profilePicUrl == null){
                ProfilePic()
            }else{
                Box(modifier = Modifier.size(64.dp)){
                    LoadImage(imageUrl = profilePicUrl)
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = name.uppercase(),
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
                Text(
                    text = "Delivery person",
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
            Row(
                modifier = Modifier
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { onCallDeliveryPerson() },
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(27, 252, 19))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "",
                        modifier = Modifier.size(24 .dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TrackingStatus(message:String,buttonText:String,success:Boolean){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .clip(CircleShape)
                    .background(
                        if (success) {
                            Color(5, 154, 0)
                        } else {
                            Color.White
                        }
                    )
                    .border(
                        width = 2.dp, shape = CircleShape, color = if (success) {
                            Color.Transparent
                        } else {
                            Color.LightGray
                        }
                    )
            ) {
                Icon(
                    imageVector = (if (success){Icons.Filled.Done}else{Icons.Filled.FiberManualRecord}),
                    contentDescription = "status",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(16.dp)
                    ,
                    tint = if (success){
                        Color.White}
                    else{
                        Color.LightGray
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                ,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Dot(color = if (success){Color(5, 154, 0)}else{Color.LightGray})
                Dot(color = if (success){Color(5, 154, 0)}else{Color.LightGray})
                Dot(color = if (success){Color(5, 154, 0)}else{Color.LightGray})
                Dot(color = if (success){Color(5, 154, 0)}else{Color.LightGray})
            }

//            Column(
//                modifier = Modifier
//                    .fillMaxHeight(1f)
//                ,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ){
//                DotsColumn(color = Color(5, 154, 0))
//            }
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                ,

            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (success) {
                            Color(5, 154, 0)
                        } else {
                            Color.LightGray
                        }
                    )
            ) {
                Text(
                    text = buttonText.uppercase(),
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                    ,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,



                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "02:30 HRS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp)
                ,
            )
        }
    }
}
@Composable
fun Dot(color:Color){
    Spacer(modifier = Modifier
        .padding(bottom = 2.dp)
        .size(4.dp)
        .clip(CircleShape)
        .background(color)
    )
}

@Preview(showBackground = true)
@Composable
fun TrackingPageContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        OrderStatus()
    }
}