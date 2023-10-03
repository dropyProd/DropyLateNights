package com.example.dropy.ui.screens.shops.backside.addshop

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
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.network.repositories.shop.ShopRepository
import com.example.dropy.network.repositories.shop.ShopRepositoryImpl
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.use_case.shops.backside.AddShopUseCase
import com.example.dropy.network.use_case.shops.frontside.GetAllShopCategoriesUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.createshop.addshopimages.AddShopImagesUiState
import com.example.dropy.ui.screens.shops.backside.createshop.addshoplocation.AddShopLocationUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject


data class AddShopUiState(
    val shopName: String = "",
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

data class AddShopLocationUiState(
    val shopAddress: com.example.dropy.ui.components.commons.maps.AddressDataClass? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class ShopUploadsUiState(
    val shopLogo: ImageBitmap? = null,
    val shopCoverPhoto: ImageBitmap? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class AddShopImagesUiState(
    val shopLogo: ImageBitmap? = null,
    val shopCoverPhoto: ImageBitmap? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class AddShopViewModel @Inject constructor(
//    private val dropyRepository: DropyRepository
    private val shopBackendRepository: ShopBackendRepository,
    private val addShopUseCase: AddShopUseCase,
    private val getAllShopCategoriesUseCase: GetAllShopCategoriesUseCase,
    private val app: DropyApp
) : ViewModel() {

    val _addShopUiState = MutableStateFlow(AddShopUiState())
    val addShopUiState: StateFlow<AddShopUiState> = _addShopUiState.asStateFlow()

    private val _addShopLocationUiState = MutableStateFlow(AddShopLocationUiState())
    val addShopLocationUiState: StateFlow<AddShopLocationUiState> =
        _addShopLocationUiState.asStateFlow()

    private val _addShopImagesUiState = MutableStateFlow(AddShopImagesUiState())
    val addShopImagesUiState: StateFlow<AddShopImagesUiState> = _addShopImagesUiState.asStateFlow()


    private val _shopUploadsUiState = MutableStateFlow(ShopUploadsUiState())

    val shopUploadsUiState: StateFlow<ShopUploadsUiState> = _shopUploadsUiState.asStateFlow()

    var appViewModel: AppViewModel? = null
    val shopLogo: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopLogoUri: MutableState<Uri?> = mutableStateOf(null)
    val shopCoverPhoto: MutableState<ImageBitmap?> = mutableStateOf(null)
    val shopCoverPhotoUri: MutableState<Uri?> = mutableStateOf(null)

    val showProductCategoryDialog = mutableStateOf(false)
    val showOptionsDialog = mutableStateOf(false)

    fun dismissDialog() {
        showProductCategoryDialog.value = false
        // appViewModel?.navigate(ShopsBacksideNavigation.ADD_PRODUCT)
    }

    fun showDialog() {
        showProductCategoryDialog.value = true
    }

    fun dismissOptionsDialog() {
        showOptionsDialog.value = false
        // appViewModel?.navigate(ShopsBacksideNavigation.ADD_PRODUCT)
    }

    fun showoptionsDialog() {
        showOptionsDialog.value = true
    }

    fun onShopNameChanged(shopName: String) {
        _addShopUiState.update {
            it.copy(
                shopName = shopName
            )
        }
    }

    fun onBranchManagerNameChanged(branchManagerName: String) {
        _addShopUiState.update {
            it.copy(
                branchManager = branchManagerName
            )
        }
    }

    fun changeTextFState(state: Boolean) {
        _addShopUiState.update {
            it.copy(
                branchManagerState = state
            )
        }
    }

    fun addBranchName(name: String) {
        val list = mutableListOf<Int>()
        if (_addShopUiState.value.branchManagers.isNotEmpty()) {
            _addShopUiState.value.branchManagers.forEach {
                list.add(it.toInt())
            }
        }
        list.add(name.toInt())

        _addShopUiState.update {
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
        _addShopUiState.update {
            it.copy(
                shopPhoneOne = shopPhoneOne
            )
        }
    }

    fun onShopPhoneTwoChanged(shopPhoneTwo: String) {
        _addShopUiState.update {
            it.copy(
                shopPhoneTwo = shopPhoneTwo
            )
        }
    }

    fun changeSaturdayState(state: Boolean) {
        _addShopUiState.update {
            it.copy(
                saturdayState = state
            )
        }
    }

    fun changeSundayState(state: Boolean) {
        _addShopUiState.update {
            it.copy(
                sundayState = state
            )
        }
    }

    fun changeOperationHrState(state: Boolean) {
        _addShopUiState.update {
            it.copy(
                operationHrState = state
            )
        }
    }

    fun changeHolidayState(state: Boolean) {
        _addShopUiState.update {
            it.copy(
                holidayState = state
            )
        }
    }

    fun changeHolidayOpeningTime(time: String) {
        _addShopUiState.update {
            it.copy(
                holiday_opening_time = time
            )
        }
    }

    fun changeHolidayClosingTime(time: String) {
        _addShopUiState.update {
            it.copy(
                holiday_closing_time = time
            )
        }
    }

    fun changeSaturdayOpeningTime(time: String) {
        _addShopUiState.update {
            it.copy(
                saturday_opening_time = time
            )
        }
    }

    fun changeSaturdayClosingTime(time: String) {
        _addShopUiState.update {
            it.copy(
                saturday_closing_time = time
            )
        }
    }

    fun changeSundayOpeningTime(time: String) {
        _addShopUiState.update {
            it.copy(
                sunday_opening_time = time
            )
        }
    }

    fun changeSundayClosingTime(time: String) {
        _addShopUiState.update {
            it.copy(
                sunday_closing_time = time
            )
        }
    }

    fun changeWeekdayOpeningTime(time: String) {
        _addShopUiState.update {
            it.copy(
                weekday_opening_time = time
            )
        }
    }

    fun changeWeekdayClosingTime(time: String) {
        _addShopUiState.update {
            it.copy(
                weekday_closing_time = time
            )
        }
    }

    fun changeShopType(type: String) {
        _addShopUiState.update {
            it.copy(
                shopType = type
            )
        }
    }

    fun onCategorySelected(category: ShopCategoriesResponseItem, index: Int) {
        Log.d("TDDAG", "AddShopDetailsContent: $category  $index")

        _addShopUiState.update {
            it.copy(
                shopCategory = category.category_name!!
            )
        }
    }

    fun onGoToShopLocations(context: Context) {
        if (!addShopUiState.value.shopName.equals("") && addShopUiState.value.shopCategory.equals("") && !addShopUiState.value.shopPhoneOne.equals(
                ""
            )
        ) {
            appViewModel?.navigate(ShopsBacksideNavigation.ADD_SHOP_LOCATION)
        } else {
            Toast.makeText(context, "fill all required values", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGoToShopUploads() {
        appViewModel?.navigate(ShopsBacksideNavigation.SHOP_UPLOADS)
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        _addShopLocationUiState.update {
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


            _addShopImagesUiState.update {
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

            _addShopImagesUiState.update {
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

    fun getShopCategories(
        appHomePageViewModel: AppHomePageViewModel,
    ) {

        viewModelScope.launch {

            getAllShopCategoriesUseCase.shopCategories(app.token.value).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception

                }
                .collect { result ->
                    Log.d("uopopi", "ght: $result")
                    when (result) {
                        is Resource.Success -> {
                            val current = mutableListOf<ShopCategoriesResponseItem>()

                            if (result.data != null) {
                                if (!result.data.isEmpty()) {
                                    result.data.forEach { one ->
                                        if (!current.contains(one)) {
                                            current.add(one)
                                        }
                                        /*     _uiState.update {
                                                 it.copy(
                                                     shopCategoryList = current
                                                 )
                                             }*/
                                        Log.d("FFTAG", "getShopCategories: $current")
                                        _addShopUiState.update {
                                            it.copy(
                                                shopCategoryList = current
                                            )
                                        }

                                    }
                                    appHomePageViewModel.uiState.update {
                                        it.copy(pageLoading = false)
                                    }
                                }
                            }
                        }
                        is Resource.Loading -> {
                            appHomePageViewModel.uiState.update {
                                it.copy(pageLoading = true)
                            }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                appHomePageViewModel.uiState.update {
                                    it.copy(pageLoading = false, errorList = listOf(message))
                                }
                            }

                        }
                    }

                }
            /*    val res = shopFrontendRepository.getAllShopCategories()
    */

        }

    }

    fun onAddShop(context: Context) {
        Log.d(
            "TDDiuAG",
            "onAddShop: ${addShopImagesUiState.value.shopCoverPhotoUri}  ${addShopImagesUiState.value.shopLogoUri}  ${addShopUiState.value.shopCategory}    ${appViewModel?.appUiState?.value?.firebaseUid.toString()}"
        )
        viewModelScope.launch {

            var id = 0
            //_addShopImagesUiState.update { it.copy(pageLoading = true) }

            _addShopUiState.value.shopCategoryList.forEach {
                if (it.category_name.equals(addShopUiState.value.shopCategory)) id = it.id!!
            }

            val model = createShopReq(
                shop_name = addShopUiState.value.shopName,
                holidays_open = addShopUiState.value.holidayState,
                saturday_open = addShopUiState.value.saturdayState,
                sunday_open = addShopUiState.value.sundayState,
                managers = addShopUiState.value.branchManagers,
                operating_all_day = addShopUiState.value.operationHrState,
                phone_number = addShopUiState.value.shopPhoneOne,
                phone_number_two = addShopUiState.value.shopPhoneTwo,
                saturday_closing_time = addShopUiState.value.saturday_closing_time,
                saturday_opening_time = addShopUiState.value.saturday_opening_time,
                weekday_opening_time = addShopUiState.value.weekday_opening_time,
                weekday_closing_time = addShopUiState.value.weekday_closing_time,
                sunday_opening_time = addShopUiState.value.sunday_opening_time,
                sunday_closing_time = addShopUiState.value.sunday_closing_time,
                shop_category = 0,
                shop_location = 1245,
                shop_owner = app.id.value,
                shop_type = addShopUiState.value.shopType
            )


            //addShopLocationUiState.value.shopAddress?.let { it2 ->
            addShopUseCase.addShop(
                context = context,
                shopname = addShopUiState.value.shopName,
                shopLocation = /*it2*/null,
                shopPhoneOne = addShopUiState.value.shopPhoneOne,
                firebase_uid = appViewModel?.appUiState?.value?.firebaseUid.toString(),
                shop_cover_photo = addShopImagesUiState.value.shopCoverPhotoUri,
                shop_logo = addShopImagesUiState.value.shopLogoUri,
                shopCategory = id,
                createShopReq = model,
                token = app.token.value,
                shopOwner = app.id.value
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("KKTAG", "onAddShop: $result")
                            if (result.data != null) {
                                //  if (result.data?.resultCode?.equals(0) == true) {
                                _addShopImagesUiState.update { it.copy(pageLoading = false) }
                                moveAddProductCategory()
                                // }
                            }
                            _addShopImagesUiState.update { it.copy(pageLoading = false) }


                        }
                        is Resource.Loading -> {
                            _addShopImagesUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                _addShopImagesUiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }
            //    }


        }
    }

    fun moveAddProductCategory() {
        showDialog()
    }
}