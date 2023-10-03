package com.example.dropy.ui.screens.rider

import com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist.IncomingJobListItemDataClass

data class RiderHomePageUiState(
    val availability:Boolean = false,
    val firstName:String = "",
    val lastName:String = "",
    val incomingJobs:List<IncomingJobListItemDataClass> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)
