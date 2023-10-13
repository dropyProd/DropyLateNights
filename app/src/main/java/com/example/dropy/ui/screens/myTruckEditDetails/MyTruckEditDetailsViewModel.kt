package com.example.dropy.ui.screens.myTruckEditDetails

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.getTruckDrivers.GetTruckDriversResItem
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.network.models.topUp.TopUpReq
import com.example.dropy.network.use_case.modifyTruckDetails.ModifyTruckDetailsUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckViewmodel
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

data class MyTruckEditDetailsUiState(
    val shopCoverPhoto: ImageBitmap? = null,
    val shopCoverPhotoUri: Uri? = null,
    val shopLogo: ImageBitmap? = null,
    val shopLogoUri: Uri? = null,
    val active: Boolean = false,
    val selectedTruckCapacity: String = "5,000LT",
    val truckDriverList: List<GetTruckDriversResItem> = listOf(),
    val truckCapacities: List<String> = listOf("5,000LT", "10,000LT"),
    val licensePlate: String = "",
    val logo: String = "",
    val shopLocation: AddressDataClass? = null,
    val selectedTruckId: String = "",
    val model: String = "",
    val selectedTruck: GetTrucksResItem? = null,
    val year: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class MyTruckEditDetailsViewModel @Inject constructor(
    private val app: DropyApp,
    private val modifyTruckDetailsUseCase: ModifyTruckDetailsUseCase
) : ViewModel() {


    val uiState = MutableStateFlow(MyTruckEditDetailsUiState())

    val myTruckEditDetailsUiState: StateFlow<MyTruckEditDetailsUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun changeActiveState(state: Boolean) {
        uiState.update {
            it.copy(
                active = state
            )
        }
    }

    fun setTruckCapacity(text: String) {
        uiState.update {
            it.copy(
                selectedTruckCapacity = text
            )
        }
    }

    fun onAddShopLogo(bitmap: Bitmap, uri: Uri, context: Context) {
        viewModelScope.launch {

/*            val file = FileUtil.from(context, uri)

// here selected image = image URI from gallay/camera
            val imageStream: InputStream = uri?.let {
                context?.contentResolver?.openInputStream(
                    it
                )
            }!!


             val baos = ByteArrayOutputStream()

            val temp = BitmapFactory.decodeStream(imageStream)

         val compressedBitmap =   temp.compress(Bitmap.CompressFormat.JPEG, 50, baos)*/
            /*  val editfile =   Compressor.compress(context, file) {
                     resolution(1280, 720)
                     quality(40)
                     format(Bitmap.CompressFormat.WEBP)
                     size(2_097_152) // 2 MB
                 }*/


            uiState.update {
                it.copy(
                    shopLogo = bitmap.asImageBitmap(),
                    shopLogoUri = uri
                )
            }
        }


        /* shopLogo.value = bitmap.asImageBitmap()
         shopLogoUri.value = uri
         Log.d("DDDDF", "onAddShopCoverPhoto: $bitmap $uri")*/

    }

    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }

    fun onAddShopCoverPhoto(bitmap: Bitmap, uri: Uri, context: Context) {
        viewModelScope.launch {

            /* val file = FileUtil.from(context, uri)
             Compressor.compress(context, file) {
                 resolution(1280, 720)
                 quality(40)
                 format(Bitmap.CompressFormat.WEBP)
                 size(2_097_152) // 2 MB
             }*/

            uiState.update {
                it.copy(
                    shopCoverPhoto = bitmap.asImageBitmap(),
                    shopCoverPhotoUri = uri
                )
            }
        }
        /*    Log.d("FFTAG", "onAddShopCoverPhoto: $bitmap $uri")
            shopCoverPhoto.value = bitmap.asImageBitmap()
            shopCoverPhotoUri.value = uri*/
    }

    fun onModelChange(text: String) {
        uiState.update {
            it.copy(
                model = text
            )
        }
    }

    fun onYearChange(text: String) {
        uiState.update {
            it.copy(
                year = text
            )
        }
    }

    fun onLicensePlateChange(text: String) {
        uiState.update {
            it.copy(
                licensePlate = text
            )
        }
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        uiState.update {
            it.copy(
                shopLocation = addressDataClass
            )
        }
    }

    fun setSelectedTruckId(getTrucksResItem: GetTrucksResItem) {
        uiState.update {
            it.copy(
                selectedTruckId = getTrucksResItem.id,
                active = getTrucksResItem.is_active,
                selectedTruckCapacity = if (getTrucksResItem.capacity.equals(5000)) "5,000LT" else "10,000LT",
                logo = getTrucksResItem.image,
                licensePlate = getTrucksResItem.license_plate,
                model = getTrucksResItem.model,
                year = getTrucksResItem.year.toString(),
                selectedTruck = getTrucksResItem
            )
        }
    }

    fun getTruckDrivers() {
        uiState.update {
            it.copy(truckDriverList = app.waterTruckDrivers)
        }
    }

    fun navigateLocation(addWaterTruckViewmodel: AddWaterTruckViewmodel) {
        addWaterTruckViewmodel.onRouteeChanged("EditTruck")
        appViewModel!!.navigate(AppDestinations.WATERTRUCK_LOCALE)
    }

    fun modifyTruck(context: Context) {
        viewModelScope.launch {
//            Toast.makeText(context, "reacheddddd", Toast.LENGTH_SHORT).show()
            modifyTruckDetailsUseCase(
                token = app.token.value,
                capacity = if (uiState.value.selectedTruckCapacity.equals("5,000LT")) 5000 else 10000,
                context = context,
                current_latitude = if (uiState.value.shopLocation == null) uiState.value.selectedTruck?.current_latitude.toString()
                else uiState.value.shopLocation?.latitude.toString(),
                current_longitude = if (uiState.value.shopLocation == null) uiState.value.selectedTruck?.current_longitude.toString()
                        else uiState.value.shopLocation?.longitude.toString(),
                registered_latitude =if (uiState.value.shopLocation == null) uiState.value.selectedTruck?.registered_latitude.toString()
                else uiState.value.shopLocation?.latitude.toString(),
                registered_longitude =if (uiState.value.shopLocation == null) uiState.value.selectedTruck?.registered_longitude.toString()
                else uiState.value.shopLocation?.longitude.toString(),
                current_location =if (uiState.value.shopLocation == null) uiState.value.selectedTruck?.current_location.toString()
                else uiState.value.shopLocation?.placeName.toString(),
                image = uiState.value.shopCoverPhotoUri,
                is_active = true,
                is_available = true,
                license_plate = uiState.value.licensePlate,
                model = uiState.value.model,
                year = uiState.value.year.toInt(),
                name = "n/a",
                shop_cover_photo = uiState.value.shopCoverPhotoUri,
                vendor = appViewModel!!.appUiState.value.activeProfile?.id.toString(),
                truckId = uiState.value.selectedTruckId
            ).flowOn(Dispatchers.IO)
                /* .catch { e ->
                     // handle exception
                     uiState.update { it.copy(pageLoading = false) }

                 }*/
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("KKTAG", "onAddShop: $result")
                            if (result.data != null) {
                                //  if (result.data?.resultCode?.equals(0) == true) {
                                //                                _addShopImagesUiState.update { it.copy(pageLoading = false) }
                                //                                moveAddProductCategory()
                                // }
                                Toast.makeText(
                                    context,
                                    result.data.license_plate + "updated success",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                uiState.update {
                                    it.copy(
                                        pageLoading = false
                                    )
                                }
                                appViewModel!!.getWaterTrucks()
                                appViewModel!!.navigate(AppDestinations.WATER_VENDOR_DASHBOARD)

//                                    appViewModel!!.navigate(AppDestinations.WATER_ORDER_SINGLE)

                            }
                            //                            _addShopImagesUiState.update { it.copy(pageLoading = false) }


                        }

                        is Resource.Loading -> {
                            uiState.update { it.copy(pageLoading = true) }
                        }

                        is Resource.Error -> {
                            //                            result.message?.let { message ->
                            uiState.update {
                                it.copy(
                                    pageLoading = false
                                )
                            }
                            //                            }

                        }
                    }

                }
        }
    }

}