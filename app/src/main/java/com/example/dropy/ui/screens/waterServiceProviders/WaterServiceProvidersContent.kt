package com.example.dropy.ui.screens.waterServiceProviders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader


data class ServiceItem(
    val text: String,
    val image: Int
)

@Composable
fun WaterServiceProvidersContent(
    navigate: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ClippedHeader(title = "Water SERVICE PROVIDERS",
            modifier = Modifier.padding(top = 22.dp))

        Text(
            text = "CHOOSE\nYOUR\nCATEGORY",
            color = Color.Black,
            fontSize = 22.sp,
//            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
            letterSpacing = (-1.06).sp,
            modifier = Modifier
                .padding(top = 50.dp)
                .align(Alignment.CenterHorizontally),
            lineHeight = 24.sp,
            textAlign = TextAlign.Center
        )


        val listO = remember {
            mutableListOf(
                ServiceItem(
                    text = "WATER\nPOINT",
                    image = R.drawable.shop1
                ),
                ServiceItem(
                    text = "TRUCK\nCOMPANY",
                    image = R.drawable.shop1
                )
            )
        }
        val listT: MutableList<ServiceItem> = remember {
            mutableListOf(
                      ServiceItem(
                          text = "TRUCK\nDRIVER",
                          image = R.drawable.shop1
                      ),
                      ServiceItem(
                          text = "TRUCK\nDRIVER",
                          image = R.drawable.shop1
                      )
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                itemsIndexed(listO) { index, itemm ->
                    item(itemm.text, navigate = navigate)
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                itemsIndexed(listT) { index, itemm ->
                    if (index.equals(1))
                        item(itemm.text, color = Color.White, navigate = navigate)
                    else
                        item(itemm.text, navigate = navigate)
                }
            }
        }

    }
}

@Composable
fun item(text: String, color: Color? = null, navigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(108.dp)
            .height(104.dp)
            .background(
                color = if (color != null) color else Color.Transparent,
                RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                color = if (color != null) color else Color(0xFFDEDEDE),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable { navigate(text) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        /*Icon(
            Icons.Filled.Person,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 24.dp)
                .size(30.dp)
        )*/
        Text(
            text = text,
            color = if (color != null) color else Color.Black,
            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
            letterSpacing = (-0.48).sp,
            modifier = Modifier.padding(top = 16.dp),
            lineHeight = 19.sp
        )

    }

}

@Preview
@Composable
fun demo() {
    WaterServiceProvidersContent(navigate = {})
}