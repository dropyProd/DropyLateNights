package com.example.dropy.ui.screens.topUp

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopUpViewModel @Inject constructor(
    private val app: DropyApp
): ViewModel() {


}