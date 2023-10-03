package com.example.dropy.ui.app.navigation.parcelsnavigation



import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.dropy.ui.app.AppViewModel


object SendParcelNavigation{
    const val SELECT_PARCEL_LOCATIONS = "selectParcelsLocations"
    const val PARCEL_RECEIVER_DETAILS ="parcelReceiverDetails"
    const val EXPRESS_DELIVERY_METHOD = "parcelExpressDeliveryMethod"
    const val EXPRESS_DELIVERY_PERSON = "parcelExpressDeliveryPerson"
    const val PARCEL_PAYMENT = "parcelPayment"
    const val PAYMENT_SUCCESSFUL = "paymentSuccessful"
}

fun NavGraphBuilder.sendParcelNavigation(navController: NavController, appViewModel: AppViewModel){
    /*navigation(startDestination = SendParcelNavigation.SELECT_PARCEL_LOCATIONS, route = ParcelsFrontNavigation.SEND_PARCEL){

        composable(SendParcelNavigation.SELECT_PARCEL_LOCATIONS){
            val sendParcelBackStackEntry = remember { navController.getBackStackEntry(ParcelsFrontNavigation.SEND_PARCEL) }
            val sendParcelViewModel:SendParcelViewModel = hiltViewModel(sendParcelBackStackEntry)
            if (sendParcelViewModel.appViewModel == null){
                sendParcelViewModel.appViewModel = appViewModel
            }
            SelectParcelsLocations(sendParcelViewModel = sendParcelViewModel)
        }

        composable(SendParcelNavigation.PARCEL_RECEIVER_DETAILS){
            val sendParcelBackStackEntry = remember { navController.getBackStackEntry(ParcelsFrontNavigation.SEND_PARCEL) }
            val parcelReceiverDetailsViewModel: ParcelReceiverDetailsViewModel = hiltViewModel(sendParcelBackStackEntry)
            val sendParcelViewModel:SendParcelViewModel = hiltViewModel(sendParcelBackStackEntry)
            if (sendParcelViewModel.appViewModel == null){
                sendParcelViewModel.appViewModel = appViewModel
            }
            ParcelReceiverDetails(
                parcelReceiverDetailsViewModel = parcelReceiverDetailsViewModel,
                sendParcelViewModel = sendParcelViewModel
            )
        }

        composable(SendParcelNavigation.EXPRESS_DELIVERY_METHOD){
            val sendParcelBackStackEntry = remember { navController.getBackStackEntry(ParcelsFrontNavigation.SEND_PARCEL) }
            val sendParcelViewModel:SendParcelViewModel = hiltViewModel(sendParcelBackStackEntry)
            if (sendParcelViewModel.appViewModel == null){
                sendParcelViewModel.appViewModel = appViewModel
            }
            SelectExpressDeliveryMethod(sendParcelViewModel = sendParcelViewModel)
        }

        composable(SendParcelNavigation.EXPRESS_DELIVERY_PERSON){
            val sendParcelBackStackEntry = remember { navController.getBackStackEntry(ParcelsFrontNavigation.SEND_PARCEL) }
            val sendParcelViewModel:SendParcelViewModel = hiltViewModel(sendParcelBackStackEntry)
            if (sendParcelViewModel.appViewModel == null){
                sendParcelViewModel.appViewModel = appViewModel
            }
            SelectExpressDeliveryPerson(sendParcelViewModel = sendParcelViewModel)
        }

        composable(SendParcelNavigation.PARCEL_PAYMENT){
            val sendParcelBackStackEntry = remember { navController.getBackStackEntry(ParcelsFrontNavigation.SEND_PARCEL) }
            val sendParcelViewModel:SendParcelViewModel = hiltViewModel(sendParcelBackStackEntry)
            if (sendParcelViewModel.appViewModel == null){
                sendParcelViewModel.appViewModel = appViewModel
            }
            ParcelPayment(sendParcelViewModel = sendParcelViewModel)
        }

        composable(SendParcelNavigation.PAYMENT_SUCCESSFUL){
            val sendParcelBackStackEntry = remember { navController.getBackStackEntry(ParcelsFrontNavigation.SEND_PARCEL) }
            val sendParcelViewModel:SendParcelViewModel = hiltViewModel(sendParcelBackStackEntry)
            if (sendParcelViewModel.appViewModel == null){
                sendParcelViewModel.appViewModel = appViewModel
            }
            ParcelPaymentComplete(sendParcelViewModel = sendParcelViewModel)
        }
    }*/
}