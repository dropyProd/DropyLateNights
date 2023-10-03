package com.example.dropy.ui.components.commons.maps.selectpickupanddestinationlocations
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.AddressDataClass
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.autocopletepredictions.GoogleMapsAutoComplete
import com.example.dropy.ui.components.maps.selectlocation.SelectLocationContent
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun SelectPickupAndDestinationContent(
    selectPickupAndDestinationViewModel: SelectPickupAndDestinationViewModel = viewModel(),
    onLocationsSelected:(pickupLocation:AddressDataClass,destinationLocation:AddressDataClass)->Unit
){
    val uiState by selectPickupAndDestinationViewModel.selectPickupAndDestinationUiState.collectAsState()
    val context = LocalContext.current
    val editPickupLocation = remember {
        mutableStateOf(false)
    }
    val editDestinationLocation = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
        ,
    ){
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight(0.75f)
        ){
           // MapComponent()
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SelectPickupAndDestinationForm(
                pickupLocation = uiState.pickupLocation?.addressName,
                editPickupLocation = { editPickupLocation.value = true },
                destinationLocation = uiState.destinationLocation?.addressName,
                editDestinationLocation = { editDestinationLocation.value = true }
            )
            TotallyRoundedButton(
                buttonText = "next",
                backgroundColor = DropyYellow,
                action = {
                     if (uiState.pickupLocation == null){
                         Toast.makeText(
                             context,
                             "Please select a pickup location",
                             Toast.LENGTH_SHORT
                         ).show()
                     }else{
                         if (uiState.destinationLocation == null){
                             Toast.makeText(
                                 context,
                                 "Please select a destination location",
                                 Toast.LENGTH_SHORT
                             ).show()
                         }else{
                             onLocationsSelected(
                                 uiState.pickupLocation!!,
                                 uiState.destinationLocation!!
                             )
                         }
                     }
                },
                widthFraction = 0.6,
               // verticalPadding = 16
            )
        }

        if (editPickupLocation.value){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
               /* SelectLocationContent(
                    onConfirmClicked = {
                        selectPickupAndDestinationViewModel.setPickupLocation(it)
                        editPickupLocation.value = false
                    },
                    onCancelClicked = {editPickupLocation.value = false},
                    showCancelButton = true
                )*/
            }
        }

        if (editDestinationLocation.value){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
               /* SelectLocationContent(
                    onConfirmClicked = {
                        selectPickupAndDestinationViewModel.setDestinationLocation(it)
                        editDestinationLocation.value = false
                    },
                    onCancelClicked = {editDestinationLocation.value = false},
                    showCancelButton = true
                )*/
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun SelectLocationsContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        SelectPickupAndDestinationContent(
            viewModel(),
            onLocationsSelected = {_,_ ->}
        )
    }
}