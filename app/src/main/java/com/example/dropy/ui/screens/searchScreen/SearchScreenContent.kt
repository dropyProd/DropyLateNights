package com.example.dropy.ui.screens.searchScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.shopscommons.ProductAndShopSearchBar
import com.example.dropy.ui.theme.LightBlue
import com.example.dropy.R
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun SearchScreenContent(
    searchItem: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight().padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Spacer(modifier = Modifier.height(15.dp))
        ProductAndShopSearchBar(searchItem = searchItem)
        Spacer(modifier = Modifier.height(3.dp))

        searchesContainer(
            text = "MY SEARCHES",
            items = listOf("PS 5", "Gaming laptop", "CLS Mercedes", "BURGER", "Apples","BURGER")
        )
        Spacer(modifier = Modifier.height(2.dp))
        searchesContainer(
            text = "TRENDING",
            items = listOf(
                "Johnsons 3+1",
                "Lucozade gold",
                "MC Fries",
                "KFC",
                "Cocacola",
                "Chikito R",
                "Ribena",
                "Lucozade gold"
            )
        )

        popularRankCont()
    }
}

@Composable
fun searchesContainer(text: String, items: List<String>) {

    Column(modifier = Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        BackgroundedText(
            background = Color.Black,
            textColor = Color.White,
            text = text,
            vertical = 6,
            textSize = 10,
            fontWeight = FontWeight.Bold,
            font = Font(R.font.axiformabold)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(3.dp),  content = {

                items(items) {
                    BackgroundedText(
                        background = Color.LightGray,
                        textColor = Color.Black,
                        text = it,
                        vertical = 3,
                        fontWeight = FontWeight.Medium,
                        font = Font(R.font.axiformamedium),
                        textSize = 9,

                    )
                }
            })
    }

}

@Composable
fun popularRankCont() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 11.dp, end = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackgroundedText(
                background = Color.Black,
                textColor = Color.White,
                text = "POPULAR RANKINGS",
                vertical = 6,
                textSize = 12,
                fontWeight = FontWeight.Black,
                font = Font(R.font.axiformabold )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                SimpleText(
                    text = "HIDE RANKING",
                    fontWeight = FontWeight.Normal,
                    font = Font(R.font.axiformaregular),
                    textColor = Color.LightGray,
                    textSize = 7
                )

                Icon(Icons.Filled.Password, contentDescription = "", tint = Color.LightGray)
            }

        }

        Row(
            modifier = Modifier
                .padding(start = 11.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            val list = remember {
                mutableListOf(
                    rankPojo("This space is reserved for the product title", true),
                    rankPojo(
                        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam.",
                        true
                    ),
                    rankPojo("This space is reserved for the product title", false),
                    rankPojo(
                        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam.",
                        true
                    ),
                    rankPojo("This space is reserved for the product title", true),
                    rankPojo(
                        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam.",
                        false
                    ),
                    rankPojo("This space is reserved for the product title", true),
                    rankPojo(
                        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam.",
                        false
                    ),
                    rankPojo("This space is reserved for the product title", false),
                    rankPojo(
                        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam.",
                        true
                    ),

                    )
            }
            popularrankItem(title = "FASHION", list = list) {

            }
            popularrankItem(title = "FASHION", list = list) {

            }
        }
    }
}

@Composable
fun popularrankItem(title: String, list: List<rankPojo>, onNextButtonClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .width(245.dp)
            .fillMaxHeight()
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackgroundedText(
                background = DropyYellow,
                textColor = Color.Black,
                text = title,
                vertical = 2,
                textSize = 8,
                fontWeight = FontWeight.Bold,
                font = Font(R.font.axiformabold)
            )

            Row(
                modifier = Modifier
                    .size(23.dp)
                    .clickable { onNextButtonClicked() }
                    .clip(CircleShape)
                    .background(Color.Black),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "back button",
                    tint = DropyYellow,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items = list) { index, item ->
                if (index in 0..2) {

                    val color =
                        if (index.equals(0)) DropyYellow else if (index.equals(1)) Color(0xFFABABAB) else Color(
                            0xFFBB6A00
                        )

                    rankItem(
                        text = item.text,
                        number = (index + 1).toString(),
                        show = true,
                        isUp = item.isUp,
                        showTopLine = if (index.equals(0)) false else false,
                        color = color
                    )
                } else {
                    rankItem(
                        text = item.text,
                        number = (index + 1).toString(),
                        show = false,
                        isUp = item.isUp,
                        showTopLine = if (index.equals((list.size - 1))) false else false,
                        showBottomLine = if (index.equals((list.size - 1))) false else true,
                        color = LightBlue.copy(.3f)
                    )
                }
            }
        }
    }

}

@Composable
fun rankItem(
    show: Boolean = false,
    showTopLine: Boolean = false,
    showBottomLine: Boolean = true,
    text: String,
    number: String,
    isUp: Boolean = false,
    color: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (show) Color.Black.copy(.3f) else Color.White),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showTopLine) {
            line()
        }

        Row(
            modifier = Modifier
                .padding(start = 11.dp, end = 11.dp, top = if (showTopLine) 0.dp else 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            numberBox(color = color, text = number)

            Row(modifier = Modifier.width(150.dp)) {
                SimpleText(
                    text = text,
                    isBold = true,
                    fontWeight = FontWeight.Medium,
                    font = Font(R.font.axiformamedium),
                    textColor = Color.Black,
                    textSize = 12
                )
            }

            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .rotate(if (isUp) 0f else 180f),
                colorFilter = ColorFilter.tint(color = if (isUp) Color.Green else Color.Red)
            )
        }

        if (showBottomLine) {
            line()
        }
    }
}

@Composable
fun numberBox(color: Color, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(15.dp)
            .rotate(45f)
            .background(
                color = color,
                shape = RoundedCornerShape(topEnd = 7.dp, bottomStart = 7.dp)
            )
    ) {
        Row(modifier = Modifier.rotate(-45f)) {
            SimpleText(
                text = text,
                isBold = true,
                fontWeight = FontWeight.Bold,
                font = Font(R.font.axiformabold),
                textColor = Color.Black,
                textSize = 10
            )
        }
    }
}

@Composable
fun line() {
    Row() {
        for (i in 1..14) {
            repeat(i) {
                SimpleText(text = ".", textColor = Color.Black, textSize = 8)
            }
        }
    }
}

data class rankPojo(
    val text: String,
    val isUp: Boolean
)

@Preview(showBackground = true)
@Composable
fun demoScreen() {
    SearchScreenContent(searchItem = {})

}