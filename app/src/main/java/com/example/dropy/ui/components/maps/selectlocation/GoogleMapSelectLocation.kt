package com.example.dropy.ui.components.commons.maps.selectlocation


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsResItem
import com.example.dropy.ui.components.commons.maps.markers.GoogleMapMarker
import com.example.dropy.ui.screens.apphome.ProductSearch
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

data class CustLatLng(
    val name: String,
    val locale: LatLng
)

@Composable
fun GoogleMapSelectLocation(
    markerPosition: LatLng? = null,
    markerPositionList: List<ProductSearch> = listOf(),
    waterpointsMarkerPositionList: List<GetWaterPointsResItem> = listOf(),
    trucksMarkerPositionList: List<AssignedTruck> = listOf(),
    trucksRouteMarkerPositionList: List<CustLatLng> = listOf(),
    cameraPositionState: CameraPositionState,
    mapUiSettings: MapUiSettings,
    mapProperties: MapProperties,
    locationSelected: ((LatLng) -> Unit)? = null,
    markerInfoWindowClicked: (() -> Unit)? = null,
    waterpointMarkerInfoWindowClicked:( (GetWaterPointsResItem) -> Unit)? = null,
    truckMarkerInfoWindowClicked:( (AssignedTruck) -> Unit)? = null,
    truckRouteMarkerInfoWindowClicked:( (CustLatLng) -> Unit)? = null,
    title: String = "Shop Location",
    snippet: String = "Long press to delete marker",
    polylines: List<LatLng> = listOf(),
    polylinesCustomer: List<LatLng> = listOf()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
            properties = mapProperties,
            onMapLongClick = {
                if (locationSelected != null) {
                    locationSelected(it)
                }
            }
        ) {
           if (polylines.isEmpty()){
               if (markerPosition?.equals(null) == false) {
                   GoogleMapMarker(
                       position = markerPosition!!,
                       title = title,
                       snippet = snippet,
                       infoWindowLongClick = {
                           if (markerInfoWindowClicked != null) {
                               markerInfoWindowClicked()
                           }
                       }

                   )
               } else {
                   if (!markerPositionList.isEmpty()) {
                       markerPositionList.forEach {
                           GoogleMapMarker(
                               position = LatLng(it.shop.shop_location?.toDouble()/*.latitude*/!!, it.shop.shop_location.toDouble()/*.longitude*/!!),
                               title = it.shop.shop_name!!,
                               snippet = snippet,
                               infoWindowLongClick = {
                                   if (markerInfoWindowClicked != null) {
                                       markerInfoWindowClicked()
                                   }
                               }
                           )
                       }
                   }
                   else if (!waterpointsMarkerPositionList.isEmpty()) {
                       Log.d("lopkmj", "GoogleMapSelectLocation: $waterpointsMarkerPositionList")
                       waterpointsMarkerPositionList.forEach {
                           GoogleMapMarker(
                               position = LatLng(it.latitude.toDouble()/*.latitude*/, it.longitude.toDouble()/*.longitude*/),
                               title = it.name!!,
                               snippet = snippet,
                               infoWindowClicked = {
                                   if (waterpointMarkerInfoWindowClicked != null) {
                                       waterpointMarkerInfoWindowClicked(it)
                                   }
                               }
                           )
                       }
                   }
                   else if (!trucksMarkerPositionList.isEmpty()) {
                       Log.d("lopkmj", "GoogleMapSelectLocation: $waterpointsMarkerPositionList")
                       trucksMarkerPositionList.forEach {
                           GoogleMapMarker(
                               position = LatLng(it.current_latitude.toDouble()/*.latitude*/, it.current_longitude.toDouble()/*.longitude*/),
                               title = it.name!!,
                               snippet = snippet,
                               infoWindowClicked = {
                                   if (truckMarkerInfoWindowClicked != null) {
                                       truckMarkerInfoWindowClicked(it)
                                   }
                               }
                           )
                       }
                   }
               }
           }else {
               Log.d("jijij", "GoogleMapSelectLocation: ${polylines}")
               if (!trucksRouteMarkerPositionList.isEmpty()) {
                   Log.d("lopkmj", "GoogleMapSelectLocation: $waterpointsMarkerPositionList")
                   trucksRouteMarkerPositionList.forEach {
                       GoogleMapMarker(
                           position = it.locale,
                           title = it.name!!,
                           snippet = snippet,
                           infoWindowClicked = {
                               if (truckRouteMarkerInfoWindowClicked != null) {
                                   truckRouteMarkerInfoWindowClicked(it)
                               }
                           }
                       )
                   }
               }
               Polyline(points = polylines)
               if (polylinesCustomer.isNotEmpty()){
                   Polyline(points = polylinesCustomer)
               }
   /*            polylines.forEach {

                   Marker(state = MarkerState(position = it))
*//*                   GoogleMapMarker(
                       position = it!!,
                       title = "it.shop.shop_name!!",
                       snippet = "Long press to delete marker",
                       infoWindowLongClick = { markerInfoWindowClicked() }
                   )*//*
               }*/
           }

        }
    /*    Text(
            text = "Long press to mark location",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            textAlign = TextAlign.Center
        )*/
    }

}