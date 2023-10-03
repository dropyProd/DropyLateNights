package com.example.dropy.ui.screens.waterpointOrders

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.network.models.getWaterPointOrders.GetWaterPointOrdersResItem
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader

@Composable
fun WaterpointOrdersContent(
    navigate: () -> Unit,
    navigateNewOrder: () -> Unit,
    scanQr: () -> Unit,
    waterPointOrderUiState: WaterPointOrderUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(top = 22.dp, start = 19.dp, end = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .width(17.dp)
                        .height(17.dp)
                        .background(
                            color = Color(0xFF02CBE3),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDEDEDE),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = waterPointOrderUiState.orders.size.toString(),
                        fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformaheavy)
                        ),
                        letterSpacing = (-0.48).sp,
                        lineHeight = 19.sp,
                        color = Color.White,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            top = 4.dp,
                            bottom = 3.dp
                        )
                    )
                }
                Text(
                    text = "ORDERS",
                    fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.padding()
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                Row(
                    modifier = Modifier
                        .width(96.dp)
                        .height(27.dp)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDEDEDE),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable { navigateNewOrder() },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "",
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "NEW ORDER",
                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.38).sp,
                            lineHeight = 16.sp,
                            color = Color.Black
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .width(96.dp)
                        .height(27.dp)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDEDEDE),
                            shape = RoundedCornerShape(4.dp)
                        ).clickable { scanQr() },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        Icon(
                            Icons.Filled.QrCode,
                            contentDescription = "",
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "SCAN",
                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.38).sp,
                            lineHeight = 16.sp,
                            color = Color.Black
                        )
                    }
                }
            }

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 15.dp, end = 14.dp)
                .padding(top = 21.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            itemsIndexed(waterPointOrderUiState.orders) { index, item ->
                val backgroundColor = if (index % 2 == 0)
                    Color.Transparent
                else Color(0xFFF5F5F5)
                truckOrderItem(
                    navigate = { navigate() },
                    color = backgroundColor,
                    getWaterPointOrdersResItem = item
                )

            }
        }

    }

}


@Composable
fun truckOrderItem(
    navigate: () -> Unit,
    color: Color,
    getWaterPointOrdersResItem: GetWaterPointOrdersResItem
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = color, shape = RoundedCornerShape(12.dp))
        .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
        .clickable { navigate() }
    ) {

        Column(modifier = Modifier.padding(top = 9.dp)) {
            LoadImage(
                imageUrl  = getWaterPointOrdersResItem.truck.image,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
            )

            Text(
                text = "ANTONY",
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
                text = "Order NO #${getWaterPointOrdersResItem.id}",
                modifier = Modifier.padding(start = 18.dp).width(90.dp),
                fontSize = 11.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                overflow = TextOverflow.Ellipsis,
                letterSpacing = (-0.53).sp,
                lineHeight = 21.sp,
                color = Color.Black,
                maxLines = 1
            )

            Row(
                modifier = Modifier
                    .padding(top = 12.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFF979797),
                        shape = RoundedCornerShape(9.dp)
                    )
                    .padding(horizontal = 4.dp)
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = getWaterPointOrdersResItem.truck.license_plate.toString(),
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
                        color = Color.Transparent,
                        shape = RoundedCornerShape(9.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD1D1D1),
                        shape = RoundedCornerShape(7.dp)
                    )
                    .padding(horizontal = 6.dp)
            ) {
                Text(
                    text = "${getWaterPointOrdersResItem.truck.capacity}LT",
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

        Column(modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.End) {

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
                modifier = Modifier.padding(end = 14.dp)
            )
            Text(
                text = "${getWaterPointOrdersResItem.truck.capacity}LT",
                fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.82).sp,
                lineHeight = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp, top = 11.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 11.dp, end = 14.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFF02CBE3),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 4.dp)
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getWaterPointOrdersResItem.status.equals("P")) "UNPAID" else "PAID",
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


@Preview
@Composable
fun demo() {
    WaterpointOrdersContent(navigate = {}, waterPointOrderUiState = WaterPointOrderUiState(), navigateNewOrder = {}, scanQr = {})
}