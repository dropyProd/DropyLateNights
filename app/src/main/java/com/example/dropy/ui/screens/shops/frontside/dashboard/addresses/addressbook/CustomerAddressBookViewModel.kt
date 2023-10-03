package com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.addressbook

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.addresses.AddressListItemDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class AddressBookUiState(
    val addressList: List<AddressListItemDataClass> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class CustomerAddressBookViewModel @Inject constructor():ViewModel() {

    private val uiState = MutableStateFlow(AddressBookUiState())

    val addressBookUiState:StateFlow<AddressBookUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? = null

    init {
        getAddresses()
    }

    private fun getAddresses(){

        val addressList = mutableListOf(
            AddressListItemDataClass(
                locationTag = "tag",
                placeName = "some location name",
                addressId = 1
            ),
            AddressListItemDataClass(
                locationTag = "tag",
                placeName = "some location name",
                addressId = 1
            ),
            AddressListItemDataClass(
                locationTag = "tag",
                placeName = "some location name",
                addressId = 1
            ),
        )

        uiState.update { it.copy(addressList = addressList) }
    }


    fun onEditAddressClicked(addressId:Int){
        appViewModel?.navigate(ShopsFrontDestination.SINGLE_ADDRESS)
    }
    fun onDeleteAddressClicked(addressId:Int){

    }
    fun onAddAddressClicked(){

    }

}