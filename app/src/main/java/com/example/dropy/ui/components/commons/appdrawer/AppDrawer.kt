package com.example.dropy.ui.components.commons.appdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.*
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
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue


@Composable
fun Drawer(
    userProfiles: List<ActiveProfileDataClass>,
    onSelectProfile: (ActiveProfileDataClass) -> Unit,
    activeProfile: ActiveProfileDataClass?,
    drawerMenuItems: List<DrawerMenuItem>,
    drawerMenuItemClicked: (route: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        DrawerHeader(
            profiles = userProfiles,
            onSelect = { onSelectProfile(it) },
            activeProfile = activeProfile
        )
        DrawerBody(
            items = drawerMenuItems,
            onItemClick = { drawerMenuItemClicked(it.route) },
            onSignOutClicked = { drawerMenuItemClicked("signOut") }
        )
    }
}

@Composable
fun DrawerHeader(
    profiles: List<ActiveProfileDataClass>,
    onSelect: (ActiveProfileDataClass) -> Unit,
    activeProfile: ActiveProfileDataClass?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 64.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logonewblack),
                contentDescription = "dropy logo",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )

        }
        SelectProfile(
            profiles = profiles,
            onSelect = { onSelect(it) },
            activeProfile = activeProfile
        )
    }

}

@Composable
fun SelectProfile(
    profiles: List<ActiveProfileDataClass>,
    onSelect: (ActiveProfileDataClass) -> Unit,
    activeProfile: ActiveProfileDataClass?
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {

        SimpleText(
            text = "Logged in as ${activeProfile?.type}",
            padding = 8,
            fontWeight = FontWeight.ExtraBold,
            font = Font(R.font.axiformaextrabold)
        )

        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.TopStart)
        ) {
            Box(
                modifier = Modifier
                    .clickable(onClick = { expanded = true })
                    .height(48.dp)
                    .border(2.dp, Color.LightGray)
                    .background(Color.White),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = "${activeProfile?.name}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.axiformablack)))
                    )

                    BackgroundedText(
                        background = if (activeProfile?.type.equals(ProfileTypes.CUSTOMER)) DropyYellow else Color(
                            0xFF02CBE3
                        ),
                        textColor = Color.Black,
                        text = "${activeProfile?.type}",
                        vertical = 2
                    )
                }
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "dropdown icon",
                        modifier = Modifier
                            .size(32.dp)
                    )

                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                profiles.forEachIndexed { index, profile ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onSelect(profile)
                        }
                    ) {
                        Text(
                            text = profile.name, fontFamily = FontFamily(Font(R.font.axiformabold)),
                            fontWeight = FontWeight.Bold
                        )

                        BackgroundedText(
                            background = if (profile.type.equals(ProfileTypes.CUSTOMER)) DropyYellow else Color(
                                0xFF02CBE3
                            ),
                            textColor = Color.Black,
                            text = "${profile.type}",
                            vertical = 2,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun DrawerBody(
    items: List<DrawerMenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (DrawerMenuItem) -> Unit,
    onSignOutClicked: () -> Unit
) {
    Column(modifier) {
        for (item in items) {
            Row(
                modifier = Modifier
                    .padding(top = 24.dp, start = 51.dp)
                    .width(217.dp)
                    .height(49.dp)
                    .clickable {
                        onItemClick(item)
                    }.border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(bottomEnd = 14.dp))
                    .background(color = Color(0xFFF5F5F5),shape = RoundedCornerShape(bottomEnd = 14.dp))
                    .padding(start = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.QrCode,
                    contentDescription = "next"
                )
                Spacer(modifier = Modifier.width(27.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f),
                    fontFamily = FontFamily(Font(R.font.axiformabold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    letterSpacing = -(.72).sp
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 51.dp)
                .width(217.dp)
                .height(49.dp)
                .clickable {
                    onSignOutClicked()
                }.border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(bottomEnd = 14.dp))
                .background(color = Color(0xFFF5F5F5),shape = RoundedCornerShape(bottomEnd = 14.dp))
                .padding(start = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.QrCode,
                contentDescription = "next"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Sign out",
                style = itemTextStyle,
                modifier = Modifier.weight(1f),
                fontFamily = FontFamily(Font(R.font.axiformabold)),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                letterSpacing = -(.72).sp
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    val profiles = listOf(
        ActiveProfileDataClass(
            type = "customer",
            name = "claudius",
            id = "1"
        ),
        ActiveProfileDataClass(
            type = "shop",
            name = "my shop name",
            id = "1"
        ),
        ActiveProfileDataClass(
            type = "rider",
            name = "claudius",
            id = "1"
        )
    )
    val drawerMenus = listOf(
        DrawerMenuItem(
            route = "home",
            title = "Home"
        ),
        DrawerMenuItem(
            route = "settings",
            title = "Settings"
        ),
        DrawerMenuItem(
            route = "help",
            title = "Help"
        ),
    )

    Drawer(
        drawerMenuItems = drawerMenus,
        drawerMenuItemClicked = {},
        userProfiles = profiles,
        onSelectProfile = {},
        activeProfile = ActiveProfileDataClass(type = "type", name = "name", id = "1")
    )
}