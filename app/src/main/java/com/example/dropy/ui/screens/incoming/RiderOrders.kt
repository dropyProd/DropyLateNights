package com.example.dropy.ui.screens.incoming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
/*import com.example.dropy.ui.components.maps.MapsScreen*/
import com.example.dropy.R
import com.example.dropy.ui.components.order.RiderOrderItem
import com.google.android.gms.maps.model.LatLng

@Composable
fun RiderOrders(startLocale: LatLng? = null) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (mp, bdy) = createRefs()

     /*   startLocale?.let {
            MapsScreen(modifier = Modifier
                .fillMaxSize()
                .constrainAs(mp) {
                    top.linkTo(parent.top)
                }.blur(radius = 5.dp), start = it
            )
        }*/

        Column(modifier = Modifier
            .fillMaxSize()
            .constrainAs(bdy) {
                top.linkTo(parent.top, 100.dp)
            }
            .background(color = Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Text(
                    text = "INCOMING WORKS",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = "You have found some work, Please proceed",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            val list = remember {
                mutableListOf(
                    RiderOrdersPojo(
                        image = R.drawable.imgone,
                        name = "PICKUP CHIRAG",
                        price = "50/-",
                        route = "FROM: KISUMU, TO: MOI AVENUE STREET",
                        type = "RIDE",
                        distance = "3.5KM"
                    ),
                    RiderOrdersPojo(
                        image = R.drawable.imgtwo,
                        name = "WAMSO GROCERIES",
                        price = "150/-",
                        route = "FROM: KISUMU, TO: MOI AVENUE STREET",
                        type = "DELIVERY",
                        distance = "3.5KM"
                    ),
                    RiderOrdersPojo(
                        image = R.drawable.imgfrr,
                        name = "PICKUP CHIRAG",
                        price = "500/-",
                        route = "FROM: KISUMU, TO: MOI AVENUE STREET",
                        type = "RIDE",
                        distance = "3.5KM"
                    ),
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .fillMaxWidth()
            ) {
                itemsIndexed(items = list) { index, item ->
                    val show = if (index % 2 == 0) true else false
                    RiderOrderItem(show = show, riderOrdersPojo = item)
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color(0xFF584AFF)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "1/- WILL BE DEDUCTED FROM WALLET",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }

    }
}

data class RiderOrdersPojo(
    val image: Int,
    val name: String,
    val route: String,
    val price: String,
    val type: String,
    val distance: String,
)