package com.example.dropy.ui.components.parcels.frontside.parceldetails
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.theme.LightContainerBackground

@Composable
fun ParcelSize(
    size:String,
    description:String,
    image:Painter?,
    myIndex:Int,
    select:(Index:Int)->Unit,
    backgroundColor:Color
){
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(80.dp)
            .aspectRatio(1 / 2f)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
        ,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ,
            contentAlignment = Alignment.BottomCenter
        ){
            SimpleText(
                textSize = 12,
                text = size,
                isUppercase = true,
                isBold = true,
            )
        }
        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(2f)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .clickable { select(myIndex) }
                .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (image != null) {
                    Image(
                        painter = image,
                        contentDescription = "parcel size image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        ,
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            SimpleText(
                textSize = 9,
                text = description,
                textColor = Color.Black,
                isUppercase = true,
                isBold = true,
                isExtraBold = false,
                padding = 4
            )
        }
        Box(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun ParcelSizePreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ParcelSize(
            size = "small",
            description = "document",
            image = null,
            myIndex = 0,
            select = {},
            backgroundColor = LightContainerBackground
        )
    }
}