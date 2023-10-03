package com.example.dropy.ui.screens.rider.becomerider

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.RiderBackside
import com.example.dropy.ui.components.commons.AddressDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddRiderUiState(
    val riderName: String = "",
    val riderLocation: AddressDataClass? = null,
    val riderPhoneOne: String = "",
    val riderId: String = "",
    val deliveryMethod: String = "",
    val riderMethodList: List<String> = listOf("On Foot", "Cyclist", "Rider"),
    val riderProfileLogo: ImageBitmap? = null,
    val riderProfileLogoUri: Uri? = null,
    val riderBikeCoverPhoto: ImageBitmap? = null,
    val riderBikeCoverPhotoUri: Uri? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class AddRiderViewModel @Inject constructor(

) : ViewModel() {

    val _addRiderUiState = MutableStateFlow(AddRiderUiState())
    val addRiderUiState: StateFlow<AddRiderUiState> = _addRiderUiState.asStateFlow()

    val appViewModel: MutableState<AppViewModel?> = mutableStateOf(null)

    fun setAppViewModel(appViewModell: AppViewModel) {
        appViewModel.value = appViewModell
    }

    fun goToRiderUploads(context: Context) {
        if (addRiderUiState.value.riderLocation?.equals(null) == false) {

        } else {
            Toast.makeText(context, "Location is empty", Toast.LENGTH_SHORT).show()
        }
    }

    fun setRiderName(name: String) {
        _addRiderUiState.update {
            it.copy(
                riderName = name
            )
        }
    }

    fun setRiderPhone(phone: String) {
        _addRiderUiState.update {
            it.copy(
                riderPhoneOne = phone
            )
        }
    }

    fun setRiderId(id: String) {
        _addRiderUiState.update {
            it.copy(
                riderId = id
            )
        }
    }

    fun onAddCoverPhoto(bitmap: Bitmap, uri: Uri, context: Context) {

        viewModelScope.launch {
          /*  val file = FileUtil.from(context, uri)
            Compressor.compress(context, file) {
                resolution(1280, 720)
                quality(40)
                format(Bitmap.CompressFormat.WEBP)
                size(2_097_152) // 2 MB
            }*/

            _addRiderUiState.update {
                it.copy(
                    riderBikeCoverPhoto = bitmap.asImageBitmap(),
                    riderBikeCoverPhotoUri = uri/*FileUtil.getImageUri(
                        context,
                        BitmapFactory.decodeFile(file!!.absolutePath)
                    )*/
                )
            }

        }

    }

    fun onAddLogo(bitmap: Bitmap, uri: Uri, context: Context) {

        viewModelScope.launch {

          /*  val file = FileUtil.from(context, uri)
            Compressor.compress(context, file) {
                resolution(1280, 720)
                quality(40)
                format(Bitmap.CompressFormat.WEBP)
                size(2_097_152) // 2 MB
            }*/

            _addRiderUiState.update {
                it.copy(
                    riderProfileLogo = bitmap.asImageBitmap(),
                    riderProfileLogoUri = uri/*FileUtil.getImageUri(
                        context,
                        BitmapFactory.decodeFile(file!!.absolutePath)
                    )*/
                )
            }
        }
    }

    fun setRiderMethod(method: String) {
        _addRiderUiState.update {
            it.copy(
                deliveryMethod = method
            )
        }
    }


    fun addAddress(addressDataClass: AddressDataClass) {
        _addRiderUiState.update {
            it.copy(
                riderLocation = addressDataClass
            )
        }
    }

    fun navigateAddriderLocation(context: Context) {
        if (!addRiderUiState.value.riderName.equals("") && !addRiderUiState.value.riderId.equals("") && !addRiderUiState.value.deliveryMethod.equals(
                ""
            ) && !addRiderUiState.value.riderPhoneOne.equals("")
        ) {
            appViewModel.value?.navigate(RiderBackside.RIDERLOCATION)
        } else {
            Toast.makeText(context, "Fill all required values", Toast.LENGTH_SHORT).show()
        }
    }

    fun addRiderImage(context: Context) {
        if (addRiderUiState.value.riderLocation?.equals(null) == false) {

            appViewModel.value?.navigate(RiderBackside.RIDERIMAGE)
        } else {
            Toast.makeText(context, "Choose location", Toast.LENGTH_SHORT).show()
        }
    }
}