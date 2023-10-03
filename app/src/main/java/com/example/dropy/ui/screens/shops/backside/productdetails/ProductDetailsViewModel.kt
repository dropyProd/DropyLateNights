package com.example.dropy.ui.screens.shops.backside.productdetails

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.shops.backside.dashboard.product.productdetails.ProductImageDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ProductDetailsUiState(
    val productImages:List<ProductImageDataClass> = emptyList(),
    val productPrice: Int = 0,
    val numberInStock: Int = 0,
    val productUnits: String = "piece",
    val isAvailable: Boolean = true,
    val productName: String = "product name",
    val productDescription: String = "product description",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(): ViewModel() {

    private val uiState = MutableStateFlow(ProductDetailsUiState())

    val productDetailsUiState:StateFlow<ProductDetailsUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? = null

    fun onEditCoverPhoto(){

    }
    fun onDeleteImage(imageId:Int){

    }
    fun onAddImage(){

    }
    fun onProductNameChanged(productName:String){

    }
    fun onProductDescriptionChanged(description:String){

    }
    fun onProductPriceChanged(price:Int){

    }
    fun onProductUnitsChanged(units:String){

    }
    fun onAddToStack(){

    }
    fun onSubtractFromStack(){

    }
    fun onChangeAvailability(availability:Boolean){

    }
    fun onSaveClicked(){

    }
    fun onDeleteClicked(){

    }

}