package com.example.dropy.ui.screens.topUp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.topUp.TopUpReq
import com.example.dropy.network.use_case.topUpWallet.TopUpWalletUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TopUpUiState(
    val number: String = "",
    val amount: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class TopUpViewModel @Inject constructor(
    private val app: DropyApp,
    private val topUpWalletUseCase: TopUpWalletUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(TopUpUiState())

    val topUpUiState: StateFlow<TopUpUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    /*fun save*/

    fun onAmountChange(text: String){
        uiState.update { it.copy(amount = text) }
    }

    fun topUpWallet() {
        viewModelScope.launch {
            val item = TopUpReq(
                phone_number = 794700294,
                amount = uiState.value.amount.toString().toInt()
            )
            topUpWalletUseCase(
                topUpReq = item
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

                            Log.d("KKTAG", "onAddShop: $result")
                            if (result.data != null) {
                                //  if (result.data?.resultCode?.equals(0) == true) {
                                //                                _addShopImagesUiState.update { it.copy(pageLoading = false) }
                                //                                moveAddProductCategory()
                                // }

                                uiState.update {
                                    it.copy(
                                        pageLoading = false
                                    )
                                }
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