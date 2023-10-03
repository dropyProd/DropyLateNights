package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL

@Composable
fun OrderItemsList(
    title:String = "item list",
    orderItemsList: List<OrderItemListItemDataClass>,
    markAsAvailable:(itemId:Int)->Unit,
    markAsUnavailable:(itemId:Int, url: String)->Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ){
            SimpleText(
                textSize = 10,
                text = title,
                isUppercase = true,
                isExtraBold = true,
                font = Font(R.font.axiformaextrabold)
            )
        }
        orderItemsList.forEachIndexed { index, item ->
            OrderItem(
                productImageUrl = /*BaseUrL+*/item.productImageUrl,
                productName = item.productName,
                numberOfUnits = item.numberOfUnits,
                price = item.price,
                isAvailable = item.isAvailable,
                markAsUnavailable = {markAsUnavailable(index, BaseUrL+item.productImageUrl)},
                markAsAvailable = {markAsAvailable(index)}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderListItemListPreview(){
    Column(Modifier.fillMaxSize()) {
        val orderItemsList = mutableListOf(
            OrderItemListItemDataClass(
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                isAvailable = true
            ),
            OrderItemListItemDataClass(
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                isAvailable = true
            ),
            OrderItemListItemDataClass(
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                isAvailable = true
            )
        )
        OrderItemsList(
            orderItemsList=orderItemsList,
            title = "item list",
            markAsAvailable = {},
            markAsUnavailable = {_, _ ->}
        )
    }
}