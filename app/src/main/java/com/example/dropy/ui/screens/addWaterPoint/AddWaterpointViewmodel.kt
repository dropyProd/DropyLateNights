package com.example.dropy.ui.screens.addWaterPoint

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
import com.example.dropy.network.use_case.addWaterPoint.AddWaterPointUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopUiState
import com.example.dropy.ui.screens.shops.backside.addshop.ShopUploadsUiState
import com.example.dropy.ui.screens.shops.backside.createshop.addshopimages.AddShopImagesUiState
import com.example.dropy.ui.screens.shops.backside.createshop.addshoplocation.AddShopLocationUiState
import com.example.dropy.ui.screens.watepointDash.WaterPointDashViewModel
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

data class AddWaterpointUiState(
    val name: String = "",
    val branchManager: String = "",
    val pricePerLitre: String = "",
    val email: String = "",
    val mpesaPaybill: String = "",
    val bankAccount: String = "",
    val branchManagerState: Boolean = false,
    val operationHrState: Boolean = false,
    val saturdayState: Boolean = false,
    val sundayState: Boolean = false,
    val holidayState: Boolean = false,
    val branchManagers: List</*String*/Int> = listOf(),
    val location: AddressDataClass? = null,
    val saturday_closing_time: String = "",
    val saturday_opening_time: String = "",
    val holiday_opening_time: String = "",
    val holiday_closing_time: String = "",
    val weekday_opening_time: String = "",
    val weekday_closing_time: String = "",
    val sunday_closing_time: String = "",
    val sunday_opening_time: String = "",
    val phoneOne: String = "",
    val capacity: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class AddWaterpointLocationUiState(
    val waterpointAddress: AddressDataClass? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class WaterpointUploadsUiState(
    val shopLogo: ImageBitmap? = null,
    val shopCoverPhoto: ImageBitmap? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class AddWaterpointImagesUiState(
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
class AddWaterpointViewmodel @Inject constructor(
    private val app: DropyApp,
    private val addWaterPointUseCase: AddWaterPointUseCase
) : ViewModel() {

    val _addWaterpointUiState = MutableStateFlow(AddWaterpointUiState())
    val addWaterpointUiState: StateFlow<AddWaterpointUiState> = _addWaterpointUiState.asStateFlow()

    private val _addWaterpointLocationUiState = MutableStateFlow(AddWaterpointLocationUiState())
    val addWaterpointLocationUiState: StateFlow<AddWaterpointLocationUiState> =
        _addWaterpointLocationUiState.asStateFlow()

    private val _addWaterpointImagesUiState = MutableStateFlow(AddWaterpointImagesUiState())
    val addWaterpointImagesUiState: StateFlow<AddWaterpointImagesUiState> =
        _addWaterpointImagesUiState.asStateFlow()


    private val _waterpointUploadsUiState = MutableStateFlow(WaterpointUploadsUiState())

    val shopUploadsUiState: StateFlow<WaterpointUploadsUiState> =
        _waterpointUploadsUiState.asStateFlow()

    var appViewModel: AppViewModel? = null
    val shopLogo: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopLogoUri: MutableState<Uri?> = mutableStateOf(null)
    val shopCoverPhoto: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopCoverPhotoUri: MutableState<Uri?> = mutableStateOf(null)

    fun onShopNameChanged(shopName: String) {
        _addWaterpointUiState.update {
            it.copy(
                name = shopName
            )
        }
    }

    fun onBranchManagerNameChanged(branchManagerName: String) {
        _addWaterpointUiState.update {
            it.copy(
                branchManager = branchManagerName
            )
        }
    }

    fun changeTextFState(state: Boolean) {
        _addWaterpointUiState.update {
            it.copy(
                branchManagerState = state
            )
        }
    }

    fun addBranchName(name: String) {
        val list = mutableListOf<Int>()
        if (_addWaterpointUiState.value.branchManagers.isNotEmpty()) {
            _addWaterpointUiState.value.branchManagers.forEach {
                list.add(it.toInt())
            }
        }
        list.add(name.toInt())

        _addWaterpointUiState.update {
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
        _addWaterpointUiState.update {
            it.copy(
                phoneOne = shopPhoneOne
            )
        }
    }
    fun onPricePerLitreChanged(text: String) {
        _addWaterpointUiState.update {
            it.copy(
                pricePerLitre = text
            )
        }
    }
    fun onEmailChanged(text: String) {
        _addWaterpointUiState.update {
            it.copy(
                email = text
            )
        }
    }
    fun onMpesaPaybillChanged(text: String) {
        _addWaterpointUiState.update {
            it.copy(
                mpesaPaybill = text
            )
        }
    }
    fun onBankAccountChanged(text: String) {
        _addWaterpointUiState.update {
            it.copy(
                bankAccount = text
            )
        }
    }

    fun onShopPhoneTwoChanged(shopPhoneTwo: String) {
        _addWaterpointUiState.update {
            it.copy(
                capacity = shopPhoneTwo
            )
        }
    }

    fun changeSaturdayState(state: Boolean) {
        _addWaterpointUiState.update {
            it.copy(
                saturdayState = state
            )
        }
    }

    fun changeSundayState(state: Boolean) {
        _addWaterpointUiState.update {
            it.copy(
                sundayState = state
            )
        }
    }

    fun changeOperationHrState(state: Boolean) {
        _addWaterpointUiState.update {
            it.copy(
                operationHrState = state
            )
        }
    }

    fun changeHolidayState(state: Boolean) {
        _addWaterpointUiState.update {
            it.copy(
                holidayState = state
            )
        }
    }

    fun changeHolidayOpeningTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                holiday_opening_time = time
            )
        }
    }

    fun changeHolidayClosingTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                holiday_closing_time = time
            )
        }
    }

    fun changeSaturdayOpeningTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                saturday_opening_time = time
            )
        }
    }

    fun changeSaturdayClosingTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                saturday_closing_time = time
            )
        }
    }

    fun changeSundayOpeningTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                sunday_opening_time = time
            )
        }
    }

    fun changeSundayClosingTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                sunday_closing_time = time
            )
        }
    }

    fun changeWeekdayOpeningTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                weekday_opening_time = time
            )
        }
    }

    fun changeWeekdayClosingTime(time: String) {
        _addWaterpointUiState.update {
            it.copy(
                weekday_closing_time = time
            )
        }
    }

    fun changeShopType(type: String) {
        /*    _addWaterpointUiState.update {
                it.copy(
                    shopType = type
                )
            }*/
    }

    fun onCategorySelected(category: ShopCategoriesResponseItem, index: Int) {
        Log.d("TDDAG", "AddShopDetailsContent: $category  $index")

        /*_addWaterpointUiState.update {
            it.copy(
                shopCategory = category.category_name!!
            )
        }*/
    }

    fun onGoToShopLocations(context: Context) {
        if (!_addWaterpointUiState.value.name.equals("") && !_addWaterpointUiState.value.phoneOne.equals(
                ""
            )
        ) {
            appViewModel?.navigate(AppDestinations.WATERPOINT_LOCATION)
        } else {
            Toast.makeText(context, "fill all required values", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGoToShopUploads() {
        appViewModel?.navigate(AppDestinations.WATERPOINT_IMAGES)
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        _addWaterpointLocationUiState.update {
            it.copy(
                waterpointAddress = addressDataClass
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


            _addWaterpointImagesUiState.update {
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

            _addWaterpointImagesUiState.update {
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


    fun addWaterPoint(context: Context, waterPointDashViewModel: WaterPointDashViewModel) {
        viewModelScope.launch {
            addWaterPointUseCase(
                bank_account = addWaterpointUiState.value.bankAccount, capacity = _addWaterpointUiState.value.capacity.toInt(),
                city = addWaterpointLocationUiState.value.waterpointAddress?.placeName.toString(),
                county = addWaterpointLocationUiState.value.waterpointAddress?.region.toString(),
                latitude = addWaterpointLocationUiState.value.waterpointAddress?.latitude.toString(),
                longitude = addWaterpointLocationUiState.value.waterpointAddress?.longitude.toString(),
                description = addWaterpointUiState.value.name.toString(),
                email = addWaterpointUiState.value.email,
                holidays_open = if (addWaterpointUiState.value.operationHrState) true else addWaterpointUiState.value.holidayState,
                operating_all_day = if (addWaterpointUiState.value.operationHrState) true else addWaterpointUiState.value.operationHrState,
                saturday_open = if (addWaterpointUiState.value.operationHrState) true else addWaterpointUiState.value.saturdayState,
                sunday_open = if (addWaterpointUiState.value.operationHrState) true else addWaterpointUiState.value.sundayState,
                weekday_opening_time =if (addWaterpointUiState.value.operationHrState) "00:00:00" else addWaterpointUiState.value.weekday_opening_time,
                weekday_closing_time = if (addWaterpointUiState.value.operationHrState) "00:00:00" else addWaterpointUiState.value.weekday_closing_time,
                saturday_opening_time = if (addWaterpointUiState.value.operationHrState) "00:00:00" else addWaterpointUiState.value.saturday_opening_time,
                saturday_closing_time = if (addWaterpointUiState.value.operationHrState) "00:00:00" else addWaterpointUiState.value.saturday_closing_time,
                sunday_opening_time = if (addWaterpointUiState.value.operationHrState) "00:00:00" else addWaterpointUiState.value.sunday_opening_time,
                sunday_closing_time = if (addWaterpointUiState.value.operationHrState) "00:00:00" else addWaterpointUiState.value.sunday_closing_time,
                phone_number = addWaterpointUiState.value.phoneOne,
                price_per_litre = addWaterpointUiState.value.pricePerLitre,
                is_active = true,
                logo = addWaterpointImagesUiState.value.shopLogoUri,
                name = addWaterpointUiState.value.name,
                mpesa_paybill = addWaterpointUiState.value.mpesaPaybill,
                owner = app.id.value,
                token = app.token.value,
                shop_cover_photo = addWaterpointImagesUiState.value.shopCoverPhotoUri,
                context = context,
                street = addWaterpointLocationUiState.value.waterpointAddress?.placeName.toString(),
                sub_county = addWaterpointLocationUiState.value.waterpointAddress?.region.toString(),
                total_earnings = "0",
            ).flowOn(Dispatchers.IO)
          /*      .catch { e ->
                    // handle exception
                    _addWaterpointImagesUiState.update { it.copy(pageLoading = false) }

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
                                val item = ActiveProfileDataClass(
                                    type = ProfileTypes.WATER_POINT,
                                    name = result.data.name,
                                    id = result.data.id
                                )
                                waterPointDashViewModel.setWaterPoint(result.data)

                                _addWaterpointImagesUiState.update { it.copy(pageLoading = false) }
                                Toast.makeText(context, "${result.data.name} created success", Toast.LENGTH_SHORT).show()
                                appViewModel?.getWaterpoints()
                                appViewModel?.onSelectProfile(item)
                            }
//                            _addShopImagesUiState.update { it.copy(pageLoading = false) }


                        }
                        is Resource.Loading -> {
                            _addWaterpointImagesUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
//                            result.message?.let { message ->
                            _addWaterpointImagesUiState.update {
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