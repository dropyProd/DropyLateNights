package com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.addressbook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.addresses.AddressBookContent

@Composable
fun CustomerAddressBook(
    customerAddressBookViewModel: CustomerAddressBookViewModel
){
    val appUiState = customerAddressBookViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by customerAddressBookViewModel.addressBookUiState.collectAsState()

    AppScaffold(
        content = {
            AddressBookContent(
                uiState = uiState,
                onEditAddressClicked = {customerAddressBookViewModel.onEditAddressClicked(it)},
                onDeleteAddressClicked = { customerAddressBookViewModel.onDeleteAddressClicked(it) },
                onAddAddressClicked = { customerAddressBookViewModel.onAddAddressClicked() }
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { customerAddressBookViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { customerAddressBookViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { customerAddressBookViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { customerAddressBookViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { customerAddressBookViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
    )
}


@Preview(showBackground = true)
@Composable
fun CustomerAddressBookPreview(){
    Column(Modifier.fillMaxSize()) {
        CustomerAddressBook(CustomerAddressBookViewModel())
    }
}