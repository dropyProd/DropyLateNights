package com.example.dropy.ui.screens.pickCustomer

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dropy.R
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.TotallyRoundedButtonBorder
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.rider.IncomingJobUiState
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.rider.PickCustomerUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import com.example.dropy.ui.theme.grayText
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun PickCustomer(
    incomingJobViewModel: IncomingJobViewModel,
    scanQr: () -> Unit,
    useCode: () -> Unit,
    uiState: PickCustomerUiState,
    appuiState: AppUiState,
    incomingJobUiState: IncomingJobUiState,
    type: String
) {


    Log.d("juuyuy", "PickCustomer: $type")

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (mp, bdy) = createRefs()
        /*if (startLocale != null) {
            MapsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(mp) {
                        top.linkTo(parent.top)
                    }, start = startLocale
            )
        }*/

        if (incomingJobViewModel.latLng.value != null) {
            MapComponent {
                GoogleMapWrapper(
                    cameraPosition = incomingJobViewModel.latLng.value,
                    cameraZoom = 12f
                ) { mapUiSettings, mapProperties, cameraPositionState ->

                    GoogleMapSelectLocation(
                        cameraPositionState = cameraPositionState,
                        mapUiSettings = mapUiSettings,
                        mapProperties = mapProperties,
                        locationSelected = { },
                        markerPosition = incomingJobViewModel.latLng.value,
                        markerInfoWindowClicked = {},
                        polylines = incomingJobUiState.polylines
                    )

                    //MapsScreen(start = start)
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 50.dp, top = 20.dp)
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val exten = remember {
                mutableStateOf(
                    if (type == "shop") "DESTINATION" else "CUSTOMER"
                )
            }

            val timeString: String = if (incomingJobViewModel.currentduration.value >= 3600) {
                val hrs: Int = incomingJobViewModel.currentduration.value / 3600
                val min: Int = incomingJobViewModel.currentduration.value % 3600 / 60
                // val seconds: Int = input % 3600 % 60
                // val hrs = timeInMin / 60
                //  val min = timeInMin % 60
                "$hrs HOURS $min MINUTES"
            } else {
                val min = incomingJobViewModel.currentduration.value / 60
                "$min MINUTES"
            }

            Text(
                text = "${timeString} TO ${exten.value}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
        Column(
            modifier = Modifier
                .clickable {


                }
                .constrainAs(bdy) {
                    bottom.linkTo(parent.bottom)
                }
                .padding(horizontal = 7.dp)
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp),
                    color = Color.LightGray.copy(.4f)
                ),
        ) {
            Text(text = incomingJobViewModel.response.value.toString(), color = Color.Red)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, end = 13.dp, top = 15.dp, bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BackgroundedText(
                    background = Color(0xFFFCD313),
                    textColor = Color.Black,
                    text = if (type == "shop") "SHOP DETAILS" else "CUSTOMER DETAILS"
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp, top = 17.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (type == "shop") {
                            LoadImage(
                                imageUrl = uiState.shopprofilePic, modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray.copy(.4f),
                                        shape = CircleShape
                                    ),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            LoadImage(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFF584AFF),
                                        shape = CircleShape
                                    ),
                                contentScale = ContentScale.Crop
                            )
                        }


                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = (if (type.equals("shop")) uiState.shopName.toString() else appuiState.activeProfile?.name).toString(),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                                )
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {

                                val extension = if (type.equals("shop")) "orders" else "trips"
                                BackgroundedText(
                                    background = Color(0xFFFCD313),
                                    textColor = Color.Black,
                                    text = "${Random.nextInt(1, 3)} $extension",
                                    vertical = 3
                                )

                                BackgroundedText(
                                    background = Color.Black,
                                    textColor = Color.White,
                                    text = "${uiState.shopDistance}",
                                    vertical = 2
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.offset(y = 12.dp)
                    ) {

                        backgroundedIcon(background = Color(0xFF1BFC13), image = R.drawable.phone)
                        backgroundedIcon(background = Color(0xFFFCD313), image = R.drawable.email)
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TotallyRoundedButton(
                        buttonText = "scan qr",
                        textFontSize = 12,
                        textColor = Color.White,
                        backgroundColor = LightBlue,
                        widthFraction = 0.6,
                        action = {
                            scope.launch {
                                Toast
                                    .makeText(
                                        context,
                                        "Preparing to start scanner",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                                delay(4000)
                                scanQr()
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                        /* .padding(bottom = 20.dp)*/
                    )

                    TotallyRoundedButtonBorder(
                        buttonText = "use code",
                        textFontSize = 12,
                        textColor = Color.Black,
                        backgroundColor = Color.Transparent,
                        widthFraction = 0.8,
                        action = {
                            scope.launch {
                                useCode()
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 20.dp)
                    )

                }
            }


        }
    }
}

@Composable
fun backgroundedIcon(
    background: Color,
    image: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .size(28.dp)
            .background(background, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .size(17.dp),
            colorFilter = ColorFilter.tint(color = Color.Black)
        )

    }
}

@Composable
fun backgroundedTextIcon(
    background: Color,
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .background(background, shape = RoundedCornerShape(10.dp))
            .padding(start = 7.dp, top = 3.dp, bottom = 3.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            Icons.Filled.MyLocation,
            contentDescription = "",
            modifier = Modifier
                .size(17.dp),
            tint = Color.Black
        )

        SimpleText(
            text = text,
            fontWeight = FontWeight.ExtraBold,
            textSize = 10,
            font = Font(R.font.axiformaextrabold),
            textColor = Color.Black,
            fontStyle = FontStyle.Normal
        )

    }
}

@Preview(showBackground = true)
@Composable
fun screeny() {
    TotallyRoundedButtonBorder(
        buttonText = "use code",
        textFontSize = 12,
        textColor = Color.Black,
        backgroundColor = Color.Transparent,
        widthFraction = 0.8,
        action = {
            /* scope.launch {
                 useCode()
             }*/
        },
        modifier = Modifier
            // .align(Alignment.CenterHorizontally)
            .padding(bottom = 20.dp)
    )
}