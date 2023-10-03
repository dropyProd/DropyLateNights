package com.example.dropy.ui.components.rider.incomingjobs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.components.commons.EmptyBlock
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist.IncomingJobListItemDataClass
import com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist.IncomingJobsList
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.ui.screens.rider.IncomingJobUiState
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun IncomingJobContent(
    navController: NavController,
    incomingJobViewModel: IncomingJobViewModel,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    uiState: IncomingJobUiState,
    appViewModel: AppViewModel,
    appHomePageViewModel: AppHomePageViewModel,
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        appViewModel.appUiState.value.riderId?.let {
            incomingJobViewModel.getOngoingJobs(
                it,
                appViewModel = appViewModel,
                appHomePageViewModel = appHomePageViewModel,
                context
            )
        }
    })
/*    LaunchedEffect(key1 = true, block = {
        incomingJobViewModel.reviewRider(1)
    })*/
    val show = remember {
        mutableStateOf(false)
    }
    val fee = remember {
        mutableStateOf(0)
    }



    Box(modifier = Modifier.fillMaxWidth()) {
        // MapsScreen(start = start, modifier = Modifier.fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
            //    .zIndex(.2f)
        ) {
            if (incomingJobViewModel.latLng.value != null) {
                MapComponent(float = 1f) {
                    GoogleMapWrapper(
                        cameraPosition = incomingJobViewModel.latLng.value,
                        cameraZoom = 12f
                    ) { mapUiSettings, mapProperties, cameraPositionState ->

                        GoogleMapSelectLocation(
                            cameraPositionState = cameraPositionState,
                            mapUiSettings = mapUiSettings,
                            mapProperties = mapProperties,
                            locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                            markerPosition = incomingJobViewModel.latLng.value,
                            markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                            title = "",
                            polylines = uiState.polylines
                        )

                        //MapsScreen(start = start)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            /*     dropyMainHeader()*/

            Row(
                modifier = Modifier
                    .clickable { }
                    .fillMaxWidth()
                    .height(95.dp)
                    .background(color = Color.White)
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column() {

                    val exten = if (uiState.hasongoingJob) {
                        "ONGOING"
                    } else {
                        "INCOMING"
                    }
                    Text(
                        text = "${exten} WORKS",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.axiformabold))
                        )
                    )

                    val extension = if (uiState.hasongoingJob) {
                        "ongoing"
                    } else {
                        "found some"
                    }

                    Text(
                        text = "You have ${extension} work. Please proceed",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.axiformaregular))
                        )
                    )
                }
            }

            val scope = rememberCoroutineScope()
            val context = LocalContext.current
            if (uiState.jobsList.isNotEmpty()) {
                uiState.infolist?.let {
                    IncomingJobsList(
                        incomingJobs = uiState.jobsList,
                        durationJobs = uiState.distanceslist,
                        onDecline = {
                            show.value = false
                            incomingJobViewModel.cancelIncomingJob(
                                deliveryPersonId = 2,
                                deliveryId = 2,
                                context = context
                            )
                        },
                        onAccept = {
                            scope.launch {
                                /*      fee.value = it.cost!!
                                                  show.value = true
                                                  it.orderId?.let { it1 -> incomingJobViewModel.orderAcceptedRider(it1, shopIncomingOrdersViewModel = shopIncomingOrdersViewModel) }
                                                  delay(2000)*/
                                incomingJobViewModel.searchIncomingJob(it.customerFirstName.toString())
                                incomingJobViewModel.setShopUrl(it.profilePicUrl.toString())
                                if (uiState.hasongoingJob) {

                                    incomingJobViewModel.setValues(
                                        it.customerFirstName.toString(),
                                        appViewModel
                                    )
                                } else {
                                    incomingJobViewModel.acceptIncomingJob(
                                        it.customerFirstName.toString(),
                                        appViewModel,
                                        it.deliveryId!!,
                                        context
                                    )
                                }
                                // incomingJobViewModel.setSection("shop")
                            }
                        },
                        customerName = incomingJobViewModel.customername.value,
                        hasOngoingJob = uiState.hasongoingJob,
                        incomingJobViewModel = incomingJobViewModel, infolist = it
                    )
                }
            } else {
                EmptyBlock()
            }

            if (show.value) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(19.dp)
                        .background(
                            Color(0xFF584AFF)
                        )
                ) {
                    Text(
                        text = "${fee.value}/- WILL BE DEDUCTED FROM WALLET",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformasemibold))
                        ),
                        letterSpacing = (-0.5).sp
                    )
                }
            }
        }
    }
}