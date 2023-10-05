package com.example.dropy.ui.screens.truckOrders

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TruckOrdersContent(
    navigate: () -> Unit,
    truckOrdersUiState: TruckOrdersUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ClippedHeader(
            title = "ORDER HISTORY", modifier = Modifier
                .padding(top = 22.dp)
                .align(Alignment.Start)
        )

        Row(
            modifier = Modifier.padding(top = 29.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(27.dp)
        ) {
            Row(
                modifier = Modifier
                    .width(245.dp)
                    .wrapContentHeight()
                    .background(Color.Transparent)
                    .border(
                        shape = RoundedCornerShape(80.dp),
                        width = 1.dp,
                        color = Color(0xFFD1D1D1)
                    )
                    .padding(top = 3.dp, bottom = 3.dp, start = 12.dp, end = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = "Search",
                    onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

            }

            Row(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(3.dp))
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.filternew),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentScale = ContentScale.FillWidth
                )
            }

        }


        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 14.dp)
            .padding(top = 21.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp), content = {
                itemsIndexed(truckOrdersUiState.orders) { index, item ->
                    val backgroundColor = if (index % 2 == 0)
                        Color.Transparent
                    else Color(0xFFF5F5F5)
                    truckOrderItem(
                        navigate = { navigate() },
                        color = backgroundColor,
                        getIndividualOrdersRes = item,
                        truckOrdersUiState = truckOrdersUiState
                    )
                }
            })

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun truckOrderItem(
    navigate: () -> Unit,
    color: Color,
    getIndividualOrdersRes: GetIndividualOrdersResItem,
    truckOrdersUiState: TruckOrdersUiState
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = color, shape = RoundedCornerShape(12.dp))
        .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
        .clickable { navigate() }
    ) {

        Column(modifier = Modifier.padding(top = 9.dp)) {
            Image(
                painter = painterResource(id = R.drawable.shop1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
            )

            Text(
                text = getIndividualOrdersRes.customer.first_name + " " + getIndividualOrdersRes.customer.last_name,
                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.43).sp,
                lineHeight = 17.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp, start = 22.dp)
            )
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = "Order NO #${getIndividualOrdersRes.tracking_id}",
                fontSize = 11.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.53).sp,
                lineHeight = 21.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 18.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 1.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFF979797),
                        shape = RoundedCornerShape(9.dp)
                    )
                    .padding(horizontal = 6.dp)
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getIndividualOrdersRes.delivery_status.equals("P")) "NOT STARTED" else if (getIndividualOrdersRes.delivery_status.equals(
                            "S"
                        )
                    ) "ON THE WAY" else "DELIVERED",
                    fontSize = 7.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.34).sp,
                    lineHeight = 13.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 9.dp,
                        top = 5.dp
//                        bottom = 1.dp
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFFC2F8FF),
                        shape = RoundedCornerShape(9.dp)
                    )
                    .padding(horizontal = 6.dp)
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getIndividualOrdersRes.delivery_status.equals("P")) "NOT STARTED" else if (getIndividualOrdersRes.delivery_status.equals(
                            "S"
                        )
                    ) "ON THE WAY" else "DELIVERED",
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = 9.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 4.dp
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {

            /*    val date = remember{
                    mutableStateOf("")
                }
                getIndividualOrdersRes.timestamp.forEachIndexed { index, time ->
                    if (index in 0..9){
                        date.value += time
                    }
                }
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val newDate = formatter.parse(date.value)
                val formatterNew = DateTimeFormatter.ofPattern("dd/MMM/yy")
                val formatDate = formatterNew.format(newDate)
    */

            Text(
                text = "formatDate",
                fontSize = 8.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformamedium)
                ),
                letterSpacing = (-0.38).sp,
                lineHeight = 15.sp,
                color = Color(0xFF979797),
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(
                text = truckOrdersUiState.truckDetails?.capacity.toString() + "LT",
                fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.82).sp,
                lineHeight = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp, top = 5.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 17.dp, end = 7.dp)
                    .width(68.dp)
                    .height(18.dp)
                    .background(
                        color = Color(0xFF02CBE3),
                        shape = RoundedCornerShape(12.dp)
                    )
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = "ETA 12 HRS",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 4.dp,
                        top = 4.dp,
                        bottom = 1.dp
                    )
                )
            }
        }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun demo() {
    TruckOrdersContent(navigate = {}, truckOrdersUiState = TruckOrdersUiState())
}