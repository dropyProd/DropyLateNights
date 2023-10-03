package com.example.dropy.ui.screens.dropymainmodel

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination

@Composable
fun DropyMainModel(navController: NavController? = null) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        dropyMainHeader(modifier = Modifier.fillMaxWidth(), start = 0)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color(0xFFFFF9DB), shape = RoundedCornerShape(20.dp))
                .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(20.dp))
                .padding(horizontal = 13.dp, vertical = 19.dp)
                .align(Alignment.End)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "SENDING SOMETHING?",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Text(
                    text = "LOOK NO FURTHER",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Image(
                painter = painterResource(id = R.drawable.parcel),
                contentDescription = "",
                modifier = Modifier.size(110.dp)
            )

        }

        val list = remember {
            mutableListOf(
                DropyMainPojo(
                    image = R.drawable.parcel,
                    text = "SEND PACKAGE",
                    color = Color(0xFFCCC9FC),
                    colorOne = Color(0xFFCCC9FC),
                ),
                DropyMainPojo(
                    image = R.drawable.parcel,
                    text = "SEND PACKAGE",
                    color = Color(0xFFFCD313),
                    colorOne = Color(0xFFFEF8DB)
                ),
                DropyMainPojo(
                    image = R.drawable.parcel,
                    text = "SEND PACKAGE",
                    color = Color(0xFFFCD313),
                    colorOne = Color(0xFFFEF8DB)
                ),
                DropyMainPojo(
                    image = R.drawable.parcel,
                    text = "SEND PACKAGE",
                    color = Color(0xFFFCD313),
                    colorOne = Color(0xFFFEF8DB)
                )

            )
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            itemsIndexed(items = list) { index, item ->
                dropyCategoryItem(dropyMainPojo = item, click = {
                    navController?.navigate(ParcelDestination.PARCEL_HOME)
                })
            }
        }
    }

}

@Composable
fun dropyCategoryItem(
    dropyMainPojo: DropyMainPojo,
    size: Int = 40,
    width: Int = 50,
    height: Int = 190,
    showtext: Boolean = true,
    click: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .width(width.dp)
            .height(height.dp)
            .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(14.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        dropyMainPojo.colorOne,
                        dropyMainPojo.color

                    )
                ), shape = RoundedCornerShape(14.dp)
            )
            .clickable { click() }
    ) {


        val (img, txt) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.parcel),
            contentDescription = "",
            modifier = Modifier
                .size(size.dp)
                .constrainAs(img) {
                    top.linkTo(parent.top, 30.dp)
                    end.linkTo(parent.end, 15.dp)
                }
        )

        if (showtext) {
            Text(
                text = dropyMainPojo.text,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .width(100.dp)
                    .constrainAs(txt) {
                        top.linkTo(img.bottom, 60.dp)
                        start.linkTo(parent.start, 15.dp)
                    }
            )
        }
    }
}

data class DropyMainPojo(
    val image: Int,
    val text: String,
    val color: Color,
    val colorOne: Color
)

@Preview
@Composable
fun demo() {
    DropyMainModel()
}