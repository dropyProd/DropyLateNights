package com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.network.models.jobs.infoPojo
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.rider.distancepojo

@Composable
fun IncomingJobsList(
    incomingJobs: List<IncomingJobListItemDataClass>,
    durationJobs: List<distancepojo> = listOf(),
    infolist: List<infoPojo>,
    customerName: String,
    onDecline: (IncomingJobListItemDataClass) -> Unit,
    onAccept: (IncomingJobListItemDataClass) -> Unit,
    hasOngoingJob: Boolean,
    incomingJobViewModel: IncomingJobViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        incomingJobs.forEachIndexed { index, item ->
            Log.d("djufh", "IncomingJobsList: $durationJobs")
            item.addressName?.let {
                item.cost?.let { it1 ->
                    item.jobType?.let { it2 ->
                        item.timeInMin?.let { it3 ->
                            IncomingJob(
                                customerFirstName = item.customerFirstName.toString(),
                                customerLastName = "",
                                addressName = it,
                                cost = item.cost,
                                jobType = it2,
                                timeInMin =/*if (durationJobs.isNotEmpty()) {
                                    if (durationJobs.size - 1 <= index) {
                                        durationJobs[index].shopDistanceduration
                                    }else 0
                                } else*/ 0,
                                onAccept = { onAccept(item) },
                                onDecline = { onDecline(item) }, profilePicUrl = item.profilePicUrl,
                                hasOngoingJob = hasOngoingJob,
                                incomingJobViewModel = incomingJobViewModel
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun IncomingJobsListPreview() {
    Column(Modifier.fillMaxSize()) {
        /*    val jobs = listOf(
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
            IncomingJobsList(
                incomingJobs = jobs,
                onDecline = {},
                onAccept = {},
                customerName = ""
            )*/
    }
}