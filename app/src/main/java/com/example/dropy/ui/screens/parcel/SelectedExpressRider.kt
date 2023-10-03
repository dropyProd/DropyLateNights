package com.example.dropy.ui.screens.parcel

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.R
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.order.BackgroundedImageText
import com.example.dropy.ui.components.parcel.favoriteRiderPojo
import com.example.dropy.ui.components.parcel.favoriteRiders
import com.example.dropy.ui.components.parcel.headerInfo
import com.example.dropy.ui.theme.LightBlue
import com.google.android.gms.maps.model.LatLng

@Composable
fun SelectedExpressRider(navController: NavController, route: String = "default", startLocale: LatLng? = null, latLng: LatLng? = LatLng(0.0, 0.0)) {
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
        ) {

            val (map, head) = createRefs()

   /*         if (startLocale != null) {
                MapsScreen(modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(map) {
                        top.linkTo(parent.top)
                    }, start = startLocale)
            }*/

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(map) {
                        top.linkTo(parent.top)
                    }
                    .zIndex(.2f)
            ) {
                if (latLng != null) {
                    MapComponent(float = 1f) {
                        GoogleMapWrapper(
                            cameraPosition = latLng
                        ) { mapUiSettings, mapProperties, cameraPositionState ->

                            GoogleMapSelectLocation(
                                cameraPositionState = cameraPositionState,
                                mapUiSettings = mapUiSettings,
                                mapProperties = mapProperties,
                                locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                                markerPosition = latLng,
                                markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                                title = ""
                            )

                            //MapsScreen(start = start)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(head) {
                        top.linkTo(parent.top)
                    },
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                dropyMainHeader(
                    modifier = Modifier
                        .zIndex(.3f)
                )

                BackgroundedText(
                    background = Color(0xFFFCD313),
                    textColor = Color.Black,
                    text = "6 RIDERS FOUND",
                    vertical = 3,
                    modifier = Modifier.padding(start = 14.dp)
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(top = 25.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            val selectedFavRider: MutableState<favoriteRiderPojo> = remember {
                mutableStateOf(favoriteRiderPojo(
                    image = R.drawable.imgone,
                    text = "TIMOTHY KARANJA NYORO",
                    eta = 7,
                    price = 200
                ))
            }

            headerInfo(
                selectedFavRider.value
            )
            favoriteRiders(navController = navController, route = route, returnItem = {
                selectedFavRider.value = it
            })

        }
    }
}

@Preview
@Composable
fun demo() {
    // SelectedExpressRider()
}


@Composable
fun rate() {
    ConstraintLayout() {

        val (image, txt) = createRefs()


        Image(
            painter = painterResource(id = R.drawable.fav),
            contentDescription = "",
            modifier = Modifier
                .size(33.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = "4.7",
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.constrainAs(txt) {
                top.linkTo(parent.top, 11.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}