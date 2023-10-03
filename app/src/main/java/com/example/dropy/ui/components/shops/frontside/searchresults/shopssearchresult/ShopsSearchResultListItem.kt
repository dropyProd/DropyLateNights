package com.example.dropy.ui.components.shops.frontside.searchresults.shopssearchresult

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.Distance
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.Rating
import com.example.dropy.ui.components.commons.Time
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun ShopsSearchResultListItem(
    shopLogoUrl : String?,
    shopName : String,
    shopLocation :String,
    distanceInMeters : Int,
    timeInMinutes : Int,
    rating :Double,
    onShopSelected : ()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onShopSelected() }
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
            LoadImage(imageUrl = shopLogoUrl)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp, end=8.dp)
                ,
            ) {
                Text(
                    text = shopName.uppercase(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(DropyYellow)
                        .fillMaxWidth()
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = shopLocation.uppercase(),
                        fontSize = 9.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                ,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Distance(distanceInMeters = distanceInMeters)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                ,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Rating(rating = rating)
                Time(timeInMin = timeInMinutes.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsSearchResultListItemPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShopsSearchResultListItem(
            shopLogoUrl = "",
            shopName = "shop name",
            shopLocation = "some shop location",
            distanceInMeters = 1234,
            timeInMinutes = 45,
            rating = 4.2,
            onShopSelected = {}
        )
    }
}
