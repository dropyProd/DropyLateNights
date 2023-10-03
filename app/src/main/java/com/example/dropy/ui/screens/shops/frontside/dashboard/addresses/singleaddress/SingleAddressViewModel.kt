package com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class SingleAddressUiState(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val placeName: String = "",
    val placeId: String = "",
    val region: String = "",
    val floor: String = "",
    val additionalInformation: String? = null,
    val locationTagList: List<AddressDataClass> = listOf(
        AddressDataClass(
            locationTag = "OTHER",
            customTag = "OTHER"
        )
    ),
    val yourAddress: AddressDataClass? = null,
    val locationTag: String? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class MyLocation(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val placeName: String = "",
    val locationTag: String = "",
    val placeId: String = "",
    val region: String = "",
    val floor: String = "",
)

@HiltViewModel
class SingleAddressViewModel @Inject constructor() : ViewModel() {

    private val uiState = MutableStateFlow(SingleAddressUiState())

    val singleAddressUiState: StateFlow<SingleAddressUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun onAddressChanged(text: String) {
        uiState.update {
            it.copy(
                placeName = text
            )
        }
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        uiState.update {
            it.copy(
                yourAddress = addressDataClass
            )
        }
    }

    fun onLocationChanged(text: String) {
        uiState.update {
            it.copy(
                region = text,
                yourAddress = uiState.value.yourAddress?.copy(buildingName = text)
            )
        }
    }

    fun onFloorChanged(text: String) {
        uiState.update {
            it.copy(
                floor = text,
                yourAddress = uiState.value.yourAddress?.copy(floorNumber = text)
            )
        }
    }

    fun onAdditionalInformationChanged(additionalInfo: String) {

    }

    fun onLocationTagChanged(locationTag: String) {
        uiState.update {
            it.copy(
                locationTag = locationTag,
                yourAddress = uiState.value.yourAddress?.copy(customTag = locationTag)
            )
        }
    }


    fun updateLocationTag() {

        val current: MutableList<AddressDataClass> = uiState.value.locationTagList.toMutableList()
        val item = uiState.value.yourAddress
        current.forEach {
            if (it.customTag.equals(uiState.value.locationTag)) {
                current.remove(it)
            }
        }
        if (item != null) {
            current.add(item)
        }
        uiState.update {
            it.copy(
                locationTagList = current,
                locationTag = "",
                floor = "",
                region = "",
                yourAddress = AddressDataClass()
            )
        }
    }

}