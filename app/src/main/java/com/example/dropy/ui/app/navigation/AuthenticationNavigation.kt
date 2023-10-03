package com.example.dropy.ui.app.navigation

import android.util.Log
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.authentication.AuthLandingPage
import com.example.dropy.ui.screens.authentication.AuthenticationViewModel
import com.example.dropy.ui.screens.authentication.ForgotPasswordScreen
import com.example.dropy.ui.screens.authentication.otpverification.EnterOtp
import com.example.dropy.ui.screens.authentication.otpverification.EnterPhoneNumber

import com.example.dropy.ui.screens.authentication.register.PhoneAuthRegister
import com.example.dropy.ui.screens.loginAs.LoginAsDialogViewModel
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointViewModel
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel

object AuthenticationDestinations {
    const val AUTHENTICATION_LANDING_PAGE = "authLandingPage"
    const val REGISTER_PAGE = "registerPage"
    const val ENTER_PHONE_NUMBER = "enterPhoneNumber"
    const val FORGOT_PASSWORD = "forgotPassword"
    const val ENTER_OTP = "enterOtpCode"
}

fun NavGraphBuilder.authenticationNavGraph(
    navController: NavController,
    appViewModel: AppViewModel,
    scanQRWaterViewModel: ScanQRWaterViewModel,
    cartPageViewModel: CartPageViewModel,
    authenticationViewModel: AuthenticationViewModel,
    nearestWaterPointViewModel: NearestWaterPointViewModel,
    truckStartTripViewModel: TruckStartTripViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel
) {
    navigation(
        startDestination = AuthenticationDestinations.AUTHENTICATION_LANDING_PAGE,
        route = AppDestinations.AUTHENTICATION
    ) {


        composable(AuthenticationDestinations.AUTHENTICATION_LANDING_PAGE) {
            val loginBackStackEntry =
                remember { navController.getBackStackEntry(AppDestinations.AUTHENTICATION) }
            val authenticationViewModel: AuthenticationViewModel =
                hiltViewModel(loginBackStackEntry)
            if (authenticationViewModel.appViewModel == null) {
                authenticationViewModel.appViewModel = appViewModel
            }
            if (authenticationViewModel.context == null) {
                authenticationViewModel.context = LocalContext.current
            }
            AuthLandingPage(
                authenticationViewModel = authenticationViewModel,
                appViewModel = appViewModel
            )
        }
        composable(AuthenticationDestinations.REGISTER_PAGE) {
            val loginBackStackEntry =
                remember { navController.getBackStackEntry(AppDestinations.AUTHENTICATION) }
            val authenticationViewModel: AuthenticationViewModel =
                hiltViewModel(loginBackStackEntry)
            if (authenticationViewModel.appViewModel == null) {
                authenticationViewModel.appViewModel = appViewModel
            }
            if (authenticationViewModel.context == null) {
                authenticationViewModel.context = LocalContext.current
            }

            val loginAsDialogViewModel: LoginAsDialogViewModel =
                hiltViewModel()
            if (loginAsDialogViewModel.appViewModel == null) {
                loginAsDialogViewModel.appViewModel = appViewModel
            }
            PhoneAuthRegister(
                authenticationViewModel = authenticationViewModel,
                loginAsDialogViewModel = loginAsDialogViewModel
            )
        }
        composable(AuthenticationDestinations.ENTER_PHONE_NUMBER) {
            val loginBackStackEntry =
                remember { navController.getBackStackEntry(AppDestinations.AUTHENTICATION) }
            val authenticationViewModel: AuthenticationViewModel =
                hiltViewModel(loginBackStackEntry)
            if (authenticationViewModel.appViewModel == null) {
                authenticationViewModel.appViewModel = appViewModel
            }
            val loginAsDialogViewModel: LoginAsDialogViewModel =
                hiltViewModel()
            if (loginAsDialogViewModel.appViewModel == null) {
                loginAsDialogViewModel.appViewModel = appViewModel
            }
            if (authenticationViewModel.context == null) {
                authenticationViewModel.context = LocalContext.current
            }
            EnterPhoneNumber(
                authenticationViewModel = authenticationViewModel,
                loginAsDialogViewModel = loginAsDialogViewModel
            )
        }

        composable(AuthenticationDestinations.FORGOT_PASSWORD) {
            val loginBackStackEntry =
                remember { navController.getBackStackEntry(AppDestinations.AUTHENTICATION) }
            val authenticationViewModel: AuthenticationViewModel =
                hiltViewModel(loginBackStackEntry)
            if (authenticationViewModel.appViewModel == null) {
                authenticationViewModel.appViewModel = appViewModel
            }
            if (authenticationViewModel.context == null) {
                authenticationViewModel.context = LocalContext.current
            }
            ForgotPasswordScreen(authenticationViewModel = authenticationViewModel)
        }

        composable(AuthenticationDestinations.ENTER_OTP) {

            EnterOtp(
                authenticationViewModel = authenticationViewModel,
                scanQRWaterViewModel = scanQRWaterViewModel,
                cartPageViewModel = cartPageViewModel,
                nearestWaterPointViewModel = nearestWaterPointViewModel,
                truckIncomingWorkViewModel = truckIncomingWorkViewModel,
                truckStartTripViewModel = truckStartTripViewModel
            )
        }
    }
}