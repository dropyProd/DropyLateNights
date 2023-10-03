package com.example.dropy.ui.components.commons.maps.autocopletepredictions

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.theme.DropyYellow
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse

@Composable
fun GoogleMapsAutoComplete(
    selectedLocation:(AutocompletePrediction)->Unit,
    autocompleteViewModel: AutocompleteViewModel = viewModel(),
    selectMyLocation:()->Unit,
    closeAutocompleteDialog:()->Unit
){
    val uiState = autocompleteViewModel.autoCompleteUiState.collectAsState()
    val context = LocalContext.current
    val placesClient = remember {
        Places.createClient(context)
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleText(
            text = "Search location",
            verticalPadding = 8
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            var dropdownExpanded by remember {
                mutableStateOf(uiState.value.autocompletePredictions.isNotEmpty())
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                OutlinedTextField(
                    value = uiState.value.queryString,
                    onValueChange = {
                        autocompleteViewModel.updateQueryString(it)
                        placesClient.findAutocompletePredictions(autocompleteViewModel.getSuggestionsRequest(it) )
                            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                                dropdownExpanded = true
                                autocompleteViewModel.updatePredictions(response.autocompletePredictions)
                            }.addOnFailureListener { exception: Exception? ->
                                if (exception is ApiException) {
                                    Toast.makeText(
                                        context,
                                        "Error finding predictions:${exception.message} ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                )
                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
//                    DropdownMenuItem(
//                        onClick = {
//                            selectMyLocation()
//                        }
//                    ) {
//                        Text(text = "My location")
//                    }
                    uiState.value.autocompletePredictions.forEachIndexed { _, autocompletePrediction ->
                        DropdownMenuItem(
                            onClick = {
                                selectedLocation(autocompletePrediction)
                            }
                        ) {
                            Text(text = autocompletePrediction.getFullText(null).toString())
                        }
                    }
                }
            }
        }
        TotallyRoundedButton(
            buttonText = "close",
            backgroundColor = DropyYellow,
            action = { closeAutocompleteDialog() },
            widthFraction = 0.6
        )
    }
}