package com.example.dropy.ui.screens.waterMyOrder

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.R
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck
import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.ui.components.commons.LoadImage

@Composable
fun WaterMyOrderContent(
    navigate: (AssignedTruck) -> Unit,
    navigateO: (com.example.dropy.network.models.GetIndividualOrders.AssignedTruck?) -> Unit,
    waterMyOrderUiState: WaterMyOrderUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ClippedHeader(
            title = "ORDER #${if (waterMyOrderUiState.createIndividualWaterOrderRes != null) waterMyOrderUiState.createIndividualWaterOrderRes.tracking_id else waterMyOrderUiState.getIndividualOrdersResItem?.tracking_id}",
            modifier = Modifier.padding(top = 22.dp)
        )


        LazyColumn(modifier = Modifier
            .padding(top = 36.dp, start = 15.dp, end = 14.dp),
            verticalArrangement = Arrangement.spacedBy(11.dp), content = {
                if (waterMyOrderUiState.getIndividualOrdersResItem != null) {
                    itemsIndexed(waterMyOrderUiState.getIndividualOrdersResItem!!.assigned_trucks) { index, item ->
                        val backgroundColor = if (index % 2 == 0)
                            Color.Transparent
                        else Color(0xFFF5F5F5)

                        waterMyOrderUiState.getIndividualOrdersResItem.tasks.forEach {it ->
                            if (it.truck.id.equals( item.id)) {
                                waterOrderItem(
                                    navigateO = navigateO,
                                    color = backgroundColor,
                                    assignedTruckO = item,
                                    getIndividualOrdersResItem = waterMyOrderUiState.getIndividualOrdersResItem,
                                    deliveryStatus = it.delivery_status
                                )
                            }
                        }
                    }
                }
                if (waterMyOrderUiState.createIndividualWaterOrderRes != null) {
                    itemsIndexed(waterMyOrderUiState.createIndividualWaterOrderRes!!.assigned_trucks) { index, item ->
                        val backgroundColor = if (index % 2 == 0)
                            Color.Transparent
                        else Color(0xFFF5F5F5)


                        waterMyOrderUiState.createIndividualWaterOrderRes.tasks.forEach {it ->
                                if (it.truck.id.equals( item.id)) {
                                    waterOrderItem(
                                        navigate = navigate,
                                        color = backgroundColor,
                                        assignedTruck = item,
                                        createIndividualWaterOrderRes = waterMyOrderUiState.createIndividualWaterOrderRes,
                                        deliveryStatus = it.delivery_status
                                    )
                                }
                        }

                    }
                }
            })

    }
}

@Composable
fun waterOrderItem(
    navigate: ((AssignedTruck) -> Unit)? = null,
    navigateO: ((com.example.dropy.network.models.GetIndividualOrders.AssignedTruck?) -> Unit)? = null,
    getIndividualOrdersResItem: GetIndividualOrdersResItem? = null,
    createIndividualWaterOrderRes: CreateIndividualWaterOrderRes? = null,
    color: Color,
    assignedTruck: AssignedTruck? = null,
    assignedTruckO: com.example.dropy.network.models.GetIndividualOrders.AssignedTruck? = null,
    deliveryStatus: String
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = color, shape = RoundedCornerShape(12.dp))
        .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
        .clickable {
            if (assignedTruck != null) {
                if (navigate != null) {
                    navigate(assignedTruck)
                }
            }
            if (assignedTruckO != null) {
                if (navigateO != null) {
                    navigateO(assignedTruckO)
                }
            }

        }
    ) {

        Column(modifier = Modifier.padding(top = 9.dp)) {
            LoadImage(
                imageUrl = if (assignedTruck != null) assignedTruck.image else assignedTruckO?.image,
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
                text = if (assignedTruck != null) assignedTruck.license_plate.toString() else assignedTruckO?.license_plate.toString(),
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
                        color = if (deliveryStatus.equals(
                                "D"
                            )
                        ) Color.Black else Color(0xFF979797),
                        shape = RoundedCornerShape(9.dp)
                    )
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getIndividualOrdersResItem != null) {
                        if (deliveryStatus.equals(
                                "P"
                            )
                        ) "NOT PROCESSED" else if (deliveryStatus.equals(
                                "S"
                            )
                        ) "PROCESSED" else "DELIVERED"
                    } else {
                        if (deliveryStatus.equals(
                                "P"
                            )
                        ) "NOT PROCESSED" else if (deliveryStatus.equals(
                                "S"
                            )
                        ) "PROCESSED" else "DELIVERED"
                    },
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
                    .padding(top = 12.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = if (deliveryStatus.equals(
                                "P"
                            )
                        ) Color.Black else Color(0xFFC2F8FF),
                        shape = RoundedCornerShape(9.dp)
                    )
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getIndividualOrdersResItem != null) {
                        if (deliveryStatus.equals(
                                "P"
                            )
                        ) "NOT STARTED" else if (deliveryStatus.equals(
                                "S"
                            )
                        ) "ON THE WAY" else "DELIVERED"
                    } else {
                        if (deliveryStatus.equals(
                                "P"
                            )
                        ) "NOT STARTED" else if (deliveryStatus.equals(
                                "S"
                            )
                        ) "ON THE WAY" else "DELIVERED"
                    },
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = if (deliveryStatus.equals(
                            "P"
                        )
                    ) Color.White else Color.Black,
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
                .padding(top = 27.dp)
                .fillMaxWidth(), horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${assignedTruck?.capacity ?: assignedTruckO?.capacity}LT",
                fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.82).sp,
                lineHeight = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 12.dp, end = 8.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFF02CBE3),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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
                    color = Color.White
                )
            }
        }

    }
}

@Preview
@Composable
fun demo() {
    WaterMyOrderContent(navigate = {}, waterMyOrderUiState = WaterMyOrderUiState(), navigateO = {})
}