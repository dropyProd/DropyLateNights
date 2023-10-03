package com.example.dropy.ui.screens.rider

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist.IncomingJobListItemDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RiderHomePageViewModel @Inject constructor():ViewModel(){
    private val uiState = MutableStateFlow(RiderHomePageUiState())
    val riderHomePageUiState:StateFlow<RiderHomePageUiState> = uiState.asStateFlow()
    var appViewModel: AppViewModel? = null

 /*   init {
        getAvailableJobs()
    }*/

    private fun getAvailableJobs(){
     /*   val jobs = listOf(
            IncomingJobListItemDataClass(
                customerFirstName = "customer",
                customerLastName = "name",
                addressName = "Some Long address name",
                cost = 100,
                jobType = "delivery",
                timeInMin = 1234
            ),
            IncomingJobListItemDataClass(
                customerFirstName = "customer",
                customerLastName = "name",
                addressName = "Some Long address name",
                cost = 100,
                jobType = "delivery",
                timeInMin = 1234
            ),
        )
        uiState.update {
            it.copy(
                incomingJobs = jobs
            )
        }*/
    }
}