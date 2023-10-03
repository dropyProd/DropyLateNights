package com.example.dropy.ui.screens.onboarding

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation


class OnBoardingViewModel : ViewModel(), LifecycleObserver {

    var appViewModel: AppViewModel? = null

    fun addAppViewModel(providedAppViewModel: AppViewModel) {
        appViewModel = providedAppViewModel
    }

    fun onOnBoardingFinished() {
        appViewModel?.navigate(AppDestinations.AUTHENTICATION)
//        appViewModel?.navigate(
//            ShopsBacksideNavigation.ORDER_READY
//        )
        //  appViewModel?.navigate(AppDestinations.APP_HOME)
        // appViewModel?.navigate(ShopsBacksideNavigation.ADD_SHOP)
        // appViewModel?.navigate(ShopsBacksideNavigation.ADD_PRODUCT)
    }

}