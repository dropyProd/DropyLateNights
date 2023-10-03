package com.example.dropy.ui.components.rider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist.IncomingJobListItemDataClass
import com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist.IncomingJobsList
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.rider.RiderHomePageUiState


@Composable
fun RiderHomePageContent(
    uiState: RiderHomePageUiState,
    changeAvailability: (Boolean) -> Unit,
    onDecline:(IncomingJobListItemDataClass)->Unit,
    onAccept:(IncomingJobListItemDataClass)->Unit,
    incomingJobViewModel: IncomingJobViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
    ) {
        RiderAvailability(
            isAvailable = uiState.availability,
            firstName = uiState.firstName ,
            lastName = uiState.lastName,
            changeAvailability = {changeAvailability(it)}
        )
        IncomingJobsList(
            incomingJobs = uiState.incomingJobs,
            onDecline = {onDecline(it)},
            onAccept = {onAccept(it)},
            customerName = "",
            hasOngoingJob = false,
            incomingJobViewModel = incomingJobViewModel, infolist = listOf()
        )
    }
}

@Composable
fun RiderAvailability(
    isAvailable:Boolean,
    firstName:String,
    lastName:String,
    changeAvailability:(Boolean)->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
            .sizeIn(minHeight = 80.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            SimpleText(
                text = "Hi $firstName $lastName",
                textSize = 21,
                isBold = true,
                verticalPadding = 8
            )
            SimpleText(
                text = "Turn availability on to receive jobs"
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SimpleText(
                text = if (isAvailable){"Online"}else{"Offline"},
                horizontalPadding = 8
            )
            Switch(
                checked = isAvailable,
                onCheckedChange = {changeAvailability(it)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RiderHomePagePreview(){
    RiderHomePageContent(
        uiState = RiderHomePageUiState(),
        changeAvailability = {},
        onAccept = {},
        onDecline = {},
        incomingJobViewModel = hiltViewModel()
    )
}