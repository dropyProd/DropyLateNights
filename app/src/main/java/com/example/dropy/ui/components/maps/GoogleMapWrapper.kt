package com.example.dropy.ui.components.commons.maps

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.app.AppViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GoogleMapWrapper(
    cameraPosition: LatLng? = null,
    cameraZoom: Float = 18f,
    appViewModel: AppViewModel? = null,
    mapInstance: @Composable (mapUiSettings: MapUiSettings, mapProperties: MapProperties, cameraPositionState: CameraPositionState) -> Unit,
    ) {

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    if (locationPermissionsState.allPermissionsGranted) {
        val context = LocalContext.current
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        val currentLocation: MutableState<Location?> = remember {
            mutableStateOf(null)
        }


        @SuppressLint("MissingPermission") val currentLocationRequest =
            fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY, null
            )

        currentLocationRequest.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentLocation.value = task.result
                if (currentLocation.value != null){
                    //appViewModel?.setYourLocale(LatLng(currentLocation.value!!.latitude, currentLocation.value!!.longitude))
                }
                if (task.result == null) {
             /*       Toast.makeText(
                        context,
                        "Could not get your current location",
                        Toast.LENGTH_SHORT
                    ).show()*/
                }
            } else {
                currentLocation.value = null
         /*       Toast.makeText(context, "Could not get your current location", Toast.LENGTH_SHORT)
                    .show()*/
            }
        }

        @SuppressLint("MissingPermission") val lastLocationResult =
            fusedLocationProviderClient.lastLocation

        val lastKnownLocation: MutableState<Location?> = remember {
            mutableStateOf(null)
        }

        lastLocationResult.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                lastKnownLocation.value = task.result
                if (lastKnownLocation.value != null){
                   // appViewModel?.setYourLocale(LatLng(currentLocation.value!!.latitude, currentLocation.value!!.longitude))
                }
                if (task.result == null) {
       /*             Toast.makeText(
                        context,
                        "Could not get your last known location",
                        Toast.LENGTH_SHORT
                    ).show()*/
                }
            } else {
                lastKnownLocation.value = null
     /*           Toast.makeText(
                    context,
                    "Could not get your last known location",
                    Toast.LENGTH_SHORT
                ).show()*/
            }
        }

        val cameraPositionState = rememberCameraPositionState()
        val defaultCameraPosition = LatLng(-1.2921, 36.8219)



        val alternateCameraPosition: MutableState<CameraPosition> = remember {
            mutableStateOf(
                if (currentLocation.value != null) {
                    CameraPosition.fromLatLngZoom(
                        LatLng(
                            currentLocation.value!!.latitude,
                            currentLocation.value!!.longitude
                        ), cameraZoom
                    )
                } else {
                    if (lastKnownLocation.value != null) {
                        CameraPosition.fromLatLngZoom(
                            LatLng(
                                lastKnownLocation.value!!.latitude,
                                lastKnownLocation.value!!.longitude
                            ), cameraZoom
                        )
                    } else {
                        CameraPosition.fromLatLngZoom(defaultCameraPosition, cameraZoom)
                    }
                }
            )
        }

        cameraPositionState.position = if (cameraPosition != null) {
            CameraPosition.fromLatLngZoom(cameraPosition, cameraZoom)
        } else {
            alternateCameraPosition.value
        }
        val mapUiSetting = remember {
            mutableStateOf(MapUiSettings(myLocationButtonEnabled = locationPermissionsState.allPermissionsGranted))
        }
        val mapProperties = remember {
            mutableStateOf(MapProperties(isMyLocationEnabled = locationPermissionsState.allPermissionsGranted))
        }
        mapInstance(
            mapUiSetting.value,
            mapProperties.value,
            cameraPositionState,
        )
    } else {
        Column {
            val allPermissionsRevoked =
                locationPermissionsState.permissions.size ==
                        locationPermissionsState.revokedPermissions.size
            val textToShow = if (!allPermissionsRevoked) {
                // If not all the permissions are revoked, it's because the user accepted the COARSE
                // location permission, but not the FINE one.
                "For better app experience, we would like to know your exact location."
            } else if (locationPermissionsState.shouldShowRationale) {
                // Both location permissions have been denied
                "Getting your exact location is important for this app. " +
                        "Please grant us fine location. Thank you :D"
            } else {
                // First time the user sees this feature or the user doesn't want to be asked again
                "This feature requires location permission"
            }
            val buttonText = if (!allPermissionsRevoked) {
                "Allow precise location"
            } else {
                "Request permissions"
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = textToShow)
                Button(
                    onClick = { locationPermissionsState.launchMultiplePermissionRequest() },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(buttonText)
                }
            }
        }
    }
}