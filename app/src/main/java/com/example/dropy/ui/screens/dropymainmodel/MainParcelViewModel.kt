package com.example.dropy.ui.screens.dropymainmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainParcelViewModel @Inject constructor(): ViewModel() {
    val pickup = mutableStateOf("")

    val destination = mutableStateOf("")

    fun onPickupChange(it:String){
        pickup.value = it
    }
    fun clearPickupChange(){
        pickup.value = ""
    }

 fun onDestinationChange(it:String){
        destination.value = it
    }
    fun clearDestinationChange(){
        destination.value = ""
    }

}