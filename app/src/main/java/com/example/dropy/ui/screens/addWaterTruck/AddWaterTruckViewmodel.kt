package com.example.dropy.ui.screens.addWaterTruck

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.addWaterDriver.AddWaterDriverReq
import com.example.dropy.network.models.addWaterTruckRes.AddWaterTruckRes
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.network.use_case.addWaterTruckUseCase.AddWaterTruckUseCase
import com.example.dropy.network.use_case.createDriver.CreateDriverUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointImagesUiState
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointLocationUiState
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointUiState
import com.example.dropy.ui.screens.addWaterPoint.WaterpointUploadsUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject


data class AddWaterTruckUiState(
    val driverId: String = "",
    val selectedTruckCapacity: String = "5,000LT",
    val truckCapacities: List<String> = listOf("5,000LT", "10,000LT"),
    val shopLocation: AddressDataClass? = null,
    val licensePlate: String = "",
    val shopPhoneTwo: String = "",
    val model: String = "",
    val year: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class AddWaterTruckLocationUiState(
    val shopAddress: AddressDataClass? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class WaterTruckUploadsUiState(
    val shopLogo: ImageBitmap? = null,
    val shopCoverPhoto: ImageBitmap? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class AddWaterTruckImagesUiState(
    val shopLogo: ImageBitmap? = null,
    val shopLogoUri: Uri? = null,
    val shopCoverPhoto: ImageBitmap? = null,
    val shopCoverPhotoUri: Uri? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class AddWaterTruckViewmodel @Inject constructor(
    private val app: DropyApp,
    private val addWaterTruckUseCase: AddWaterTruckUseCase
) : ViewModel() {

    val _addWaterTruckUiState = MutableStateFlow(AddWaterTruckUiState())
    val addWaterTruckUiState: StateFlow<AddWaterTruckUiState> = _addWaterTruckUiState.asStateFlow()

    private val _addWaterTruckLocationUiState = MutableStateFlow(AddWaterTruckLocationUiState())
    val addWaterTruckLocationUiState: StateFlow<AddWaterTruckLocationUiState> =
        _addWaterTruckLocationUiState.asStateFlow()

    private val _addWaterTruckImagesUiState = MutableStateFlow(AddWaterTruckImagesUiState())
    val addWaterTruckImagesUiState: StateFlow<AddWaterTruckImagesUiState> =
        _addWaterTruckImagesUiState.asStateFlow()


    private val _waterTruckUploadsUiState = MutableStateFlow(WaterTruckUploadsUiState())

    val waterTruckUploadsUiState: StateFlow<WaterTruckUploadsUiState> =
        _waterTruckUploadsUiState.asStateFlow()

    var appViewModel: AppViewModel? = null
    val shopLogo: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopLogoUri: MutableState<Uri?> = mutableStateOf(null)
    val shopCoverPhoto: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopCoverPhotoUri: MutableState<Uri?> = mutableStateOf(null)

    fun onShopNameChanged(shopName: String) {
        _addWaterTruckUiState.update {
            it.copy(
                driverId = shopName
            )
        }
    }

    /* fun onBranchManagerNameChanged(branchManagerName: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 branchManager = branchManagerName
             )
         }
     }

     fun changeTextFState(state: Boolean) {
         _addWaterTruckUiState.update {
             it.copy(
                 branchManagerState = state
             )
         }
     }

     fun addBranchName(name: String) {
         val list = mutableListOf<Int>()
         if (_addWaterTruckUiState.value.branchManagers.isNotEmpty()) {
             _addWaterTruckUiState.value.branchManagers.forEach {
                 list.add(it.toInt())
             }
         }
         list.add(name.toInt())

         _addWaterTruckUiState.update {
             it.copy(
                 branchManagers = list,
                 branchManager = ""
             )
         }

     }*/

/*
    fun getShopCategories() {
        val current = mutableListOf<ShopCategoriesResponseItem>()
        viewModelScope.launch {
            dropyRepository.getAllShopCategories().collectLatest { item ->
                if (!item.isEmpty()) {
                    item.forEach { one ->
                        if (!current.contains(one)) {
                            current.add(one)
                        }
                        Log.d("FFTAG", "getShopCategories: $current")
                        _addShopUiState.update {
                            it.copy(
                                shopCategoryList = current
                            )
                        }
                    }

                }
            }
        }

    }*/

    fun onShopPhoneOneChanged(shopPhoneOne: String) {
        _addWaterTruckUiState.update {
            it.copy(
                licensePlate = shopPhoneOne
            )
        }
    }

    fun onShopPhoneTwoChanged(shopPhoneTwo: String) {
        _addWaterTruckUiState.update {
            it.copy(
                shopPhoneTwo = shopPhoneTwo
            )
        }
    }

    fun setTruckCapacity(shopPhoneTwo: String) {
        _addWaterTruckUiState.update {
            it.copy(
                selectedTruckCapacity = shopPhoneTwo
            )
        }
    }

    fun onModelChanged(shopPhoneTwo: String) {
        _addWaterTruckUiState.update {
            it.copy(
                model = shopPhoneTwo
            )
        }
    }

    fun onYearChanged(shopPhoneTwo: String) {
        _addWaterTruckUiState.update {
            it.copy(
                year = shopPhoneTwo
            )
        }
    }

    /* fun changeSaturdayState(state: Boolean) {
         _addWaterTruckUiState.update {
             it.copy(
                 saturdayState = state
             )
         }
     }

     fun changeSundayState(state: Boolean) {
         _addWaterTruckUiState.update {
             it.copy(
                 sundayState = state
             )
         }
     }

     fun changeOperationHrState(state: Boolean) {
         _addWaterTruckUiState.update {
             it.copy(
                 operationHrState = state
             )
         }
     }

     fun changeHolidayState(state: Boolean) {
         _addWaterTruckUiState.update {
             it.copy(
                 holidayState = state
             )
         }
     }

     fun changeHolidayOpeningTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 holiday_opening_time = time
             )
         }
     }

     fun changeHolidayClosingTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 holiday_closing_time = time
             )
         }
     }

     fun changeSaturdayOpeningTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 saturday_opening_time = time
             )
         }
     }

     fun changeSaturdayClosingTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 saturday_closing_time = time
             )
         }
     }

     fun changeSundayOpeningTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 sunday_opening_time = time
             )
         }
     }

     fun changeSundayClosingTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 sunday_closing_time = time
             )
         }
     }

     fun changeWeekdayOpeningTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 weekday_opening_time = time
             )
         }
     }

     fun changeWeekdayClosingTime(time: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 weekday_closing_time = time
             )
         }
     }

     fun changeShopType(type: String) {
         _addWaterTruckUiState.update {
             it.copy(
                 shopType = type
             )
         }
     }

     fun onCategorySelected(category: ShopCategoriesResponseItem, index: Int) {
         Log.d("TDDAG", "AddShopDetailsContent: $category  $index")

         _addWaterTruckUiState.update {
             it.copy(
                 shopCategory = category.category_name!!
             )
         }
     }
 */
    fun onGoToShopLocations(context: Context) {
        if (!_addWaterTruckUiState.value.licensePlate.equals(
                ""
            )
        ) {
            appViewModel?.navigate(AppDestinations.WATERTRUCK_LOCALE)
        } else {
            Toast.makeText(context, "fill all required values", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGoToShopUploads() {
        appViewModel?.navigate(AppDestinations.WATERTRUCK_IMAGES)
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        _addWaterTruckLocationUiState.update {
            it.copy(
                shopAddress = addressDataClass
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


            _addWaterTruckImagesUiState.update {
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

            _addWaterTruckImagesUiState.update {
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

    fun addWaterTruck(context: Context) {
        viewModelScope.launch {
            addWaterTruckUseCase(
                capacity = if (addWaterTruckUiState.value.selectedTruckCapacity.equals("5,000LT")) 5000 else 10000,
                context = context,
                current_latitude = addWaterTruckLocationUiState.value.shopAddress?.latitude.toString(),
                current_longitude = addWaterTruckLocationUiState.value.shopAddress?.longitude.toString(),
                registered_latitude = addWaterTruckLocationUiState.value.shopAddress?.latitude.toString(),
                registered_longitude = addWaterTruckLocationUiState.value.shopAddress?.longitude.toString(),
                current_location = addWaterTruckLocationUiState.value.shopAddress?.placeName.toString(),
                image = addWaterTruckImagesUiState.value.shopCoverPhotoUri,
                is_active = true,
                is_available = true,
                license_plate = addWaterTruckUiState.value.licensePlate,
                model = addWaterTruckUiState.value.model,
                year = addWaterTruckUiState.value.year.toInt(),
                name = "n/a",
                shop_cover_photo = addWaterTruckImagesUiState.value.shopCoverPhotoUri,
                vendor = appViewModel!!.appUiState.value.activeProfile?.id.toString(),
                token = app.token.value
            ).flowOn(Dispatchers.IO)
                /*    .catch { e ->
                        // handle exception
                        _addWaterTruckImagesUiState.update { it.copy(pageLoading = false) }

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
                                appViewModel!!.getWaterTrucks()
                                _addWaterTruckImagesUiState.update { it.copy(pageLoading = false) }
                                Toast.makeText(
                                    context,
                                    "${result.data.license_plate} created success",
                                    Toast.LENGTH_SHORT
                                ).show()
                                appViewModel!!.navigate(AppDestinations.WATER_VENDOR_DASHBOARD)
                               /* addWaterDriver(
                                    context = context,
                                    truck = result.data.id,
                                    addWaterTruckRes = result.data
                                )*/
                            }
//                            _addShopImagesUiState.update { it.copy(pageLoading = false) }


                        }
                        is Resource.Loading -> {
                            _addWaterTruckImagesUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
//                            result.message?.let { message ->
                            _addWaterTruckImagesUiState.update {
                                it.copy(
                                    pageLoading = false,
                                    errorList = listOf()
                                )
                            }
//                            }

                        }
                    }

                }
        }
    }


}