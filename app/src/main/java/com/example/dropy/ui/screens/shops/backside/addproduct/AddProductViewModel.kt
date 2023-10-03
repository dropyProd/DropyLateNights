package com.example.dropy.ui.screens.shops.backside.addproduct

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.getshopproductcategories.ProductCategory
import com.example.dropy.network.models.productCategoryReq
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.network.repositories.shop.ShopRepository
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.use_case.shops.backside.*
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.utils.Resource
import com.example.dropy.ui.utils.compressimages.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlin.math.min

data class AddProductUiState(
    val productName: String = "",
    val productImageUrl: String? = null,
    val productId: Int = 0,
    val productDescription: String = "",
    val productCategory: String = "",
    val productCategoryId: Int = 0,
    val productCategories: List<ProductCategory> = emptyList(),
    val productPrice: String = "",
    val productSize: String = "",
    val productUnit: String = "",
    val discountPrice: String = "",
    val discountPercentage: String = "",
    val couponCode: String = "",
    val numberInStack: String = "",
    val coverPhotoBitmap: Bitmap? = null,
    val coverPhotoUri: Uri? = null,
    val multiplePhotoBitmap: List<Bitmap?>? = null,
    val multiplePhotoUri: List<Uri?> = listOf(),
    val measurementUnit: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList(),
    val offerDiscount: Boolean = false,
)

@HiltViewModel
class AddProductViewModel @Inject constructor(

    private val shopBackendRepository: ShopBackendRepository,
    private val getShopProductCategoriesUseCase: GetShopProductCategoriesUseCase,
    private val addShopProductCategoryUseCase: AddShopProductCategoryUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val deleteProductCategoryUseCase: DeleteProductCategoryUseCase,
    private val app: DropyApp
) : ViewModel() {

    private val uiState = MutableStateFlow(AddProductUiState())

    val addProductUiState: StateFlow<AddProductUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    val productcategoryname = mutableStateOf("")
    val id: MutableState<String?> = mutableStateOf(null)

    val fetch = mutableStateOf(true)

    fun onProductcategorynameChanged(name: String) {
        productcategoryname.value = name
    }

    val move = mutableStateOf(0)


    val currentProductCategories: MutableList<ProductCategory> = mutableListOf()
    suspend fun getShopProductCategories() {
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }

            id.value?.let {
                getShopProductCategoriesUseCase.getShopProductCategories(app.token.value, it)
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                        uiState.update { it.copy(pageLoading = false) }
                    }
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: $result")
                        when (result) {
                            is Resource.Success -> {
                                if (result.data != null) {
                                    currentProductCategories.clear()

                                    result.data.product_categories?.forEachIndexed { iindex, shopProductCategoriesResponseItem ->
                                        addProductCategories(
                                            shopProductCategoriesResponseItem,
                                            index = iindex
                                        )
                                    }
                                    uiState.update { it.copy(pageLoading = false) }
                                    if (!uiState.value.productCategories.isEmpty()) {
                                        if (refresh.value) onProductCategorySelected(
                                            uiState.value.productCategories[0],
                                            0
                                        )
                                    }

                                }
                            }
                            is Resource.Loading -> {
                                uiState.update { it.copy(pageLoading = true) }
                            }
                            is Resource.Error -> {
                                result.message?.let { message ->
                                    uiState.update {
                                        it.copy(
                                            pageLoading = false,
                                            errorList = listOf(message)
                                        )
                                    }
                                }

                            }
                        }

                    }


            }
        }
    }

    //val num = 0

    fun setId(num: String) {
        id.value = num
    }

    fun addProductCategory(currentList: MutableList<ShopsResponseNewItem>, name: String) {
        Log.d("XCDDF", "addProductCategory: $currentList  $name")

        currentList.forEach {
            if (it.shop_name.equals(name)) {
                it.id?.let { it1 -> setId(it1) }
                Log.d("TAGDDD", "addProductCategory: ${id.value}")
            } else Log.d("TAGDDD", "addProductCategory: Not found")
        }
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }

//            id.value?.let {
                addShopProductCategoryUseCase(
                    productCategoryReq = productCategoryReq(
                        category_name = "",
                        shop_category = 0,
                        shop = ""
                    ),
                    token = "Token " + app.token.value
                ).flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                        uiState.update { it.copy(pageLoading = false) }
                    }
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: $result")
                        when (result) {
                            is Resource.Success -> {
                                if (result.data != null) {
                                    Log.d("sddqTAG", "addProductCategory: $result")
                                    if (result.data.resultCode.equals(0)) productcategoryname.value =
                                        ""
                                    uiState.update { it.copy(pageLoading = false) }


                                }
                            }
                            is Resource.Loading -> {
                                uiState.update { it.copy(pageLoading = true) }
                            }
                            is Resource.Error -> {
                                result.message?.let { message ->
                                    uiState.update {
                                        it.copy(
                                            pageLoading = false,
                                            errorList = listOf(message)
                                        )
                                    }
                                }

                            }
                        }

                    }
//            }


        }

    }

    fun deleteProductCategory(categoryId: Int) {
        viewModelScope.launch {
            deleteProductCategoryUseCase.deleteProductCategory(app.token.value, categoryId)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                if (result.data.resultCode.equals(0)) {
                                    appViewModel?.navigate(ShopsBacksideNavigation.SHOP_PRODUCTS)
                                }
                                uiState.update { it.copy(pageLoading = false) }
                            }
                            uiState.update { it.copy(pageLoading = false) }

                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

        }

    }

    fun setNumberStock(numberInStack: String) {
        uiState.update {
            it.copy(
                numberInStack = numberInStack
            )
        }
    }

    fun setDiscountPrice(discountPrice: String) {
        uiState.update {
            it.copy(
                discountPrice = discountPrice
            )
        }
    }

    fun setDiscountPercentage(discountPercentage: String) {
        uiState.update {
            it.copy(
                discountPercentage = discountPercentage
            )
        }
    }

    fun setCouponCode(couponCode: String) {
        uiState.update {
            it.copy(
                couponCode = couponCode
            )
        }
    }


    fun deleteProduct() {
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }
            deleteProductUseCase.deleteProduct(app.token.value, uiState.value.productId)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                if (result.data.resultCode.equals(0)) {
                                    appViewModel?.navigate(ShopsBacksideNavigation.SHOP_PRODUCTS)
                                }
                                uiState.update { it.copy(pageLoading = false) }
                            }
                            uiState.update { it.copy(pageLoading = false) }

                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

        }
    }

    fun setValues(item: ProductDetailsResponse, shopHomePageViewModel: ShopHomePageViewModel) {
        Log.d("gstef", "setValues:$item ")
        val imageUrl = mutableStateOf("")
        shopHomePageViewModel.uiState.value.shopProducts.forEach {
            if (it.product_name.equals(item.product_name)) {
                Log.d("jijij", "setValues: $it")
                imageUrl.value = it.product_cover_photo.toString()
            }
        }
        uiState.update {
            it.copy(
                productId = item.id!!,
                productName = item.product_name.toString(),
                productPrice = item.product_price.toString(),
                productDescription = item.product_description.toString(),
                productCategoryId = item.id!!,
                productCategory = item.product_category!!.category_name.toString(),
                productImageUrl = "httpw://api.dropy.co.ke" + imageUrl.value
            )
        }
    }


    fun addProductCategories(item: ProductCategory, index: Int) {
        Log.d("PPPTAG", "addProductCategories: $item")
        currentProductCategories.add(item)

        uiState.update {
            it.copy(
                productCategories = currentProductCategories
            )
        }
    }

    fun onProductNameChanged(productName: String) {
        uiState.update {
            it.copy(
                productName = productName
            )
        }
    }

    val refresh = mutableStateOf(true)

    fun onAddCoverPhoto(bitmap: Bitmap, uri: Uri, context: Context) {

        viewModelScope.launch {
            val file = FileUtil.from(context, uri)
            val compressduri = Compressor.compress(context, file) {
                resolution(1280, 720)
                quality(40)
                format(Bitmap.CompressFormat.WEBP)
                size(7_152) // 2 MB
            }

            val bitmapreduced = ResizeBitmap.getResizedBitmap(bitmap, 20)

            val bitmapp = BitmapFactory.Options().run {
                inJustDecodeBounds = true
                BitmapFactory.decodeFile(file.absolutePath, this)
                val sampleHeight = if (outWidth > outHeight) 900 else 1100
                val sampleWidth = if (outWidth > outHeight) 1100 else 900
                inSampleSize =
                    min(outWidth / sampleWidth, outHeight / sampleHeight)
                inJustDecodeBounds = false
                BitmapFactory.decodeFile(file.absolutePath, this)
            }
// Now this bitmap is at it's optimum level of low memory.
// Let us write this into a file.
            //bitmap.compress(Bitmap.CompressFormat.JPEG, 90, FileOutputStream(file))


            uiState.update {
                it.copy(
                    coverPhotoBitmap = bitmap,
                    coverPhotoUri = uri
                )
            }
            refresh.value = false
        }
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap? {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    // Method to compress a bitmap
    private fun compressBitmap(bitmap: Bitmap, quality: Int): Bitmap {
        // Initialize a new ByteArrayStream
        val stream = ByteArrayOutputStream()

        // Compress the bitmap with JPEG format and specified quality
        bitmap.compress(
            Bitmap.CompressFormat.JPEG, quality, stream
        )

        val byteArray = stream.toByteArray()

        // Finally, return the compressed bitmap
        return BitmapFactory.decodeByteArray(
            byteArray, 0, byteArray.size
        )
    }

    //making it  a suspend function to wait for results
    suspend fun compressUri() {
        viewModelScope.launch {

            //  compress images here


        }
    }

    fun onAddMultiplePhoto(bitmap: List<Bitmap>, uri: List<Uri>, context: Context) {
        viewModelScope.launch {

            /*  val current: MutableList<Uri> = mutableListOf()

              uri.forEach {
                  val file = FileUtil.from(context, it)
                  Compressor.compress(context, file) {
                      resolution(1280, 720)
                      quality(40)
                      format(Bitmap.CompressFormat.WEBP)
                      size(2_097_152) // 2 MB
                  }

                  current.add(
                      FileUtil.getImageUri(
                          context,
                          BitmapFactory.decodeFile(file!!.absolutePath)
                      )
                  )
              }*/


            uiState.update {
                it.copy(
                    multiplePhotoBitmap = bitmap,
                    multiplePhotoUri = uri
                )
            }
            refresh.value = false

        }
    }

    fun onProductDescriptionChanged(productDescription: String) {
        uiState.update {
            it.copy(
                productDescription = productDescription
            )
        }
    }

    fun onProductCategorySelected(productCategory: ProductCategory, index: Int) {
        uiState.update {
            it.copy(
                productCategory = productCategory.category_name!!,
                productCategoryId = productCategory.id!!
            )
        }

    }

    fun onProductPriceChanged(productPrice: String) {

        uiState.update {
            it.copy(
                productPrice = productPrice
            )
        }

    }

    fun onProductUnitsChanged(productUnit: String) {
        uiState.update {
            it.copy(
                measurementUnit = productUnit
            )
        }
    }

    fun onAddProductPhotos() {

    }

    fun onNumberInStackChanged(productNumberInStack: String) {

        uiState.update {
            it.copy(
                numberInStack = productNumberInStack
            )
        }

    }


    fun onAddProduct(
        context: Context,
        currentList: MutableList<ShopsResponseNewItem>,
        name: String,
        type: String,
        shopHomePageViewModel: ShopHomePageViewModel,
        appHomePageViewModel: AppHomePageViewModel,
        addShopUiState: AddShopUiState,
        clearImages: () -> Unit
    ) {
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }
            if (!type.equals("editproduct")) {
                if (uiState.value.coverPhotoUri != null) {
                    add(
                        context,
                        currentList,
                        name,
                        type,
                        shopHomePageViewModel,
                        appHomePageViewModel,
                        addShopUiState,
                        clearImages
                    )
                } else {
                    uiState.update { it.copy(pageLoading = false) }
                    Toast.makeText(context, "product images required", Toast.LENGTH_SHORT).show()
                }
            } else {
                add(
                    context,
                    currentList,
                    name,
                    type,
                    shopHomePageViewModel,
                    appHomePageViewModel,
                    addShopUiState,
                    clearImages
                )
            }


        }
    }

    suspend fun add(
        context: Context,
        currentList: MutableList<ShopsResponseNewItem>,
        name: String,
        type: String,
        shopHomePageViewModel: ShopHomePageViewModel,
        appHomePageViewModel: AppHomePageViewModel,
        addShopUiState: AddShopUiState,
        clearImages: () -> Unit
    ) {
        if (!uiState.value.productPrice.equals("") && !uiState.value.numberInStack.equals("") && !uiState.value.productName.equals(
                ""
            )
        ) {
            /*      if (uiState.value.coverPhotoBitmap?.byteCount!! < 50000){*/
            currentList.forEach {
                if (it.shop_name.equals(name)) {
                    it.id?.let { it1 -> setId(it1) }
                    Log.d("TAGDDD", "addProductCategory: ${id.value}")
                } else Log.d("TAGDDD", "addProductCategory: Not found")
            }
            /*val result = */id.value?.let {

                addProductUseCase.addProduct(
                    token= app.token.value ,
                    shopId = it,
                    productCategoryId = uiState.value.productCategoryId,
                    productName = uiState.value.productName,
                    productDescription = uiState.value.productDescription,
                    productPrice = uiState.value.productPrice.toInt(),
                    productCoverPhoto = uiState.value.coverPhotoUri,
                    //      dateAdded =,
                    context = context,
                    numberInStack = uiState.value.numberInStack.toInt(),
                    type = type,
                    productId = uiState.value.productId,
                    appViewModel = appViewModel!!
                ).flowOn(Dispatchers.IO)
               /*     .catch { e ->
                        // handle exception
                    }*/
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: $result")
                        when (result) {
                            is Resource.Success -> {


                                Log.d("DDDDTAG", "onAddProduct: ${result.data}")
                                if (result.data != null) {
                                    clearValues()
                                    clearImages()
                                    uiState.update { it.copy(pageLoading = false) }

                                    if (!type.equals("editproduct")) {

                                        for (i in 0..2) {
                                            if (i == 0) {
                                                if (appViewModel!!.appUiState.value.activeProfile?.type.equals(
                                                        ProfileTypes.CUSTOMER
                                                    )
                                                ) {
                                                        /*appViewModel!!.getUserDetails(

                                                        )*/

                                                }
                                            }
                                            if (i == 1) {
                                                appViewModel!!.appUiState.value.userProfiles.forEach {
                                                    /*  Toast.makeText(
                                                          context,
                                                          "${appViewModel!!.appUiState.value.activeProfile?.type}",
                                                          Toast.LENGTH_SHORT
                                                      ).show()*/

                                                    if (appViewModel!!.appUiState.value.activeProfile?.type.equals(
                                                            ProfileTypes.SHOP
                                                        )
                                                    ) {
                                                        appViewModel!!.appUiState.value.activeProfile?.id?.let { it1 ->
                                                            shopHomePageViewModel.settempshopId(
                                                                it1.toString()
                                                            )
                                                        }

                                                    } else {
                                                        appHomePageViewModel.homePageUiState.value.popularShops.forEach {
                                                            Log.d(
                                                                "tgftf",
                                                                "onAddProduct: ${it.shop_name}     ${addShopUiState.shopName}"
                                                            )
                                                            if (addShopUiState.shopName.equals(it.shop_name)) {
                                                                it.id?.let { it1 ->
                                                                    shopHomePageViewModel.settempshopId(
                                                                        it1
                                                                    )

                                                                    /*     shopHomePageViewModel.getGetShopProducts(
                                                                             it1
                                                                         )*/

                                                                    /* appViewModel.appUiState.value.userProfiles.forEach {
                                                                         Toast.makeText(context, "${shopHomePageViewModel.tempshopid.value}     ${it.id}", Toast.LENGTH_SHORT).show()
                                                                         if (it.id.equals(shopHomePageViewModel.tempshopid.value)) {
                                                                             appViewModel.onSelectProfile(it)
                                                                         }
                                                                     }*/

                                                                }
                                                            }
                                                        }
                                                    }



                                                    Log.d(
                                                        "twgtwg",
                                                        "onAddProduct: ${it.id}     ${shopHomePageViewModel.tempshopid.value}"
                                                    )
                                                    if (it.id.equals(shopHomePageViewModel.tempshopid.value)) {
                                                        /*   Toast.makeText(
                                                               context,
                                                               "${appViewModel!!.appUiState.value.activeProfile?.type}",
                                                               Toast.LENGTH_SHORT
                                                           ).show()*/

                                                        appViewModel!!.onSelectProfile(it)
                                                        appViewModel?.navigate(
                                                            ShopsBacksideNavigation.SHOP_PRODUCTS
                                                        )
                                                    }
                                                }
                                            }
                                            delay(1500)
                                        }


                                    } else {
                                        appViewModel?.navigate(ShopsBacksideNavigation.SHOP_PRODUCTS)
                                    }

                                }
                            }
                            is Resource.Loading -> {
                                uiState.update { it.copy(pageLoading = true) }
                            }
                            is Resource.Error -> {
                                result.message?.let { message ->
                                    uiState.update {
                                        it.copy(
                                            pageLoading = false,
                                            errorList = listOf(message)
                                        )
                                    }
                                }

                            }
                        }

                    }
            }

            /*        }else {

                        uiState.update { it.copy(pageLoading = false) }

                        Toast.makeText(context, " chooose a smaller cover image", Toast.LENGTH_SHORT).show()
                    }*/
        } else {
            uiState.update { it.copy(pageLoading = false) }
            Toast.makeText(context, "fill in all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    fun clearValues() {
        uiState.update {
            it.copy(
                productName = "",
                productDescription = "",
                productPrice = "",
                numberInStack = "",
                discountPercentage = "",
                discountPrice = "",
                offerDiscount = false,
                couponCode = "",
                coverPhotoBitmap = null,
                coverPhotoUri = null,
                multiplePhotoUri = listOf(),
                multiplePhotoBitmap = listOf(), measurementUnit = ""
            )
        }
    }
}