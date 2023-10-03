package com.example.dropy.ui.screens.addWaterVendor

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
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.network.use_case.addWaterVendor.AddWaterVendorUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.addWaterPoint.WaterpointUploadsUiState
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckImagesUiState
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckLocationUiState
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckUiState
import com.example.dropy.ui.screens.waterVendorDash.WaterVendorDashViewModel
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject


data class AddWaterVendorUiState(
    val name: String = "",
    val description: String = "",
    val branchManager: String = "",
    val branchManagerState: Boolean = false,
    val operationHrState: Boolean = false,
    val saturdayState: Boolean = false,
    val sundayState: Boolean = false,
    val holidayState: Boolean = false,
    val branchManagers: List</*String*/Int> = listOf(),
    val shopLocation: AddressDataClass? = null,
    val saturday_closing_time: String = "",
    val saturday_opening_time: String = "",
    val holiday_opening_time: String = "",
    val holiday_closing_time: String = "",
    val weekday_opening_time: String = "",
    val weekday_closing_time: String = "",
    val sunday_closing_time: String = "",
    val sunday_opening_time: String = "",
    val shopPhoneOne: String = "",
    val shopPhoneTwo: String = "",
    val shopCategory: String = "",
    val shopType: String = "",
    val shopCategoryList: List<ShopCategoriesResponseItem> = listOf(
        ShopCategoriesResponseItem(
            category_name = "FOOD & SNACKS",
            category_icon_url = "kk",
            id = 0
        ),
        ShopCategoriesResponseItem(
            category_name = "MAMA MBOGA",
            category_icon_url = "kk",
            id = 0
        ),
        ShopCategoriesResponseItem(
            category_name = "CHEMIST",
            category_icon_url = "kk",
            id = 0
        ),
        ShopCategoriesResponseItem(
            category_name = "GAS",
            category_icon_url = "kk",
            id = 0
        ),
        ShopCategoriesResponseItem(
            category_name = "BAKERY",
            category_icon_url = "kk",
            id = 0
        ),
        ShopCategoriesResponseItem(
            category_name = "FAST FOOD",
            category_icon_url = "kk",
            id = 0
        )

    ),
    val shopTypeList: List<String> = listOf("Single", "Multiple"),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class AddWaterVendorLocationUiState(
    val shopAddress: AddressDataClass? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class WaterVendorUploadsUiState(
    val shopLogo: ImageBitmap? = null,
    val shopCoverPhoto: ImageBitmap? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class AddWaterVendorImagesUiState(
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
class AddWaterVendorViewModel @Inject constructor(
    private val app: DropyApp,
    private val addWaterVendorUseCase: AddWaterVendorUseCase
): ViewModel() {
    val _addWaterVendorUiState = MutableStateFlow(AddWaterVendorUiState())
    val addWaterVendorUiState: StateFlow<AddWaterVendorUiState> = _addWaterVendorUiState.asStateFlow()

    private val _addWaterVendorLocationUiState = MutableStateFlow(AddWaterVendorLocationUiState())
    val addWaterVendorLocationUiState: StateFlow<AddWaterVendorLocationUiState> =
        _addWaterVendorLocationUiState.asStateFlow()

    private val _addWaterVendorImagesUiState = MutableStateFlow(AddWaterVendorImagesUiState())
    val addWaterVendorImagesUiState: StateFlow<AddWaterVendorImagesUiState> = _addWaterVendorImagesUiState.asStateFlow()


    private val _watervendorUploadsUiState = MutableStateFlow(WaterVendorUploadsUiState())

    val watervendorUploadsUiState: StateFlow<WaterVendorUploadsUiState> = _watervendorUploadsUiState.asStateFlow()

    var appViewModel: AppViewModel? = null
    val shopLogo: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopLogoUri: MutableState<Uri?> = mutableStateOf(null)
    val shopCoverPhoto: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopCoverPhotoUri: MutableState<Uri?> = mutableStateOf(null)

    fun onShopNameChanged(shopName: String) {
        _addWaterVendorUiState.update {
            it.copy(
                name = shopName
            )
        }
    }
    fun onDescriptionChanged(shopName: String) {
        _addWaterVendorUiState.update {
            it.copy(
                description = shopName
            )
        }
    }

    fun onBranchManagerNameChanged(branchManagerName: String) {
        _addWaterVendorUiState.update {
            it.copy(
                branchManager = branchManagerName
            )
        }
    }

    fun changeTextFState(state: Boolean) {
        _addWaterVendorUiState.update {
            it.copy(
                branchManagerState = state
            )
        }
    }

    fun addBranchName(name: String) {
        val list = mutableListOf<Int>()
        if (_addWaterVendorUiState.value.branchManagers.isNotEmpty()) {
            _addWaterVendorUiState.value.branchManagers.forEach {
                list.add(it.toInt())
            }
        }
        list.add(name.toInt())

        _addWaterVendorUiState.update {
            it.copy(
                branchManagers = list,
                branchManager = ""
            )
        }

    }

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
        _addWaterVendorUiState.update {
            it.copy(
                shopPhoneOne = shopPhoneOne
            )
        }
    }

    fun onShopPhoneTwoChanged(shopPhoneTwo: String) {
        _addWaterVendorUiState.update {
            it.copy(
                shopPhoneTwo = shopPhoneTwo
            )
        }
    }

    fun changeSaturdayState(state: Boolean) {
        _addWaterVendorUiState.update {
            it.copy(
                saturdayState = state
            )
        }
    }

    fun changeSundayState(state: Boolean) {
        _addWaterVendorUiState.update {
            it.copy(
                sundayState = state
            )
        }
    }

    fun changeOperationHrState(state: Boolean) {
        _addWaterVendorUiState.update {
            it.copy(
                operationHrState = state
            )
        }
    }

    fun changeHolidayState(state: Boolean) {
        _addWaterVendorUiState.update {
            it.copy(
                holidayState = state
            )
        }
    }

    fun changeHolidayOpeningTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                holiday_opening_time = time
            )
        }
    }

    fun changeHolidayClosingTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                holiday_closing_time = time
            )
        }
    }

    fun changeSaturdayOpeningTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                saturday_opening_time = time
            )
        }
    }

    fun changeSaturdayClosingTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                saturday_closing_time = time
            )
        }
    }

    fun changeSundayOpeningTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                sunday_opening_time = time
            )
        }
    }

    fun changeSundayClosingTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                sunday_closing_time = time
            )
        }
    }

    fun changeWeekdayOpeningTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                weekday_opening_time = time
            )
        }
    }

    fun changeWeekdayClosingTime(time: String) {
        _addWaterVendorUiState.update {
            it.copy(
                weekday_closing_time = time
            )
        }
    }

    fun changeShopType(type: String) {
        _addWaterVendorUiState.update {
            it.copy(
                shopType = type
            )
        }
    }

    fun onCategorySelected(category: ShopCategoriesResponseItem, index: Int) {
        Log.d("TDDAG", "AddShopDetailsContent: $category  $index")

        _addWaterVendorUiState.update {
            it.copy(
                shopCategory = category.category_name!!
            )
        }
    }

    fun onGoToShopLocations(context: Context) {
        if (!_addWaterVendorUiState.value.name.equals("")  && !_addWaterVendorUiState.value.shopPhoneOne.equals(
                ""
            )
        ) {
            appViewModel?.navigate(AppDestinations.WATERVENDOR_LOCALE)
        } else {
            Toast.makeText(context, "fill all required values", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGoToShopUploads() {
        appViewModel?.navigate(AppDestinations.WATERVENDOR_IMAGES)
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        _addWaterVendorLocationUiState.update {
            it.copy(
                shopAddress = addressDataClass
            )
        }
    }
    fun setDeviceId(text: String) {
        app.setMyDeviceId(text)
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


            _addWaterVendorImagesUiState.update {
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

            _addWaterVendorImagesUiState.update {
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


    fun createWaterVendor(context: Context, waterVendorDashViewModel: WaterVendorDashViewModel){
        viewModelScope.launch {
            addWaterVendorUseCase(
                bank_account = "1445",
                city = addWaterVendorLocationUiState.value.shopAddress?.placeName.toString(),
                county = addWaterVendorLocationUiState.value.shopAddress?.region.toString(),
                latitude = addWaterVendorLocationUiState.value.shopAddress?.latitude.toString(),
                longitude = addWaterVendorLocationUiState.value.shopAddress?.longitude.toString(),
                description = "n/a",
                holidays_open = addWaterVendorUiState.value.holidayState,
                operating_all_day = addWaterVendorUiState.value.holidayState,
                saturday_open = addWaterVendorUiState.value.saturdayState,
                sunday_open = addWaterVendorUiState.value.sundayState,
                weekday_opening_time = addWaterVendorUiState.value.weekday_opening_time,
                weekday_closing_time = addWaterVendorUiState.value.weekday_closing_time,
                saturday_opening_time = addWaterVendorUiState.value.saturday_opening_time,
                saturday_closing_time = addWaterVendorUiState.value.saturday_closing_time,
                sunday_opening_time = addWaterVendorUiState.value.sunday_opening_time,
                sunday_closing_time = addWaterVendorUiState.value.sunday_closing_time,
                phone_number = addWaterVendorUiState.value.shopPhoneOne,
                is_active = true,
                logo = addWaterVendorImagesUiState.value.shopLogoUri,
                name = addWaterVendorUiState.value.name,
                mpesa_paybill = "140782",
                token = app.token.value,
                shop_cover_photo = addWaterVendorImagesUiState.value.shopCoverPhotoUri,
                context = context,
                street = addWaterVendorLocationUiState.value.shopAddress?.placeName.toString(),
                sub_county = addWaterVendorLocationUiState.value.shopAddress?.region.toString(),
                total_earnings = "120",
                owner = app.id.value
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    _addWaterVendorImagesUiState.update { it.copy(pageLoading = false) }

                }
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
                                val item = ActiveProfileDataClass(
                                    type = ProfileTypes.WATER_VENDOR,
                                    name = result.data.name,
                                    id = result.data.id
                                )

                                _addWaterVendorImagesUiState.update { it.copy(pageLoading = false) }
                                appViewModel!!.getWaterVendors()
                                Toast.makeText(context, "${result.data.name} created success", Toast.LENGTH_SHORT).show()
                                appViewModel?.onSelectProfile(item)
                                waterVendorDashViewModel.resetValues()
                            }
//                            _addShopImagesUiState.update { it.copy(pageLoading = false) }


                        }
                        is Resource.Loading -> {
                            _addWaterVendorImagesUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
//                            result.message?.let { message ->
                            _addWaterVendorImagesUiState.update {
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