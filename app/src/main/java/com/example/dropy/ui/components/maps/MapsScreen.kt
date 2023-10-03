package com.example.dropy.ui.components.maps
/*

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProductViewModel
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.maps.android.compose.*

private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        MapsInitializer.initialize(context)
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

@Composable
fun MapsScreen(
    modifier: Modifier = Modifier,
    showNearbyRiders: Boolean = false,
    start: LatLng,
) {

    val context = LocalContext.current
    val icon = bitmapDescriptorFromVector(
        context, R.drawable.bike
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(start, 18f)
    }
    //val start = LatLng(appViewModel.location.value!!.latitude, appViewModel.location.value!!.longitude)

    val pickup = LatLng(-1.320320, 36.705992)

    val polypointsPickup = remember {
        mutableListOf(
            LatLng(-1.319905, 36.706250),
            LatLng(-1.320002, 36.706237),
            LatLng(-1.320320, 36.705992),

            )
    }
    val polypointsRide = remember {
        mutableListOf(
            LatLng(-1.320320, 36.705992),
            LatLng(-1.320476, 36.705841),
            LatLng(-1.321990, 36.707112),
            LatLng(-1.322052, 36.707278),
            LatLng(-1.321974, 36.707578),
            LatLng(-1.321535, 36.708946),
            LatLng(-1.318391, 36.708910),
            LatLng(-1.317055, 36.708852),
            LatLng(-1.316167, 36.708240),
            LatLng(-1.316559, 36.707634),
            LatLng(-1.316685, 36.707741),
        )
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = start),
            title = "YOU",
            snippet = "YOU",
         //   icon = icon
        )

        if (showNearbyRiders) {
            for (i in 2 until 12) {

                val edit = LatLng((start.latitude - (0.0002 * i)), (start.longitude - (0.0002 * i)))

                Marker(
                    state = MarkerState(position = edit),
                    title = "YOU",
                    snippet = "YOU",
                    icon = icon
                )


            }
        }

        */
/*    Marker(
                state = MarkerState(position = pickup),
                title = "PICKUP",
                snippet = "PICKUP"
            )
            Marker(
                state = MarkerState(position = end),
                title = "DROP OFF",
                snippet = "DROP OFF"
            )
            if (show) {
                Polyline(points = polypointsPickup, color = Color(0xFF584AFF))
                Polyline(points = polypointsRide, color = Color.Black)
            } else {
                polypointsRide.forEach {
                    Marker(
                        state = MarkerState(position = it),
                        title = "bodaa",
                        snippet = "bodaa"
                    )
                }
            }*//*

    }
}

fun getLocation(context: Context): Location {
    val uid = Firebase.auth.currentUser?.uid
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    var locationNetwork: Location? = null
    var locationGps: Location? = null

    val locationres: MutableState<Location?> = mutableStateOf(null)

    if (hasGps || hasNetwork) {

        if (hasGps) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //    return locationres.value


            }


            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                object :
                    LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        if (p0 != null) {
                            locationGps = p0
                            if (uid != null) {
                                */
/*         Toast.makeText(
                                             context,
                                             "Longitude ${locationGps!!.longitude} Latitude,${locationGps!!.latitude}",
                                             Toast.LENGTH_SHORT
                                         ).show()*//*

                                */
/*   Firebase.firestore.collection("Drivers").document(uid).update("Longitude",
                                       locationGps!!.longitude,"Latitude", locationGps!!.latitude)
                                       .addOnSuccessListener {
                                           Snackbar.make(takeabreak, "Location Data feeds start", Snackbar.LENGTH_SHORT).show()
                                       }
                                       .addOnFailureListener {
                                           Snackbar.make(takeabreak, "Failed location feed", Snackbar.LENGTH_SHORT).show()
                                       }*//*

                            }
                        }
                    }

                })

            val localGpsLocation =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (localGpsLocation != null)
                locationGps = localGpsLocation
        }
        if (hasNetwork) {

            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F,
                object : LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        if (p0 != null) {
                            locationNetwork = p0
                            if (uid != null) {
                                */
/*  Toast.makeText(
                                      context,
                                      "Longitude ${locationNetwork!!.longitude} Latitude,${locationNetwork!!.latitude}",
                                      Toast.LENGTH_SHORT
                                  ).show()*//*


                                */
/*Firebase.firestore.collection("Drivers").document(uid).update("Longitude",
                                    locationNetwork!!.longitude,"Latitude", locationNetwork!!.latitude)
                                    .addOnSuccessListener {
                                        Snackbar.make(takeabreak, "Location Data feeds start", Snackbar.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener {
                                        Snackbar.make(takeabreak, "Failed location feed", Snackbar.LENGTH_SHORT).show()
                                    }*//*

                            }
                        }
                    }

                })

            val localNetworkLocation =
                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (localNetworkLocation != null)
                locationNetwork = localNetworkLocation
        }
        //locationres.value = locationNetwork
        if (locationGps != null) {
            if (locationGps!!.accuracy > locationNetwork!!.accuracy) {
                if (uid != null) {
                    Toast.makeText(
                        context,
                        "Longitude: ${locationGps!!.longitude} Latitude: ${locationGps!!.latitude}",
                        Toast.LENGTH_SHORT
                    ).show()
                    locationres.value = locationGps
                    //   appViewModel.setLocation(locationGps!!)
                    */
/* Firebase.firestore.collection("Drivers").document(uid).update(
                         "Longitude",
                         locationGps!!.longitude, "Latitude", locationGps!!.latitude
                     )
                         .addOnSuccessListener {
                             Snackbar.make(
                                 takeabreak,
                                 "Location Data feeds start",
                                 Snackbar.LENGTH_SHORT
                             ).show()
                         }
                         .addOnFailureListener {
                             Snackbar.make(
                                 takeabreak,
                                 "Failed location feed",
                                 Snackbar.LENGTH_SHORT
                             ).show()
                         }*//*

                }
            } else {
                if (uid != null) {
                    Toast.makeText(
                        context,
                        "Longitude: ${locationNetwork!!.longitude} Latitude: ${locationNetwork!!.latitude}",
                        Toast.LENGTH_SHORT
                    ).show()
                    locationres.value = locationNetwork
                    // appViewModel.setLocation(locationNetwork!!)
                    */
/*   Firebase.firestore.collection("Drivers").document(uid).update(
                           "Longitude",
                           locationNetwork!!.longitude, "Latitude", locationNetwork!!.latitude
                       )
                           .addOnSuccessListener {
                               Snackbar.make(
                                   takeabreak,
                                   "Location Data feeds start",
                                   Snackbar.LENGTH_SHORT
                               ).show()
                           }
                           .addOnFailureListener {
                               Snackbar.make(
                                   takeabreak,
                                   "Failed location feed",
                                   Snackbar.LENGTH_SHORT
                               ).show()
                           }*//*

                }
            }
        }else {
            locationres.value = locationNetwork
        }

    } else {
        context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }
    return locationres.value!!
}*/
