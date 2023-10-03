package com.example.dropy.ui.screens.approvalRequests

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.approvalRequests.ApprovalRequestsResItem
import com.example.dropy.network.use_case.approvalRequests.ApprovalRequestsUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.addWaterDriver.TruckDriverUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ApprovalRequestUiState(
    val approvalRequests: List<ApprovalRequestsResItem> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ApprovalRequestViewModel @Inject constructor(
    private val app: DropyApp,
    private val approvalRequestsUseCase: ApprovalRequestsUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(ApprovalRequestUiState())

    val approvalRequestUiState: StateFlow<ApprovalRequestUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun getApprovalRequests() {
        viewModelScope.launch {
            approvalRequestsUseCase(
                token = "Token " + app.token.value
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
                                val list: MutableList<ApprovalRequestsResItem> = mutableListOf()
                                result.data.forEach {
                                    list.add(it)
                                }
                                uiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        approvalRequests = list
                                    )
                                }

                            }

                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
//                            result.message?.let { message ->
                            uiState.update {
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