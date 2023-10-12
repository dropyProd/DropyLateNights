package com.example.dropy.ui.components.commons

import android.util.Log
import com.example.dropy.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.theme.DropyTheme

data class BottomNavItem(
    var icon: ImageVector,
    var pathName: String,
    var route: String
)

val navItems = listOf<BottomNavItem>(
    BottomNavItem(
        icon = Icons.Filled.Storefront,
        pathName = "shops",
        route = ShopsFrontDestination.SHOPS_HOME
    ),
    BottomNavItem(
        icon = Icons.Filled.Inbox,
        pathName = "orders",
        // route = /*ParcelDestination.PARCEL_HOME*/AppDestinations.PARCELS
        route = ShopsFrontDestination.CUSTOMER_ORDER_HISTORY
    ),
    BottomNavItem(
        icon = Icons.Filled.Home,
        pathName = "home",
        route = AppDestinations.APP_HOME
    ),
    BottomNavItem(
        icon = Icons.Filled.CameraRoll,
        pathName = "pay",
        route = RiderDestination.RIDERINCOMINGORDERS /*AppDestinations.REVIEWRIDER*//*AppDestinations.PARCELS*/
    ),
    BottomNavItem(
        icon = Icons.Filled.Menu,
        pathName = "menu",
        route = AppDestinations.MENU
    ),
)

@Composable
fun AppBottomNav(
    navigateTo: (route: String) -> Unit
) {


    BottomNavigation(
        // #DEDEDE
        backgroundColor = (Color(0xFFDEDEDE)),
        elevation = 0.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            navItems.forEach { item ->
                Box(
                    modifier = Modifier
                        .weight(1f, fill = true)
                        .clip(
                            if (item.pathName == "home") {
                                RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                            } else {
                                RoundedCornerShape(0.dp)
                            }
                        )
                        .fillMaxHeight()
                        .background(
                            color = if (item.pathName == "home") {
                                // Color(253, 211, 19)
                                Color.Black
                            } else {
                                Color.Transparent
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    BottomNavIcon(
                        icon = item.icon,
                        pathName = item.pathName,
                        onClick = {
                            Log.d("lopkvg", "AppBottomNav: ${item.pathName}")
                            if (item.pathName.equals("pay") && item.pathName.equals("shops")) {

                            } else {
                                navigateTo(item.route)
                            }


                        }
                    )
                }

            }
        }
    }
}

@Composable
fun BottomNavIcon(
    icon: ImageVector,
    pathName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(if (!pathName.uppercase().equals("HOME")) 40.dp else 55.dp)
        ) {
            if (!pathName.uppercase().equals("HOME"))
                Icon(imageVector = icon, contentDescription = "")
            else
                Icon(imageVector = icon, contentDescription = "", tint = Color.White)
        }
        if (!pathName.uppercase().equals("HOME")) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = pathName.uppercase(),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppBottonBarPreview() {
    DropyTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            AppBottomNav(navigateTo = {})
        }
    }
}
