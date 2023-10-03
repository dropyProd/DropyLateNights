package com.example.dropy.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.Drawer
import com.example.dropy.ui.components.commons.appdrawer.DrawerMenuItem
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppScaffold(
    content: @Composable () -> Unit,
    showBackButton: Boolean = true,
    pageLoading: Boolean = false,
    actionLoading: Boolean = false,
    errorList: List<String> = emptyList(),
    messageList: List<String> = emptyList(),
    onBackButtonClicked: () -> Unit,
    onDashboardButtonClicked: () -> Unit,
    onCartButtonClicked: () -> Unit,
    navigateTo: (route: String) -> Unit,
    drawerMenuItems: List<DrawerMenuItem>,
    userProfiles: List<ActiveProfileDataClass>,
    onSelectProfile: (ActiveProfileDataClass) -> Unit,
    activeProfile: ActiveProfileDataClass?,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    cartsize: Int = 0,
    showimg: Boolean = false,
    showSuperApp: Boolean = false,
    showLogo: Boolean = true,
    showImageRight: Boolean = false,
    frontSide: Boolean = true,
    showCart: Boolean? = null,
    showLogoBackside: Boolean = true,
    showSearchBar: Boolean = false,
    onSearchItem: (() -> Unit)?=null,
    searchItem: ((String) -> Unit)?=null,
    topBarBackendside: @Composable () -> Unit = {
        AppBackSideTopBar(
            showLogoBackside = true,
            onBackButtonClicked = { onBackButtonClicked() },
            onDashboardButtonClicked = {

                onDashboardButtonClicked()
            },

            )

    },
    topBar: @Composable () -> Unit = {
        AppTopBar(
            showCartButton = if (showCart != null) showCart else activeProfile?.type == ProfileTypes.CUSTOMER,
            showBackButton = showBackButton,
            showSuperApp = showSuperApp,
            onBackButtonClicked = { onBackButtonClicked() },
            onDashboardButtonClicked = {
                //   showimg.value = false
                onDashboardButtonClicked()
            },
            onCartButtonClicked = { onCartButtonClicked() },
            showImage = showimg,
            cartsize = cartsize,
            showLogo = showLogo,
            showImageRight = showImageRight,
            showSearchBar = showSearchBar,
            onSearchItem = onSearchItem,
            searchItem = searchItem
        )
    }


) {
    val appViewModel: AppViewModel = hiltViewModel()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            if (frontSide)
                topBar()
            else
                topBarBackendside()
        },
        drawerContent = {
            Drawer(
                drawerMenuItems = drawerMenuItems,
                drawerMenuItemClicked = {
                    if (scaffoldState.drawerState.isOpen) {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                    navigateTo(it)
                },
                userProfiles = userProfiles,
                onSelectProfile = {
                    onSelectProfile(it)
                },
                activeProfile = activeProfile
            )
        },

        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        bottomBar = {
            AppBottomNav(
                navigateTo = {
                    if (it == AppDestinations.MENU) {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    } else {
                        navigateTo(it)
                    }
                }
            )
        },

        ) {
        if (pageLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    content()
                }
                if (actionLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.7f)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppScaffoldPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppScaffold(
            content = { Text(text = "some text") },
            onBackButtonClicked = {},
            onDashboardButtonClicked = {},
            onCartButtonClicked = {},
            navigateTo = {},
            drawerMenuItems = emptyList(),
            userProfiles = emptyList(),
            onSelectProfile = {},
            activeProfile = ActiveProfileDataClass(type = "type", name = "name", id = "1"),
        )
    }
}