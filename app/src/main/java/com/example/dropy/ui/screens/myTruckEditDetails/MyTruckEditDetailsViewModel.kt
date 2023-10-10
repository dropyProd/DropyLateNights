package com.example.dropy.ui.screens.myTruckEditDetails

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MyTruckEditDetailsUiState(
    val shopCoverPhoto: ImageBitmap? = null,
    val shopCoverPhotoUri: Uri? = null,
    val active: Boolean = false,
    val licensePlate: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class MyTruckEditDetailsViewModel @Inject constructor(
    private val app: DropyApp
): ViewModel(){


    val uiState = MutableStateFlow(MyTruckEditDetailsUiState())

    val myTruckEditDetailsUiState: StateFlow<MyTruckEditDetailsUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun changeActiveState(state: Boolean){
        uiState.update {
            it.copy(
                active = state
            )
        }
    }

}