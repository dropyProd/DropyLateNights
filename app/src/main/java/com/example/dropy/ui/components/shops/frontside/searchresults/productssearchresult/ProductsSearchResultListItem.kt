package com.example.dropy.ui.components.shops.frontside.searchresults.productssearchresult

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.*
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun ProductsSearchResultListItem(
    productPhotoUrl : String?,
    productName :String,
    shopName : String,
    rating :Double,
    distanceInMeters : Int,
    timeInMinutes : Int,
    price : Int,
    onProductSelected :()->Unit,
    onAddProductClicked : ()->Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .padding(bottom = 8.dp)
    ){
        Row(
            modifier = Modifier
                .clickable { onProductSelected() }
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                ,
            ) {
                LoadImage(imageUrl = productPhotoUrl)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(8.dp)
                    ,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = productName.uppercase(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(DropyYellow)
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = shopName.uppercase(),
                            fontSize = 9.sp,
                            modifier = Modifier
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Rating(rating = rating)
                        Distance(distanceInMeters = distanceInMeters)
                        Time(timeInMin = timeInMinutes.toString())
                    }

                }
                SimpleText(isBold = true,textSize = 21, text =" ${price}/-")
            }
        }

        IconButton(
            onClick = { onAddProductClicked()},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Black)
            ,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add icon",
                tint = Color.White
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ProductsSearchResultListItemPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProductsSearchResultListItem(
            productPhotoUrl = "",
            productName = "product name",
            price = 1234,
            shopName = "shop name",
            rating = 2.5,
            distanceInMeters = 2345,
            timeInMinutes = 76,
            onAddProductClicked = {},
            onProductSelected = {}
        )
    }
}