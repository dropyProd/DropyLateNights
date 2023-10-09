package com.example.dropy.ui.screens.myWallet

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyWalletViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {

}