package com.example.dropy.ui.components.commons.maps.markers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun GoogleMapMarker(
    position: LatLng,
    title:String = "Marker title",
    snippet:String = "Marker snippet",
    infoWindowClicked: (()->Unit)? = null,
    infoWindowLongClick: (()->Unit)? = null,
){
    val infoWindowShown = remember {
        mutableStateOf(false)
    }
    Marker(
        state = MarkerState(position = position),
        title = title,
        snippet = snippet,
        onClick = {
            if (infoWindowShown.value){
                it.hideInfoWindow()
            }else{
                it.showInfoWindow()
            }
            infoWindowShown.value = !infoWindowShown.value
            true
        },
        onInfoWindowClick = {
            if (infoWindowClicked != null) {
                infoWindowClicked()
            }
        },
        onInfoWindowLongClick = {
            if (infoWindowLongClick != null) {
                infoWindowLongClick()
            }
        }

    )
}