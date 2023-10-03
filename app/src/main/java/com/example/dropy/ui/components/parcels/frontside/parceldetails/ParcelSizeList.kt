package com.example.dropy.ui.components.parcels.frontside.parceldetails
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightContainerBackground

@Composable
fun ParcelSizeList(
    selectedSize:(ParcelSizeDataClass)->Unit
){
    @Suppress("RemoveExplicitTypeArguments")val sizeList = mutableListOf<ParcelSizeDataClass>(
        ParcelSizeDataClass(size = "small", description = "document", image = null),
        ParcelSizeDataClass(size = "medium", description = "1-3kgs", image = null),
        ParcelSizeDataClass(size = "large", description = "3-10kgs", image = null),
        ParcelSizeDataClass(size = "extra\nlarge", description = "over 10kgs", image = null)
    )

    val selectedSizeIndex = remember {
        mutableStateOf(-1)
    }

    fun selectSize(index:Int){
        selectedSizeIndex.value = index
        val selected = sizeList[selectedSizeIndex.value]
        selectedSize(selected)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        StyledText(
            textColor = Color.Black,
            backgroundColor = DropyYellow,
            textSize = 12,
            text = "choose package",
            isUppercase = true,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            sizeList.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                    ,
                    contentAlignment = Alignment.Center
                ){
                    ParcelSize(
                        size = item.size,
                        description = item.description,
                        image = item.image,
                        myIndex = index,
                        select = {selectSize(it)},
                        backgroundColor = if (selectedSizeIndex.value == index){
                            LightContainerBackground
                        }else{
                            Color.White
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParcelSizeListPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ParcelSizeList(selectedSize = {})
    }
}