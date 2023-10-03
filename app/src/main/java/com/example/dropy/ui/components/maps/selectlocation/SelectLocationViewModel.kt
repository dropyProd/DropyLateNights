package com.example.dropy.ui.components.commons.maps.selectlocation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.BuildConfig

import com.example.dropy.ui.components.commons.maps.AddressDataClass
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompletePrediction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SelectLocationViewModel @Inject constructor(
    // private val geocodingApiService: GeocodingApiService
) : ViewModel() {
    private val uiState = MutableStateFlow(SelectLocationUiState())
    val selectLocationUiState: StateFlow<SelectLocationUiState> = uiState.asStateFlow()

    val addressname = mutableStateOf("")

    fun deleteMarker() {
        val address = selectLocationUiState.value.address!!.copy(
            userLatitude = null,
            userLongitude = null
        )
        uiState.update {
            it.copy(
                address = address,
                markerPosition = null
            )
        }
    }

    fun onChangeShopLocation(name: String) {
        //Log.d("TAG", "onChangeShopLocation: $name")
        addressname.value = name
    }

    fun clearShopLocation() {
        addressname.value = ""
    }

    fun setUserLatLong(coordinates: LatLng) {
        if (uiState.value.markerPosition == null || uiState.value.address == null) {
            reverseGeocoding(coordinates)
        } else {
            val address = selectLocationUiState.value.address!!.copy(
                userLatitude = coordinates.latitude,
                userLongitude = coordinates.longitude
            )
            uiState.update {
                it.copy(
                    address = address,
                    markerPosition = coordinates
                )
            }
        }
    }

    fun geocoding(autocompletePrediction: AutocompletePrediction) {
        /*  val addressName = autocompletePrediction.getFullText(null).toString()
          viewModelScope.launch {
              val response = try {
                  geocodingApiService.addressGeocoding(addressName = addressName, apiKey = BuildConfig.GOOGLE_MAPS_API_KEY)
              }catch (e: IOException){
                  Log.e("ERROR FETCHING","io error")
                  return@launch
              }catch (e: HttpException){
                  Log.e("ERROR HTTP","http error")
                  return@launch
              }
              if (response.isSuccessful){
                  val responseBody = response.body()!!
                  if (responseBody.results.isNotEmpty()){
                      val result = responseBody.results[0]
                      val address = AddressDataClass(
                          addressName = result.formattedAddress,
                          apiLongitude = result.geometry.location.lng,
                          apiLatitude = result.geometry.location.lat,
                          placeId = result.placeId,
                          userLongitude = result.geometry.location.lng,
                          userLatitude = result.geometry.location.lat,
                      )
                      uiState.update {
                          it.copy(
                              address = address,
                              selectedAddressName = address.addressName!!,
                              cameraPosition = LatLng(address.apiLatitude!!,address.apiLongitude!!),
                              markerPosition = LatLng(address.apiLatitude, address.apiLongitude)
                          )
                      }
                  }
              }
          }*/
    }

    private fun reverseGeocoding(coordinates: LatLng) {
        /*      val latitude = coordinates.latitude
              val longitude = coordinates.longitude
              viewModelScope.launch {
                  val response = try {
                      geocodingApiService.latLongReverseGeocoding(latLong = "$latitude,$longitude", apiKey = BuildConfig.GOOGLE_MAPS_API_KEY)
                  }catch (e: IOException){
                      Log.e("ERROR FETCHING","io error")
                      return@launch
                  }catch (e: HttpException){
                      Log.e("ERROR HTTP","http error")
                      return@launch
                  }
                  Log.d("raw","${response.raw()}")
                  if (response.isSuccessful){
                      val responseBody = response.body()!!
                      if (responseBody.results.isNotEmpty()){
                          val result = responseBody.results[0]
                          val address = AddressDataClass(
                              addressName = result.formattedAddress,
                              apiLongitude = result.geometry.location.lng,
                              apiLatitude = result.geometry.location.lat,
                              placeId = result.placeId,
                              userLatitude = coordinates.latitude,
                              userLongitude = coordinates.longitude,
                          )
                          uiState.update {
                              it.copy(
                                  address = address,
                                  selectedAddressName = result.formattedAddress,
                                  cameraPosition = coordinates,
                                  markerPosition = coordinates
                              )
                          }
                      }
                  }
              }*/
    }
}