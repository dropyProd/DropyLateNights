package com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.Time
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import java.util.*

@Composable
fun IncomingJob(
    profilePicUrl: String? = null,
    customerFirstName: String,
    customerLastName: String,
    addressName: String,
    cost: Int,
    jobType: String,
    timeInMin: Int,
    onDecline: () -> Unit,
    onAccept: () -> Unit,
    hasOngoingJob: Boolean,
    incomingJobViewModel: IncomingJobViewModel
) {

    val timeString: String = if (incomingJobViewModel.currentduration.value >= 3600) {
        val hrs: Int = timeInMin / 3600
        val min: Int = timeInMin % 3600 / 60
        // val seconds: Int = input % 3600 % 60
        // val hrs = timeInMin / 60
        //  val min = timeInMin % 60
        "$hrs HRS $min MINS"
    } else {
        val min = timeInMin / 60
        "$min MINS"
    }

    val bgColor = when (jobType) {
        "delivery" -> {
            Color(0xFFFFFFFF)
        }
        "ride" -> {
            Color(0xFFFFF9DB)
        }
        else -> {
            Color(0xFFFFF9DB)
        }
    }
    val boderColor = when (jobType) {
        "delivery" -> {
            Color(0xFFA8A1FF)
        }
        "ride" -> {
            Color(0x66707070)
        }
        else -> {
            Color(0xFFFFF9DB)
        }
    }

    Row(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor, RoundedCornerShape(8.dp))
            .border(1.dp, boderColor.copy(.25f), RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable {
                if (hasOngoingJob) {
                    onAccept()
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        ) {
            LoadImage(imageUrl = profilePicUrl)
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    SimpleText(
                        text = "$customerFirstName $customerLastName".replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        isBold = true,
                        isUppercase = true,
                        font = Font(R.font.axiformabold)
                    )
                    SimpleText(
                        text = addressName,
                        font = Font(R.font.axiformaregular)
                    )
                }
                SimpleText(
                    text = "$cost/-",
                    textSize = 21,
                    isExtraBold = true,
                    horizontalPadding = 8,
                    font = Font(R.font.axiformaregular)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StyledText(
                    backgroundColor = when (jobType) {
                        "delivery" -> {
                            LightBlue
                        }
                        "ride" -> {
                            Color.Black
                        }
                        else -> {
                            LightBlue
                        }
                    },
                    text = jobType.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    },
                    textColor = Color.White,
                    fontFamily = R.font.axiformaextrabold
                )
                Time(timeInMin = timeString)

            }
            if (hasOngoingJob.equals(false)) {
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(start = 8.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .clickable { onAccept() }
                    ) {
                        StyledText(
                            backgroundColor = DropyYellow,
                            text = "Accept",
                        )
                    }

                    Box(
                        modifier = Modifier.clickable { onDecline() }
                    ) {
                        StyledText(
                            backgroundColor = Color.Black,
                            textColor = Color.White,
                            text = "Decline"
                        )
                    }


                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IncomingJobPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {


        IncomingJob(
            customerFirstName = "claudius",
            customerLastName = "mango",
            addressName = "Some long address name",
            cost = 50,
            jobType = "ride",
            timeInMin = 30,
            onAccept = {},
            onDecline = {},
            hasOngoingJob = false,
            incomingJobViewModel = hiltViewModel()
        )
    }
}