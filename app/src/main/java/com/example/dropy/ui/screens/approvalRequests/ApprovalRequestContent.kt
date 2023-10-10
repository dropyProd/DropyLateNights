package com.example.dropy.ui.screens.approvalRequests

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.approvalRequests.ApprovalRequestsResItem
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader

@Composable
fun ApprovalRequestContent(
    uiState: ApprovalRequestUiState,
    onClick: (String, ApprovalRequestsResItem) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ClippedHeader(title = "order history")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            SimpleText(
                text = "Incoming approval requests",
                textSize = 14,
                isUppercase = true,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.ExtraBold
            )

            Box(
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            ) {
                SimpleText(
                    textSize = 10,
                    text = "${uiState.approvalRequests.size} approval requests",
                    isExtraBold = true,
                    isUppercase = true,
                    font = Font(R.font.axiformablack),
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn(modifier = Modifier
                .padding(top = 22.dp),
                verticalArrangement = Arrangement.spacedBy(11.dp), content = {
                    itemsIndexed(uiState.approvalRequests) { index, item ->
                        val backgroundColor = if (index % 2 == 0)
                            Color.Transparent
                        else Color(0xFFF5F5F5)
                        approvalRequestItem(
//                            navigate = navigate,
                            color = backgroundColor,
                            onClick = onClick,
                            approvalRequestsResItem = item
//                            getIndividualOrdersResItem = item
                        )
                    }

                })
        }
    }
}

@Composable
fun approvalRequestItem(
//    navigate: (GetIndividualOrdersResItem) -> Unit,
    approvalRequestsResItem: ApprovalRequestsResItem,
    color: Color,
    onClick: (String, ApprovalRequestsResItem) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = color, shape = RoundedCornerShape(12.dp))
        .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
    ) {

        Column(modifier = Modifier.padding(top = 24.dp)) {
            LoadImage(
                imageUrl = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
            )

            /*   Text(
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
               )*/
        }

        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(
                text = "John Smith wants to be a\ntruck driver for ${approvalRequestsResItem.license_number}",
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

        }

        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {


            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 7.dp)
                    .width(100.dp)
                    .height(28.dp)
                    .background(color = Color(0xFF02CBE3), RoundedCornerShape(42.dp))
                    .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(42.dp))
                    .clickable {
                        onClick("ACCEPT", approvalRequestsResItem)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                androidx.compose.material.Text(
                    text = "ACCEPT",
                    color = Color.Black,
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.48).sp,
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 8.dp, top = 15.dp)
                    .width(100.dp)
                    .height(28.dp)
                    .background(color = Color.Transparent, RoundedCornerShape(42.dp))
                    .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(42.dp))
                    .clickable {
                        onClick("DECLINE", approvalRequestsResItem)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                androidx.compose.material.Text(
                    text = "DECLINE",
                    color = Color.Black,
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.48).sp,
                )
            }

        }

    }
}


@Preview
@Composable
fun demo(){
    ApprovalRequestContent(uiState = ApprovalRequestUiState(), onClick = {_, _ ->})
}
