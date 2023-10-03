package com.example.dropy.ui.screens.rider

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.reviews.textRate
import com.example.dropy.ui.screens.pickCustomer.backgroundedIcon
import com.example.dropy.ui.screens.pickCustomer.backgroundedTextIcon
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.GrayYellow
import com.example.dropy.ui.theme.LightBlue
import com.example.dropy.ui.theme.grayText


@Composable
fun RiderDetailsDialog(
    show: Boolean = false,
    riderDetailsViewModel: RiderDetailsViewModel? = null,
    onDismisssDialog: ((Boolean) -> Unit)? = null,

) {

    val riderDetailsUiState by riderDetailsViewModel?.riderDetailsUiState!!.collectAsState()

    if (show) {
        Dialog(onDismissRequest = {
            if (onDismisssDialog != null) {
                onDismisssDialog(false)
            }
        }) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            ) {

                val (body, badge) = createRefs()

                riderBody(modifier = Modifier.constrainAs(body) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, riderDetailsUiState = riderDetailsUiState)

                badgeBdy(modifier = Modifier.constrainAs(badge) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 23.dp)
                })
            }
        }
    }
}

@Composable
fun badgeBdy(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(width = 1.dp, LightBlue, RoundedCornerShape(12.dp))
            .width(95.dp)
            .height(25.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .offset(y = -1.dp)
                .size(8.dp)
                .clip(CircleShape)
                .background(Color.Green)
                .border(1.dp, Color.Black, CircleShape)
        ) {

        }

        SimpleText(
            text = "rider id",
            isUppercase = true,
            fontWeight = FontWeight.Black,
            textSize = 9,
            font = Font(R.font.axiformablack)
        )
    }
}

@Composable
fun riderBody(modifier: Modifier = Modifier, riderDetailsUiState: RiderDetailsUiState) {
    Row(
        modifier = modifier
            .background(Color(255, 249, 219), RoundedCornerShape(20.dp))
            .border(1.dp, LightBlue, RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .height(160.dp)
            .padding(start = 5.dp, bottom = 20.dp, end = 7.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.border(1.dp, LightBlue, CircleShape)) {
            Image(
                painter = painterResource(id = R.drawable.emergencyyy),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
            )
        }

        detailsColumn(riderDetailsUiState = riderDetailsUiState)

        starItem()
    }
}

@Composable
fun detailsColumn(riderDetailsUiState: RiderDetailsUiState) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SimpleText(
            text = riderDetailsUiState.riderName,
            isUppercase = true,
            fontWeight = FontWeight.Black,
            textSize = 12,
            font = Font(R.font.axiformablack)
        )

        SimpleText(
            text = "YAMAHA 567 SM",
            isUppercase = true,
            fontWeight = FontWeight.Black,
            textSize = 12,
            font = Font(R.font.axiformablack),
            textColor = Color.Blue
        )

        SimpleText(
            text = "213 RIDES",
            isUppercase = true,
            fontWeight = FontWeight.Black,
            textSize = 12,
            font = Font(R.font.axiformablack),
            textColor = Color.Blue
        )
        SimpleText(
            text = "15,456 km travelled",
            fontWeight = FontWeight.SemiBold,
            textSize = 12,
            font = Font(R.font.axiformasemibolditalic),
            textColor = Color(0xFF74728A),
            fontStyle = FontStyle.Italic
        )

        backgroundedTextIcon(background = DropyYellow, text = riderDetailsUiState.placeName)
    }
}

@Composable
fun starItem() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxHeight()
    ) {
        textRate(
            starSize = 50,
            textSize = 14,
            topPadding = 20,
            modifier = Modifier.offset(x = 3.dp)
        )
        BackgroundedText(
            background = LightBlue,
            textColor = Color.White,
            text = "KDA678T",
            textSize = 11,
            vertical = 2,
            modifier = Modifier.offset(y = -15.dp, x = -20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun badgePrev() {
    RiderDetailsDialog(show = true)
}