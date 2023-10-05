package com.example.dropy.ui.app

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dropy.ui.app.navigation.authenticationNavGraph
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.shopsBacksideNavGraph
import com.example.dropy.ui.components.commons.*
import com.example.dropy.ui.components.commons.maps.selectlocation.SelectLocationViewModel

import com.example.dropy.ui.components.parcels.frontside.expressdeliverymethod.ExpressDeliveryContent
import com.example.dropy.ui.components.payment.DropyPayment
import com.example.dropy.ui.components.payment.PaymentThanks
import com.example.dropy.ui.components.rider.incomingjobs.IncomingJobContent
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory.orderreciept.OrderReceiptContent
import com.example.dropy.ui.components.shops.frontside.shopnearby.ShopNearbyHeader
import com.example.dropy.ui.screens.MainLanding
import com.example.dropy.ui.screens.SplashScreen
import com.example.dropy.ui.screens.addWaterDriver.AddWaterDriver
import com.example.dropy.ui.screens.addWaterDriver.AddWaterDriverViewModel
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointDetails
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointImages
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointLocation
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointViewmodel
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckImages
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckViewmodel
import com.example.dropy.ui.screens.addWaterTruck.AddWatertruckDetails
import com.example.dropy.ui.screens.addWaterTruck.AddWatertruckLocation
import com.example.dropy.ui.screens.addWaterVendor.AddWaterVendorDetails
import com.example.dropy.ui.screens.addWaterVendor.AddWaterVendorImages
import com.example.dropy.ui.screens.addWaterVendor.AddWaterVendorViewModel
import com.example.dropy.ui.screens.addWaterVendor.AddWatervendorLocation
import com.example.dropy.ui.screens.allocateTruck.AllocatingTruck
import com.example.dropy.ui.screens.allocateTruck.AllocatingTruckViewModel
import com.example.dropy.ui.screens.apphome.AppHomePage
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.authentication.AuthenticationViewModel

import com.example.dropy.ui.screens.balance.BalanceScreen
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeViewModel
import com.example.dropy.ui.screens.dropymainmodel.DropyMainModel
import com.example.dropy.ui.screens.dropymainmodel.DropyMainParcels
import com.example.dropy.ui.screens.dropymainmodel.MainParcelViewModel
import com.example.dropy.ui.screens.locale.LocationExpressRider
import com.example.dropy.ui.screens.loginAs.LoginAsDialogViewModel
import com.example.dropy.ui.screens.myTrucks.MyTrucks
import com.example.dropy.ui.screens.myTrucks.MyTrucksViewmodel
import com.example.dropy.ui.screens.nearestTrucks.NearestTrucks
import com.example.dropy.ui.screens.nearestTrucks.NearestTrucksViewmodel
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPoint
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointViewModel
import com.example.dropy.ui.screens.onboarding.OnBoarding
import com.example.dropy.ui.screens.onboarding.OnBoardingViewModel
import com.example.dropy.ui.screens.options.DropyOptions
import com.example.dropy.ui.screens.parcel.SelectedExpressRider
import com.example.dropy.ui.screens.payment.AddPayment
import com.example.dropy.ui.screens.payment.PayScreen
import com.example.dropy.ui.screens.payment.PaymentScreen
import com.example.dropy.ui.screens.pickCustomer.PickCustomer
import com.example.dropy.ui.screens.pin.PinScreen
import com.example.dropy.ui.screens.pin.PinScreenViewModel
import com.example.dropy.ui.screens.qr.ScanQr
import com.example.dropy.ui.screens.reciever.DropyReciever
import com.example.dropy.ui.screens.review.ReviewRiderScreen
import com.example.dropy.ui.screens.review.ReviewShopScreen
import com.example.dropy.ui.screens.review.ReviewThanksScreen
import com.example.dropy.ui.screens.rider.*
import com.example.dropy.ui.screens.rider.becomerider.AddRider
import com.example.dropy.ui.screens.rider.becomerider.AddRiderViewModel
import com.example.dropy.ui.screens.rider.becomerider.BecomeRiderViewModel
import com.example.dropy.ui.screens.rider.becomerider.riderLocation.AddRiderLocation
import com.example.dropy.ui.screens.rider.becomerider.riderimage.AddRiderImage
import com.example.dropy.ui.screens.scanQRWater.ScanQRWater
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.searchScreen.SearchScreen
import com.example.dropy.ui.screens.serviceProviders.ServiceProviders
import com.example.dropy.ui.screens.serviceProviders.ServiceProvidersViewModel
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProductViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.addshopimages.AddShopImages
import com.example.dropy.ui.screens.shops.backside.addshop.addshoplocation.AddShopLocation
import com.example.dropy.ui.screens.shops.backside.jobsearch.JobSearch
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.allshopcategories.AllShopCategories
import com.example.dropy.ui.screens.shops.frontside.allshopcategories.AllShopCategoriesViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPage
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.categorysingle.CategorySingle
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CompleteEtaDelivery.PaymentCompleteEtaDelivery
import com.example.dropy.ui.screens.shops.frontside.checkout.deliveryinformation.DeliverInformation
import com.example.dropy.ui.screens.shops.frontside.checkout.ordercomplete.OrderComplete
import com.example.dropy.ui.screens.shops.frontside.checkout.payment.OrderPaymentPage
import com.example.dropy.ui.screens.shops.frontside.checkout.scanQr.ScanQrShop
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrder
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.addressbook.CustomerAddressBook
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.addressbook.CustomerAddressBookViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress.SingleAddress
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress.SingleAddressViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.customerdashboard.CustomerDashboard
import com.example.dropy.ui.screens.shops.frontside.dashboard.customerdashboard.CustomerDashboardViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory.CustomerOrderHistory
import com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory.CustomerOrderHistoryViewModel
import com.example.dropy.ui.screens.shops.frontside.productpage.ProductPage
import com.example.dropy.ui.screens.shops.frontside.productpage.ProductPageViewModel
import com.example.dropy.ui.screens.shops.frontside.shopnearby.ShopSpecificSingleProduct
import com.example.dropy.ui.screens.shops.frontside.shopsbycategory.ShopsByCategory
import com.example.dropy.ui.screens.shops.frontside.shopsbycategory.ShopsByCategoryViewModel
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPage
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePage
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBorehole
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.tracking.TrackParcel
import com.example.dropy.ui.screens.truckDash.TruckDash
import com.example.dropy.ui.screens.truckDash.TruckDashViewModel
import com.example.dropy.ui.screens.truckDeduction.TruckDeductionContent
import com.example.dropy.ui.screens.truckFindJob.TruckFindJob
import com.example.dropy.ui.screens.truckFindJob.TruckFindJobViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWork
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckOrderComplete.TruckOrderComplete
import com.example.dropy.ui.screens.truckOrderComplete.TruckOrderCompleteViewModel
import com.example.dropy.ui.screens.truckOrders.TruckOrders
import com.example.dropy.ui.screens.truckOrders.TruckOrdersViewModel
import com.example.dropy.ui.screens.truckRouteCustomer.TruckRouteCustomer
import com.example.dropy.ui.screens.truckRouteCustomer.TruckRouteCustomerViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPoint
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTrip
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel
import com.example.dropy.ui.screens.watepointDash.WaterPointDash
import com.example.dropy.ui.screens.watepointDash.WaterPointDashViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHome
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrder
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderViewModel
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetails
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel
import com.example.dropy.ui.screens.waterOrderPay.WaterOrderPay
import com.example.dropy.ui.screens.waterOrderPay.WaterOrderPayViewModel
import com.example.dropy.ui.screens.waterOrderSingle.WaterOrderSingle
import com.example.dropy.ui.screens.waterOrderSingle.WaterOrderSingleViewModel
import com.example.dropy.ui.screens.waterServiceProviders.WaterServiceProviders
import com.example.dropy.ui.screens.waterServiceProviders.WaterServiceProvidersViewModel
import com.example.dropy.ui.screens.waterTracking.WaterTracking
import com.example.dropy.ui.screens.waterTracking.WaterTrackingViewModel
import com.example.dropy.ui.screens.waterTransactionComplete.WaterTransactionComplete
import com.example.dropy.ui.screens.waterTransactionComplete.WaterTransactionCompleteViewModel
import com.example.dropy.ui.screens.waterVendorDash.WaterVendorDash
import com.example.dropy.ui.screens.waterVendorDash.WaterVendorDashViewModel
import com.example.dropy.ui.screens.waterpointNewOrder.WaterpointNewOrder
import com.example.dropy.ui.screens.waterpointNewOrder.WaterpointNewOrderViewModel
import com.example.dropy.ui.screens.waterpointOrders.WaterpointOrders
import com.example.dropy.ui.screens.waterpointOrders.WaterpointOrdersViewModel
import com.example.dropy.ui.screens.workDiary.WorkDiary
import com.example.dropy.ui.screens.workDiary.WorkDiaryViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch


object AppDestinations {
    const val SPLASH = "splash"
    const val MAIN_LANDING = "mainlanding"
    const val ON_BOARDING = "onBoarding"
    const val AUTHENTICATION = "authentication"
    const val PIN = "pin"
    const val PAYMENT = "payment"
    const val APP_HOME = "appHome"
    const val SEARCH_SCREEN = "searchScreen"
    const val SHOPS_FRONT = "shopsFront"
    const val SHOPS_BACK = "shopsBack"
    const val PARCELS = "parcels"
    const val RIDES = "rides"
    const val WATER_HOME = "waterHome"
    const val TRUCK_START_TRIP = "truckStartTrip"
    const val TANKER_BOREHOLE = "tankerBoreHole"
    const val WATER_ORDER_PAY = "waterOrderPay"
    const val WATER_ORDER_DETAILS = "waterOrderDetails"
    const val ALLOCATING_TRUCKS = "allocatingTrucks"
    const val WATER_TRANSACTION_COMPLETE = "waterTransactionComplete"
    const val WATER_MY_ORDER = "waterMyOrder"
    const val WATER_TRACKING = "waterTracking"
    const val SCAN_QR_WATER = "scanQrWater"
    const val TRUCK_FIND_JOB = "truckFindJob"
    const val TRUCK_INCOMING_WORK = "truckIncomingWork"
    const val WATER_ORDER_SINGLE = "waterOrderSingle"
    const val NEAREST_WATERPOINT = "nearestWaterPoint"
    const val NEAREST_TRUCKS = "nearestTrucks"
    const val TRUCK_ROUTE_WATERPOINT = "truckRouteWaterPoint"
    const val TRUCK_ROUTE_CUSTOMER = "truckRouteCustomer"
    const val TRUCK_ORDER_COMPLETE = "truckOrderComplete"
    const val TRUCK_ORDERS_HISTORY = "truckOrdersHistory"
    const val REVIEWSHOP = "reviewShop"
    const val REVIEWRIDER = "reviewRider"
    const val BALANCE = "balance"
    const val REVIEWTHANKS = "reviewThanks"
    const val MENU = "menu"
    const val CREATE_WATERVENDOR = "CreateWaterVendor"
    const val WATERVENDOR_LOCALE = "WaterVendorLocale"
    const val WATERVENDOR_IMAGES = "WaterVendorImage"
    const val CREATE_WATERTRUCK = "CreateWaterTruck"
    const val WATERTRUCK_LOCALE = "WatertruckLocale"
    const val WATERTRUCK_IMAGES = "WatertruckImages"
    const val CREATE_WATERPOINT = "CreateWaterpoint"
    const val WATERPOINT_LOCATION = "WaterpointLocation"
    const val WATERPOINT_IMAGES = "WaterpointImages"
    const val ADDPAYMENT = "addpayment"
    const val RIDERDETAILSDIALOG = "riderDetails"
    const val TRUCK_DASHBOARD = "truckDash"
    const val MY_TRUCK = "myTruck"
    const val WATER_VENDOR_DASHBOARD = "WaterVendorDash"
    const val ADD_WATER_DRIVER = "addWaterDriver"
    const val WATERPOINT_ORDERS = "WaterPointOrders"
    const val WATERPOINT_NEW_ORDER = "WaterPointNewOrder"
    const val WATERPOINT_DASH = "WaterPointDash"
    const val SERVICE_PROVIDERS = "ServiceProviders"
    const val WATER_SERVICE_PROVIDERS = "WaterServiceProviders"
    const val WORK_DIARY = "WorkDiary"
}


object RiderDestination {
    const val RIDEOPTIONS = "rideOptions"
    const val RIDERPICKUP = "ridePickup"
    const val DROPYHERE = "dropyHere"
    const val ARRIVEDPAY = "arrivedPay"
    const val RIDERDASHBOARD = "riderDashboard"
    const val RIDERJOBSEARCH = "jobSearch"
    const val RIDERINCOMINGORDERS = "IncomingOrder"
    const val RIDERPICKCUSTOMER = "PickCustomer"
}

object RiderBackside {
    const val ADDRIDER = "addRider"
    const val RIDERLOCATION = "riderLocation"
    const val RIDERIMAGE = "riderImage"
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun App(
    checkFingerPrint: () -> Unit, choosePhoto: (section: String, type: String) -> Unit,
    addProductViewModel: AddProductViewModel,
    onPlaceNameChanged: (String) -> Unit,
    openSearchmapdialog: () -> Unit,
    checkoutViewModel: CheckoutViewModel,
    selectLocationViewModel: SelectLocationViewModel,
    suggestedLocales: List<AddressDataClass>,
    scanQr: (NavController) -> Unit,
    fetchLocaleDetails: (String) -> Unit,
    shopHomePageViewModel: ShopHomePageViewModel,
    singleAddressViewModel: SingleAddressViewModel,
    becomeRiderViewModel: BecomeRiderViewModel,
    incomingJobViewModel: IncomingJobViewModel,
    clearImages: () -> Unit,
    customerDashboardViewModel: CustomerDashboardViewModel,
    scanQRWaterViewModel: ScanQRWaterViewModel,
    context: Context
) {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    val appViewModel: AppViewModel = hiltViewModel()
    val mainParcelViewModel: MainParcelViewModel = hiltViewModel()
    val appUiState by appViewModel.appUiState.collectAsState()
    if (appViewModel.navHostController == null) {
        appViewModel.navHostController = navController
    }
    if (appViewModel.systemUiController == null) {
        appViewModel.systemUiController = systemUiController
    }
    val addShopViewModel: AddShopViewModel = hiltViewModel()
    if (addShopViewModel.appViewModel == null) {
        addShopViewModel.appViewModel = appViewModel
    }
    val waterServiceProvidersViewModel: WaterServiceProvidersViewModel = hiltViewModel()
    if (waterServiceProvidersViewModel.appViewModel == null) {
        waterServiceProvidersViewModel.appViewModel = appViewModel
    }
    if (checkoutViewModel.appViewModel == null) {
        checkoutViewModel.appViewModel = appViewModel
    }


    if (shopHomePageViewModel.appViewModel == null) {
        shopHomePageViewModel.appViewModel = appViewModel
    }

    val appHomePageViewModel: AppHomePageViewModel = hiltViewModel()
    if (appHomePageViewModel.appViewModel == null) {
        appHomePageViewModel.appViewModel = appViewModel
    }
    val truckStartTripViewModel: TruckStartTripViewModel = hiltViewModel()
    if (truckStartTripViewModel.appViewModel == null) {
        truckStartTripViewModel.appViewModel = appViewModel
    }


    if (customerDashboardViewModel.appViewModel == null) {
        customerDashboardViewModel.appViewModel = appViewModel
    }

    val shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel = hiltViewModel()
    if (shopIncomingOrdersViewModel.appViewModel == null) {
        shopIncomingOrdersViewModel.appViewModel = appViewModel
    }

    val workDiaryViewModel: WorkDiaryViewModel = hiltViewModel()
    if (workDiaryViewModel.appViewModel == null) {
        workDiaryViewModel.appViewModel = appViewModel
    }

    val orderDetailsViewModel: OrderDetailsViewModel = hiltViewModel()
    if (orderDetailsViewModel.appViewModel == null) {
        //orderDetailsViewModel.setContext(contextt = context)
        orderDetailsViewModel.appViewModel = appViewModel
    }

    val addRiderViewModel: AddRiderViewModel = hiltViewModel()
    val cartPageViewModel: CartPageViewModel = hiltViewModel()
    if (cartPageViewModel.appViewModel == null) {
        cartPageViewModel.appViewModel = appViewModel
    }
    val addWaterpointViewmodel: AddWaterpointViewmodel = hiltViewModel()
    if (addWaterpointViewmodel.appViewModel == null) {
        addWaterpointViewmodel.appViewModel = appViewModel
    }
    val waterpointOrdersViewModel: WaterpointOrdersViewModel = hiltViewModel()
    if (waterpointOrdersViewModel.appViewModel == null) {
        waterpointOrdersViewModel.appViewModel = appViewModel
    }
    val waterpointNewOrderViewModel: WaterpointNewOrderViewModel = hiltViewModel()
    if (waterpointNewOrderViewModel.appViewModel == null) {
        waterpointNewOrderViewModel.appViewModel = appViewModel
    }
    val nearestTrucksViewmodel: NearestTrucksViewmodel = hiltViewModel()
    if (nearestTrucksViewmodel.appViewModel == null) {
        nearestTrucksViewmodel.appViewModel = appViewModel
    }
    val addWaterTruckViewmodel: AddWaterTruckViewmodel = hiltViewModel()
    if (addWaterTruckViewmodel.appViewModel == null) {
        addWaterTruckViewmodel.appViewModel = appViewModel
    }
    val addWaterVendorViewModel: AddWaterVendorViewModel = hiltViewModel()
    if (addWaterVendorViewModel.appViewModel == null) {
        addWaterVendorViewModel.appViewModel = appViewModel
    }

    if (singleAddressViewModel.appViewModel == null) {
        singleAddressViewModel.appViewModel = appViewModel
    }

    val shopsLandingPageViewModel: ShopsLandingPageViewModel = hiltViewModel()
    if (shopsLandingPageViewModel.appViewModel == null) {
        shopsLandingPageViewModel.appViewModel = appViewModel
    }

    val productPageViewModel: ProductPageViewModel = hiltViewModel()
    if (productPageViewModel.appViewModel == null) {
        productPageViewModel.appViewModel = appViewModel
    }
    val waterPointDashViewModel: WaterPointDashViewModel = hiltViewModel()
    if (waterPointDashViewModel.appViewModel == null) {
        waterPointDashViewModel.appViewModel = appViewModel
    }
    val serviceProvidersViewModel: ServiceProvidersViewModel = hiltViewModel()
    if (serviceProvidersViewModel.appViewModel == null) {
        serviceProvidersViewModel.appViewModel = appViewModel
    }

    val customerOrderHistoryViewModel: CustomerOrderHistoryViewModel = hiltViewModel()
    if (customerOrderHistoryViewModel.appViewModel == null) {
        customerOrderHistoryViewModel.appViewModel = appViewModel
    }

    val waterHomeViewModel: WaterHomeViewModel = hiltViewModel()
    if (waterHomeViewModel.appViewModel == null) {
        waterHomeViewModel.appViewModel = appViewModel
    }
    val truckDashViewModel: TruckDashViewModel = hiltViewModel()
    if (truckDashViewModel.appViewModel == null) {
        truckDashViewModel.appViewModel = appViewModel
    }
    val waterVendorDashViewModel: WaterVendorDashViewModel = hiltViewModel()
    if (waterVendorDashViewModel.appViewModel == null) {
        waterVendorDashViewModel.appViewModel = appViewModel
    }
    val addWaterDriverViewModel: AddWaterDriverViewModel = hiltViewModel()
    if (addWaterDriverViewModel.appViewModel == null) {
        addWaterDriverViewModel.appViewModel = appViewModel
    }
    val myTrucksViewmodel: MyTrucksViewmodel = hiltViewModel()
    if (myTrucksViewmodel.appViewModel == null) {
        myTrucksViewmodel.appViewModel = appViewModel
    }

    val deliveryTypeViewModel: DeliveryTypeViewModel = hiltViewModel()
    if (deliveryTypeViewModel.appViewModel == null) {
        deliveryTypeViewModel.appViewModel = appViewModel
    }
    val tankerBoreholeViewModel: TankerBoreholeViewModel = hiltViewModel()
    if (tankerBoreholeViewModel.appViewModel == null) {
        tankerBoreholeViewModel.appViewModel = appViewModel
    }

    val waterOrderDetailsViewModel: WaterOrderDetailsViewModel = hiltViewModel()
    if (waterOrderDetailsViewModel.appViewModel == null) {
        waterOrderDetailsViewModel.appViewModel = appViewModel
    }

    val waterOrderPayViewModel: WaterOrderPayViewModel = hiltViewModel()
    if (waterOrderPayViewModel.appViewModel == null) {
        waterOrderPayViewModel.appViewModel = appViewModel
    }

    val allocatingTruckViewModel: AllocatingTruckViewModel = hiltViewModel()
    if (allocatingTruckViewModel.appViewModel == null) {
        allocatingTruckViewModel.appViewModel = appViewModel
    }

    val waterTransactionCompleteViewModel: WaterTransactionCompleteViewModel = hiltViewModel()
    if (waterTransactionCompleteViewModel.appViewModel == null) {
        waterTransactionCompleteViewModel.appViewModel = appViewModel
    }

    val waterMyOrderViewModel: WaterMyOrderViewModel = hiltViewModel()
    if (waterMyOrderViewModel.appViewModel == null) {
        waterMyOrderViewModel.appViewModel = appViewModel
    }

    val waterTrackingViewModel: WaterTrackingViewModel = hiltViewModel()
    if (waterTrackingViewModel.appViewModel == null) {
        waterTrackingViewModel.appViewModel = appViewModel
    }

    if (scanQRWaterViewModel.appViewModel == null) {
        scanQRWaterViewModel.appViewModel = appViewModel
    }
    val truckFindJobViewModel: TruckFindJobViewModel = hiltViewModel()
    if (truckFindJobViewModel.appViewModel == null) {
        truckFindJobViewModel.appViewModel = appViewModel
    }
    val truckIncomingWorkViewModel: TruckIncomingWorkViewModel = hiltViewModel()
    if (truckIncomingWorkViewModel.appViewModel == null) {
        truckIncomingWorkViewModel.appViewModel = appViewModel
    }

    val waterOrderSingleViewModel: WaterOrderSingleViewModel = hiltViewModel()
    if (waterOrderSingleViewModel.appViewModel == null) {
        waterOrderSingleViewModel.appViewModel = appViewModel
    }

    val nearestWaterPointViewModel: NearestWaterPointViewModel = hiltViewModel()
    if (nearestWaterPointViewModel.appViewModel == null) {
        nearestWaterPointViewModel.appViewModel = appViewModel
    }
    val truckWaterPointViewModel: TruckRouteWaterPointViewModel = hiltViewModel()
    if (truckWaterPointViewModel.appViewModel == null) {
        truckWaterPointViewModel.appViewModel = appViewModel
    }
    val truckCustomerViewModel: TruckRouteCustomerViewModel = hiltViewModel()
    if (truckCustomerViewModel.appViewModel == null) {
        truckCustomerViewModel.appViewModel = appViewModel
    }

    val truckOrderCompleteViewModel: TruckOrderCompleteViewModel = hiltViewModel()
    if (truckOrderCompleteViewModel.appViewModel == null) {
        truckOrderCompleteViewModel.appViewModel = appViewModel
    }
    val truckOrdersViewModel: TruckOrdersViewModel = hiltViewModel()
    if (truckOrdersViewModel.appViewModel == null) {
        truckOrdersViewModel.appViewModel = appViewModel
    }
    val authenticationViewModel: AuthenticationViewModel =
        hiltViewModel()
    if (authenticationViewModel.appViewModel == null) {
        authenticationViewModel.appViewModel = appViewModel
    }
  /*  if (authenticationViewModel.context == null) {
        authenticationViewModel.context = LocalContext.current
    }*/


    val trackYourOrderViewModel: TrackYourOrderViewModel = hiltViewModel()
    val pinScreenViewModel: PinScreenViewModel = hiltViewModel()

    val startDestination = if (appUiState.firebaseUid == null) {
        AppDestinations.SPLASH
        // AppDestinations.RIDERDETAILSDIALOG
    } else {
        AppDestinations.APP_HOME
        //  AppDestinations.RIDERDETAILSDIALOG
    }

    val ExpresRoute = remember {
        mutableStateOf("default")
    }
    val PaymentRoute = remember {
        mutableStateOf("default")
    }
    val state = remember {
        mutableStateOf("")
    }

    val pinState = remember {
        mutableStateOf("password")
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(AppDestinations.RIDERDETAILSDIALOG) {
            LaunchedEffect(key1 = true, block = {
                appHomePageViewModel.getAllShops()
            })
            SwitchVectorAnimation()
            /*   RiderDetailsDialog(show = true) {

               }*/
            //RiderPickup(navController = navController, startLocale = start.value)
        }

        composable(AppDestinations.SPLASH) {

            LaunchedEffect(key1 = true, block = {
                // appHomePageViewModel.message()
            })
            //DnaAnimation(modifier = Modifier)
            val authenticationViewModel: AuthenticationViewModel =
                hiltViewModel()
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
            SplashScreen(
                navController = navController,
                authenticationViewModel = authenticationViewModel,
                loginAsDialogViewModel = loginAsDialogViewModel
            )

        }
        composable(AppDestinations.MAIN_LANDING) {

            MainLanding(navController = navController, appViewModel = appViewModel)

        }
        val start: MutableState<LatLng> = mutableStateOf(LatLng(0.0, 0.0))

        composable(AppDestinations.ON_BOARDING) {
            val onBoardingViewModel: OnBoardingViewModel = viewModel()
            if (onBoardingViewModel.appViewModel == null) {
                onBoardingViewModel.addAppViewModel(providedAppViewModel = appViewModel)
            }

            val context = LocalContext.current as ComponentActivity

            /*      LaunchedEffect(key1 = true, block = {
                      val locale = getLocation(context = context)
                      start.value = LatLng(locale.latitude, locale.longitude)
                  })*/


            OnBoarding(onBoardingViewModel = onBoardingViewModel)
            //  AddPayment()
            // RiderOrders()
            //DropyOptions()
            // FingerPrint(checkFingerPrint = checkFingerPrint)
            //PickCustomer()
        }
        composable(AppDestinations.PIN) {

            PinScreen(
                navController = navController,
                incomingJobViewModel = incomingJobViewModel,
                pinScreenViewModel = pinScreenViewModel,
                appViewModel = appViewModel, type = pinState.value
            )

        }
        composable(AppDestinations.ADDPAYMENT) {

            AddPayment()

        }
        composable(AppDestinations.WORK_DIARY) {

            WorkDiary(
                cartPageViewModel = cartPageViewModel,
                workDiaryViewModel = workDiaryViewModel
            )

        }
        composable(AppDestinations.PAYMENT) {

            PaymentScreen()
        }

        composable(AppDestinations.APP_HOME) {

            val context = LocalContext.current
            val uiState by appHomePageViewModel.homePageUiState.collectAsState()
            /*    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.load))
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = LottieConstants.IterateForever,
                    isPlaying = uiState.pageLoading,
                    restartOnPlay = uiState.pageLoading
                )*/
            LaunchedEffect(key1 = true, block = {

                try {
                    shopsLandingPageViewModel.getShopCategories(
                        addShopViewModel = addShopViewModel,
                        appHomePageViewModel = appHomePageViewModel
                    )
                } catch (e: Exception) {
                    // Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                }

            })

            LaunchedEffect(key1 = true, block = {
                //try {
                appHomePageViewModel.getAllShops()
                appHomePageViewModel.getAllProducts()
                /*} catch (e: Exception) {
                    //     Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()

                }*/


            })





            AppHomePage(
                appHomePageViewModel = appHomePageViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                uiState = uiState,
                cartPageViewModel = cartPageViewModel,
                incomingJobViewModel = incomingJobViewModel,
                appViewModel = appViewModel
            )
            /*      LottieAnimation(
                      composition = composition,
                      progress = { progress },
                  )*/
        }

        composable(AppDestinations.SEARCH_SCREEN) {

            val context = LocalContext.current
            val uiState by appHomePageViewModel.homePageUiState.collectAsState()

            SearchScreen(
                appHomePageViewModel = appHomePageViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                uiState = uiState,
                cartPageViewModel = cartPageViewModel,
                appViewModel = appViewModel
            )

        }

        authenticationNavGraph(
            navController = navController, appViewModel = appViewModel,
            scanQRWaterViewModel = scanQRWaterViewModel,
            cartPageViewModel = cartPageViewModel,
            authenticationViewModel = authenticationViewModel,
            nearestWaterPointViewModel = nearestWaterPointViewModel,
            truckStartTripViewModel = truckStartTripViewModel,
            truckIncomingWorkViewModel = truckIncomingWorkViewModel
        )

        /*  shopsFrontNavGraph(
              navController = navController,
              appViewModel = appViewModel,
              checkoutViewModel = checkoutViewModel,
              onPlaceNameChanged = onPlaceNameChanged,
              openSearchmapdialog = openSearchmapdialog,
              suggestedLocales = suggestedLocales,
              start = start.value
          )*/

        composable(ShopsFrontDestination.SHOPS_HOME) {

            val move = remember {
                mutableStateOf(0)
            }
            val movee = remember {
                mutableStateOf(0)
            }

            /* LaunchedEffect(key1 = true, block = {
                 shopsLandingPageViewModel.getAllShops()
             })
 */
            ShopsLandingPage(
                shopsLandingPageViewModel = shopsLandingPageViewModel,
                appHomePageViewModel = appHomePageViewModel,
                addShopViewModel = addShopViewModel,
                cartPageViewModel = cartPageViewModel,
                shopHomePageViewModel = shopHomePageViewModel
            )
        }
        composable(ShopsFrontDestination.CATEGORY_SINGLE) {

            CategorySingle(
                appHomePageViewModel = appHomePageViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                cartPageViewModel = cartPageViewModel,
                shopsLandingPageViewModel = shopsLandingPageViewModel
            )
        }
        composable(AppDestinations.WATER_HOME) {
            WaterHome(
                cartPageViewModel = cartPageViewModel,
                waterHomeViewModel = waterHomeViewModel,
                tankerBoreholeViewModel = tankerBoreholeViewModel
            )
        }
        composable(AppDestinations.TRUCK_START_TRIP) {
            TruckStartTrip(
                cartPageViewModel = cartPageViewModel,
                truckStartTripViewModel = truckStartTripViewModel,
                nearestWaterPointViewModel = nearestWaterPointViewModel,
                truckIncomingWorkViewModel = truckIncomingWorkViewModel,
                scanQRWaterViewModel = scanQRWaterViewModel
            )
        }
        composable(AppDestinations.TRUCK_DASHBOARD) {
            TruckDash(
                cartPageViewModel = cartPageViewModel,
                truckDashViewModel = truckDashViewModel
            )
        }
        composable(AppDestinations.MY_TRUCK) {
            MyTrucks(
                cartPageViewModel = cartPageViewModel,
                myTrucksViewmodel = myTrucksViewmodel,
                waterVendorDashViewModel = waterVendorDashViewModel,
                truckOrdersViewModel = truckOrdersViewModel
            )
        }
        composable(AppDestinations.WATER_VENDOR_DASHBOARD) {
            WaterVendorDash(
                cartPageViewModel = cartPageViewModel,
                waterVendorDashViewModel = waterVendorDashViewModel,
                truckOrdersViewModel = truckOrdersViewModel
            )
        }
        composable(AppDestinations.ADD_WATER_DRIVER) {
            AddWaterDriver(
                cartPageViewModel = cartPageViewModel,
                addWaterDriverViewModel = addWaterDriverViewModel
            )
        }
        composable(AppDestinations.TANKER_BOREHOLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                TankerBorehole(
                    cartPageViewModel = cartPageViewModel,
                    deliveryTypeViewModel = deliveryTypeViewModel,
                    tankerBoreholeViewModel = tankerBoreholeViewModel,
                    onLocationChanged = openSearchmapdialog,
                    waterHomeViewModel = waterHomeViewModel,
                    nearestTrucksViewmodel = nearestTrucksViewmodel,
                    waterOrderDetailsViewModel = waterOrderDetailsViewModel,
                    pickImage = { choosePhoto("tankerBorehole", "profile") }
                )
            }
        }
        composable(AppDestinations.WATER_ORDER_PAY) {
            WaterOrderPay(
                cartPageViewModel = cartPageViewModel,
                waterOrderPayViewModel = waterOrderPayViewModel,
                tankerBoreholeViewModel = tankerBoreholeViewModel,
                waterOrderDetailsViewModel = waterOrderDetailsViewModel,
                checkoutViewModel = checkoutViewModel
            )
        }

        composable(AppDestinations.WATER_ORDER_DETAILS) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WaterOrderDetails(
                    cartPageViewModel = cartPageViewModel,
                    tankerBoreholeViewModel = tankerBoreholeViewModel,
                    waterOrderDetailsViewModel = waterOrderDetailsViewModel
                )
            }
        }
        composable(AppDestinations.ALLOCATING_TRUCKS) {
            AllocatingTruck(
                cartPageViewModel = cartPageViewModel,
                allocatingTruckViewModel = allocatingTruckViewModel,
                tankerBoreholeViewModel = tankerBoreholeViewModel
            )
        }
        composable(AppDestinations.WATER_TRANSACTION_COMPLETE) {
            WaterTransactionComplete(
                cartPageViewModel = cartPageViewModel,
                waterTransactionCompleteViewModel = waterTransactionCompleteViewModel,
                tankerBoreholeViewModel = tankerBoreholeViewModel,
                waterMyOrderViewModel = waterMyOrderViewModel
            )
        }
        composable(AppDestinations.WATER_MY_ORDER) {
            WaterMyOrder(
                cartPageViewModel = cartPageViewModel,
                waterMyOrderViewModel = waterMyOrderViewModel,
                waterTrackingViewModel = waterTrackingViewModel
            )
        }
        composable(AppDestinations.WATER_TRACKING) {
            WaterTracking(
                cartPageViewModel = cartPageViewModel,
                waterTrackingViewModel = waterTrackingViewModel,
                waterMyOrderViewModel = waterMyOrderViewModel,
                scanQrWaterViewModel = scanQRWaterViewModel
            )
        }

        composable(AppDestinations.SCAN_QR_WATER) {

            ScanQRWater(
                cartPageViewModel = cartPageViewModel,
                scanQRWaterViewModel = scanQRWaterViewModel,
                truckRouteWaterPointViewModel = truckWaterPointViewModel,
                authenticationViewModel = authenticationViewModel,
                truckStartTripViewModel = truckStartTripViewModel,
                nearestWaterPointViewModel = nearestWaterPointViewModel,
                truckIncomingWorkViewModel = truckIncomingWorkViewModel,
                scanQr = { scanQr(navController) }
            )
        }
        composable(AppDestinations.TRUCK_FIND_JOB) {
            TruckFindJob(
                cartPageViewModel = cartPageViewModel,
                truckFindJobViewModel = truckFindJobViewModel
            )
        }
        composable(AppDestinations.TRUCK_INCOMING_WORK) {
            TruckIncomingWork(
                cartPageViewModel = cartPageViewModel,
                truckIncomingWorkViewModel = truckIncomingWorkViewModel,
                waterOrderSingleViewModel = waterOrderSingleViewModel
            )
        }
        composable(AppDestinations.WATER_ORDER_SINGLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WaterOrderSingle(
                    cartPageViewModel = cartPageViewModel,
                    truckIncomingWorkViewModel = truckIncomingWorkViewModel,
                    nearestWaterPointViewModel = nearestWaterPointViewModel,
                    waterOrderSingleViewModel = waterOrderSingleViewModel,
                    truckRouteWaterPointViewModel = truckWaterPointViewModel,
                    truckRouteCustomerViewModel = truckCustomerViewModel
                )
            }
        }

        composable(AppDestinations.NEAREST_WATERPOINT) {
            NearestWaterPoint(
                cartPageViewModel = cartPageViewModel,
                nearestWaterPointViewModel = nearestWaterPointViewModel,
                truckRouteWaterPointViewModel = truckWaterPointViewModel,
                truckIncomingWorkViewModel = truckIncomingWorkViewModel
            )
        }
        composable(AppDestinations.NEAREST_TRUCKS) {
            NearestTrucks(
                cartPageViewModel = cartPageViewModel,
                nearestTrucksViewmodel = nearestTrucksViewmodel,
                tankerBoreholeViewModel = tankerBoreholeViewModel,
                waterOrderDetailsViewModel = waterOrderDetailsViewModel
            )
        }
        composable(AppDestinations.TRUCK_ROUTE_WATERPOINT) {
            TruckRouteWaterPoint(
                cartPageViewModel = cartPageViewModel,
                truckRouteWaterPointViewModel = truckWaterPointViewModel,
                nearestWaterPointViewModel = nearestWaterPointViewModel,
                truckStartTripViewModel = truckStartTripViewModel,
                truckIncomingWorkViewModel = truckIncomingWorkViewModel,
                scanQRWaterViewModel = scanQRWaterViewModel
            )
        }
        composable(AppDestinations.TRUCK_ROUTE_CUSTOMER) {
            TruckRouteCustomer(
                cartPageViewModel = cartPageViewModel,
                truckRouteCustomerViewModel = truckCustomerViewModel,
                truckIncomingWorkViewModel = truckIncomingWorkViewModel,
                truckStartTripViewModel = truckStartTripViewModel,
                scanQRWaterViewModel = scanQRWaterViewModel
            )
        }
        composable(AppDestinations.TRUCK_ORDER_COMPLETE) {
            TruckOrderComplete(
                cartPageViewModel = cartPageViewModel,
                truckRouteWaterPointViewModel = truckWaterPointViewModel,
                truckOrderCompleteViewModel = truckOrderCompleteViewModel
            )
        }
        composable(AppDestinations.WATERPOINT_ORDERS) {
            WaterpointOrders(
                waterpointOrdersViewModel = waterpointOrdersViewModel,
                cartPageViewModel = cartPageViewModel,
                scanQr = {
                    scanQr(navController)
                }
            )
        }

        composable(AppDestinations.SERVICE_PROVIDERS) {
            ServiceProviders(serviceProvidersViewModel = serviceProvidersViewModel)
        }
        composable(AppDestinations.WATER_SERVICE_PROVIDERS) {
            WaterServiceProviders(waterServiceProvidersViewModel = waterServiceProvidersViewModel)
        }
        composable(AppDestinations.WATERPOINT_DASH) {
            WaterPointDash(
                waterPointDashViewModel = waterPointDashViewModel,
                cartPageViewModel = cartPageViewModel
            )
        }
        composable(AppDestinations.WATERPOINT_NEW_ORDER) {
            WaterpointNewOrder(
                waterpointNewOrderViewModel = waterpointNewOrderViewModel,
                cartPageViewModel = cartPageViewModel
            )
        }
        composable(AppDestinations.TRUCK_ORDERS_HISTORY) {
            TruckOrders(
                cartPageViewModel = cartPageViewModel,
                truckOrdersViewModel = truckOrdersViewModel
            )
        }

        composable(ShopsFrontDestination.SHOPS_MAP) {
            val searchUiState by appHomePageViewModel.searchUiState.collectAsState()
            val cartUiState by cartPageViewModel.cartPageUiState.collectAsState()

            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    ShopSpecificSingleProduct(
                        navController = navController,
                        startLocale = start.value,
                        appHomePageViewModel = appHomePageViewModel,
                        shopHomePageViewModel = shopHomePageViewModel,
                        cartPageViewModel = cartPageViewModel,
                        searchUiState = searchUiState,
                        cartUiState = cartUiState,
                        appViewModel = appViewModel
                    )
                },
                pageLoading = searchUiState.pageLoading,
                actionLoading = searchUiState.actionLoading,
                errorList = searchUiState.errorList,
                messageList = searchUiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false,
                topBar = {
                    ShopNearbyHeader(
                        query = searchUiState.searchName,
                        cartSize = cartUiState.orderList.size,
                        appViewModel = appViewModel,
                        onCartButtonClicked = {
                            addShopViewModel.appViewModel?.onCartButtonClicked()
                        }
                    )
                }
            )

        }

        composable(ShopsFrontDestination.SINGLE_SHOP) {

            ShopHomePage(
                shopHomePageViewModel = shopHomePageViewModel,
                cartPageViewModel = cartPageViewModel,
                appHomePageViewModel = appHomePageViewModel,
                shopsLandingPageViewModel = shopsLandingPageViewModel
            )
        }

        composable(ShopsFrontDestination.SINGLE_PRODUCT) {


            LaunchedEffect(key1 = true, block = {
                shopHomePageViewModel.id.value?.let { it1 ->
                    Log.d("TAGSSS", "App: $it1")
                    productPageViewModel.getProductDetails(it1, shopHomePageViewModel)
                }
            })
            ProductPage(
                productPageViewModel = productPageViewModel,
                cartPageViewModel = cartPageViewModel,
                shopHomePageViewModel = shopHomePageViewModel
            )
        }

        composable(ShopsFrontDestination.SHOP_CATEGORIES) {
            val allShopCategoriesViewModel: AllShopCategoriesViewModel = hiltViewModel()
            if (allShopCategoriesViewModel.appViewModel == null) {
                allShopCategoriesViewModel.appViewModel = appViewModel
            }
            AllShopCategories(allShopCategoriesViewModel = allShopCategoriesViewModel)
        }

        composable(ShopsFrontDestination.SINGLE_CATEGORY) {
            val shopsByCategoryViewModel: ShopsByCategoryViewModel = hiltViewModel()
            if (shopsByCategoryViewModel.appViewModel == null) {
                shopsByCategoryViewModel.appViewModel = appViewModel
            }
            ShopsByCategory(shopsByCategoryViewModel = shopsByCategoryViewModel)
        }

        composable(ShopsFrontDestination.CART) {

            val context = LocalContext.current as ComponentActivity


            CartPage(
                cartPageViewModel = cartPageViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                trackYourOrderViewModel = trackYourOrderViewModel,
                checkoutViewModel = checkoutViewModel
            )
        }

        composable(ShopsFrontDestination.CUSTOMER_DASHBOARD) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CustomerDashboard(
                    customerDashboardViewModel = customerDashboardViewModel,
                    appViewModel = appViewModel,
                    shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                    customerOrderHistoryViewModel = customerOrderHistoryViewModel,
                    cartPageViewModel = cartPageViewModel,
                    appHomePageViewModel = appHomePageViewModel,
                    shopHomePageViewModel = shopHomePageViewModel,
                    onAddProfileLogo = {
                        choosePhoto("customerdashboard", "profile")
                    }
                )
            }
        }

        composable(ShopsFrontDestination.CUSTOMER_ADDRESS_BOOK) {
            val customerAddressBookViewModel: CustomerAddressBookViewModel = hiltViewModel()
            if (customerAddressBookViewModel.appViewModel == null) {
                customerAddressBookViewModel.appViewModel = appViewModel
            }
            CustomerAddressBook(customerAddressBookViewModel = customerAddressBookViewModel)
        }

        composable(ShopsFrontDestination.SINGLE_ADDRESS) {

            SingleAddress(
                singleAddressViewModel = singleAddressViewModel,
                onChangeLocationClicked = openSearchmapdialog,
                cartPageViewModel = cartPageViewModel,
                addShopViewModel = addShopViewModel
            )
        }

        composable(ShopsFrontDestination.ADD_DELIVERY_INFO) {
            //   val checkoutViewModel: CheckoutViewModel = hiltViewModel()
            if (checkoutViewModel.appViewModel == null) {
                checkoutViewModel.appViewModel = appViewModel
            }



            DeliverInformation(
                checkoutViewModel = checkoutViewModel,
                onPlaceNameChanged = onPlaceNameChanged,
                openSearchmapdialog = openSearchmapdialog,
                suggestedLocales = suggestedLocales,
                latLng = start.value,
                incomingJobViewModel = incomingJobViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                singleAddressViewModel = singleAddressViewModel,
                cartPageViewModel = cartPageViewModel,
                fetchLocaleDetails = fetchLocaleDetails,
                chooseLocation = openSearchmapdialog,
                appHomePageViewModel = appHomePageViewModel,
                trackYourOrderViewModel = trackYourOrderViewModel
            )
        }

        composable(ShopsFrontDestination.ORDER_PAYMENT) {

            OrderPaymentPage(
                checkoutViewModel = checkoutViewModel,
                navController = navController,
                shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                appViewModel = appViewModel,
                orderDetailsViewModel = orderDetailsViewModel,
                incomingJobViewModel = incomingJobViewModel,
                cartPageViewModel = cartPageViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                appHomePageViewModel = appHomePageViewModel
            )
        }
        composable(ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY) {
            val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    PaymentCompleteEtaDelivery(
                        navController = navController,
                        appViewModel = appViewModel,
                        cartPageViewModel = cartPageViewModel,
                        changeType = {
                            state.value = "user"
                        }
                    )
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = false,
                showCart = false
            )


        }
        composable(ShopsFrontDestination.TRACK_YOUR_ORDER) {
            val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    TrackYourOrder(
                        navController = navController,
                        startLocale = start.value,
                        shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                        trackYourOrderViewModel = trackYourOrderViewModel,
                        shopHomePageViewModel = shopHomePageViewModel,
                        state = state.value,
                        appViewModel = appViewModel
                    )
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(ShopsFrontDestination.RECEIPT) {
            val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    OrderReceiptContent(
                        cartPageViewModel = cartPageViewModel,
                        shopHomePageViewModel = shopHomePageViewModel,
                        checkoutViewModel = checkoutViewModel
                    )
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(ShopsFrontDestination.SCAN_QR_SHOP) {
            val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
            val appUiState = appViewModel.appUiState.collectAsState()
            val trackyourOrdersUiState by trackYourOrderViewModel.trackYourOrderUiStateUiState.collectAsState()

            AppScaffold(
                content = {
                    ScanQrShop(
                        navController = navController,
                        trackYourOrderUiState = trackyourOrdersUiState
                    )
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(ShopsFrontDestination.ORDER_COMPLETE) {
            val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    OrderComplete(
                        navigate = {
                            checkoutViewModel.setPageRoute(true)
//                        navController.navigate(AppDestinations.REVIEWRIDER)
//                        navController.navigate(AppDestinations.REVIEWSHOP)
                            navController.navigate(AppDestinations.REVIEWTHANKS)
                        },
                        waterMyOrderViewModel = waterMyOrderViewModel,
                        cartPageViewModel = cartPageViewModel,
                        appViewModel = appViewModel
                    )
                },
                pageLoading = false,
                actionLoading = false,
                errorList = listOf(),
                messageList = listOf(),
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showCart = false,
                showLogo = false,
                showImageRight = true
            )

            // val checkoutViewModel: CheckoutViewModel = hiltViewModel()

        }

        composable(ShopsFrontDestination.CUSTOMER_ORDER_HISTORY) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CustomerOrderHistory(
                    customerOrderHistoryViewModel = customerOrderHistoryViewModel,
                    shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                    trackYourOrderViewModel = trackYourOrderViewModel,
                    cartPageViewModel = cartPageViewModel,
                    appHomePageViewModel = appHomePageViewModel,
                    shopHomePageViewModel = shopHomePageViewModel,
                    waterMyOrderViewModel = waterMyOrderViewModel
                )
            }
        }
        composable(AppDestinations.CREATE_WATERVENDOR) {

            AddWaterVendorDetails(
                addWaterVendorViewModel = addWaterVendorViewModel,
                cartPageViewModel = cartPageViewModel
            )

        }
        composable(AppDestinations.WATERVENDOR_LOCALE) {

            start.value?.let { it1 ->
                AddWatervendorLocation(
                    addWaterVendorViewModel = addWaterVendorViewModel,
                    start = it1,
                    selectLocationViewModel = selectLocationViewModel,
                    onPlaceNameChanged = onPlaceNameChanged,
                    suggestedLocales = suggestedLocales,
                    navController = navController,
                    fetchLocaleDetails = fetchLocaleDetails,
                    openSearchPlaces = openSearchmapdialog,
                    cartPageViewModel = cartPageViewModel
                )
            }

        }
        composable(AppDestinations.WATERVENDOR_IMAGES) {

            AddWaterVendorImages(
                addWaterVendorViewModel = addWaterVendorViewModel,
                choosePhoto = {
                    choosePhoto("watervendor", it)
                },
                cartPageViewModel = cartPageViewModel,
                waterVendorDashViewModel = waterVendorDashViewModel
            )

        }
        composable(AppDestinations.CREATE_WATERTRUCK) {

            AddWatertruckDetails(
                addWaterTruckViewmodel = addWaterTruckViewmodel,
                cartPageViewModel = cartPageViewModel
            )

        }
        composable(AppDestinations.WATERTRUCK_LOCALE) {

            start.value?.let { it1 ->
                AddWatertruckLocation(
                    addWaterTruckViewmodel = addWaterTruckViewmodel,
                    start = it1,
                    selectLocationViewModel = selectLocationViewModel,
                    onPlaceNameChanged = onPlaceNameChanged,
                    suggestedLocales = suggestedLocales,
                    navController = navController,
                    fetchLocaleDetails = fetchLocaleDetails,
                    openSearchPlaces = openSearchmapdialog,
                    cartPageViewModel = cartPageViewModel
                )
            }

        }
        composable(AppDestinations.WATERTRUCK_IMAGES) {

            AddWaterTruckImages(addWaterTruckViewmodel = addWaterTruckViewmodel, choosePhoto = {
                choosePhoto("watertruck", it)
            }, cartPageViewModel = cartPageViewModel)

        }
        composable(AppDestinations.CREATE_WATERPOINT) {

            AddWaterpointDetails(
                addWaterpointViewmodel = addWaterpointViewmodel,
                cartPageViewModel = cartPageViewModel
            )

        }
        composable(AppDestinations.WATERPOINT_LOCATION) {

            start.value?.let { it1 ->
                AddWaterpointLocation(
                    addWaterpointViewmodel = addWaterpointViewmodel,
                    start = it1,
                    selectLocationViewModel = selectLocationViewModel,
                    onPlaceNameChanged = onPlaceNameChanged,
                    suggestedLocales = suggestedLocales,
                    navController = navController,
                    fetchLocaleDetails = fetchLocaleDetails,
                    openSearchPlaces = openSearchmapdialog,
                    cartPageViewModel = cartPageViewModel
                )
            }

        }

        composable(AppDestinations.WATERPOINT_IMAGES) {

            AddWaterpointImages(
                addWaterpointViewmodel = addWaterpointViewmodel,
                choosePhoto = {
                    choosePhoto("waterpoint", it)
                },
                cartPageViewModel = cartPageViewModel,
                waterPointDashViewModel = waterPointDashViewModel
            )

        }

        shopsBacksideNavGraph(
            navController = navController,
            appViewModel = appViewModel,
            choosePhoto = choosePhoto,
            addProductViewModel = addProductViewModel,
            addShopViewModel = addShopViewModel,
            shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
            orderDetailsViewModel = orderDetailsViewModel,
            shopHomePageViewModel = shopHomePageViewModel,
            appHomePageViewModel = appHomePageViewModel,
            productPageViewModel = productPageViewModel,
            cartPageViewModel = cartPageViewModel,
            changeType = {
                state.value = "shop"
            },
            trackYourOrderViewModel = trackYourOrderViewModel,
            clearImages = clearImages
        )
        composable(ShopsBacksideNavigation.ADD_SHOP_LOCATION) {
            val addShopBackStackEntry =
                remember { navController.getBackStackEntry(ShopsBacksideNavigation.ADD_SHOP) }

            start.value?.let { it1 ->
                AddShopLocation(
                    addShopViewModel = addShopViewModel,
                    start = it1,
                    selectLocationViewModel = selectLocationViewModel,
                    onPlaceNameChanged = onPlaceNameChanged,
                    suggestedLocales = suggestedLocales,
                    navController = navController,
                    fetchLocaleDetails = fetchLocaleDetails,
                    openSearchPlaces = openSearchmapdialog,
                    cartPageViewModel = cartPageViewModel
                )
            }
        }


        composable(ParcelDestination.PARCEL_MAINMODEL) {
            DropyMainModel(navController = navController)
        }
        composable(ParcelDestination.PARCEL_HOME) {
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    DropyMainParcels(
                        navController = navController,
                        startLocale = start.value,
                        suggestedLocales = suggestedLocales,
                        checkoutViewModel = checkoutViewModel,
                        onPlaceNameChanged = onPlaceNameChanged,
                        mainParcelViewModel = mainParcelViewModel
                    )
                },
                pageLoading = /*uiState.pageLoading*/false,
                actionLoading = /*uiState.actionLoading*/false,
                errorList = /*uiState.errorList*/listOf(),
                messageList = /*uiState.messageList*/listOf(),
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(ParcelDestination.PARCEL_RECIEVE) {
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    DropyReciever(navController = navController, startLocale = start.value)
                },
                pageLoading = /*uiState.pageLoading*/false,
                actionLoading = /*uiState.actionLoading*/false,
                errorList = /*uiState.errorList*/listOf(),
                messageList = /*uiState.messageList*/listOf(),
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )
        }
        composable(ParcelDestination.PARCEL_OPTION) {
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    DropyOptions(navController = navController, startLocale = start.value)
                },
                pageLoading = /*uiState.pageLoading*/false,
                actionLoading = /*uiState.actionLoading*/false,
                errorList = /*uiState.errorList*/listOf(),
                messageList = /*uiState.messageList*/listOf(),
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )


        }

        composable(ParcelDestination.PARCEL_EXPRESS) {
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    ExpressDeliveryContent(
                        navController = navController,
                        startLocale = start.value
                    )
                },
                pageLoading = /*uiState.pageLoading*/false,
                actionLoading = /*uiState.actionLoading*/false,
                errorList = /*uiState.errorList*/listOf(),
                messageList = /*uiState.messageList*/listOf(),
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )


        }
        composable(ParcelDestination.PARCEL_NEARME) {
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    start.value?.let { it1 ->
                        NearMe(
                            navController = navController,
                            startLocale = it1
                        )
                    }
                },
                pageLoading = /*uiState.pageLoading*/false,
                actionLoading = /*uiState.actionLoading*/false,
                errorList = /*uiState.errorList*/listOf(),
                messageList = /*uiState.messageList*/listOf(),
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )
        }

        composable(ParcelDestination.PARCEL_EXPRESS_SINGLE) {
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    SelectedExpressRider(
                        navController = navController,
                        route = ExpresRoute.value,
                        startLocale = start.value
                    )
                },
                pageLoading = /*uiState.pageLoading*/false,
                actionLoading = /*uiState.actionLoading*/false,
                errorList = /*uiState.errorList*/listOf(),
                messageList = /*uiState.messageList*/listOf(),
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }

        composable(ParcelDestination.PARCEL_PAY) {
            PayScreen(navController = navController)
        }
        composable(ParcelDestination.PARCEL_DROPY_PAY) {
            DropyPayment(navController = navController, route = PaymentRoute.value)
        }
        composable(ParcelDestination.PARCEL_PAYMENTTHANKS) {
            PaymentThanks(navController = navController)
        }
        composable(ParcelDestination.PARCEL_LOCATION_EXPRESS_RIDER) {
            LocationExpressRider(
                navController = navController,
                startLocale = start.value,
                appViewModel = appViewModel
            )
        }


        composable(ParcelDestination.PARCEL_SCANQR) {
            val uiState by checkoutViewModel.deliveryInformationUiState.collectAsState()
            Log.d("mdiodj", "App: ${uiState.hasmoreInfo}")
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    ScanQr(navController = navController, changeType = {
                        state.value = "shop"
                    }, trackYourOrderViewModel = trackYourOrderViewModel)
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(ParcelDestination.PARCEL_TRACK) {
            val uiState by checkoutViewModel.deliveryInformationUiState.collectAsState()
            Log.d("mdiodj", "App: ${uiState.hasmoreInfo}")
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    TrackParcel(navController = navController, startLocale = start.value)
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(AppDestinations.REVIEWRIDER) {

            val uiState by checkoutViewModel.deliveryInformationUiState.collectAsState()
            Log.d("mdiodj", "App: ${uiState.hasmoreInfo}")
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    ReviewRiderScreen(
                        navController = navController,
                        hasMoreInfo = uiState.hasmoreInfo,
                        cartPageViewModel = cartPageViewModel,
                        shopHomePageViewModel = shopHomePageViewModel,
                        appHomePageViewModel = appHomePageViewModel,
                        incomingJobViewModel = incomingJobViewModel,
                        type = incomingJobViewModel.route.value
                    )
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(AppDestinations.REVIEWTHANKS) {
            val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    ReviewThanksScreen(
                        navController = navController,
                        incomingJobViewModel = incomingJobViewModel
                    )
                },
                pageLoading = false,
                actionLoading = false,
                errorList = listOf(),
                messageList = listOf(),
                showBackButton = false,
                onBackButtonClicked = {/* addShopViewModel.appViewModel?.onBackButtonClicked()*/ },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = false,
                showCart = false,
                showImageRight = true,
                showLogo = false
            )

        }
        composable(AppDestinations.PARCELS) {
            AppScaffold(
                content = { ComingSoon() },
                onBackButtonClicked = { appViewModel.onBackButtonClicked() },
                onDashboardButtonClicked = { appViewModel.onDashboardButtonClicked() },
                onCartButtonClicked = { appViewModel.onCartButtonClicked() },
                navigateTo = { appViewModel.navigate(it) },
                drawerMenuItems = emptyList(),
                userProfiles = emptyList(),
                onSelectProfile = {},
                activeProfile = null,
            )
        }
        composable(AppDestinations.REVIEWSHOP) {
            val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
            val appUiState = appViewModel.appUiState.collectAsState()

            AppScaffold(
                content = {
                    ReviewShopScreen(
                        navController = navController,
                        cartPageViewModel = cartPageViewModel,
                        shopHomePageViewModel = shopHomePageViewModel,
                        appHomePageViewModel = appHomePageViewModel,
                        incomingJobViewModel = incomingJobViewModel
                    )
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = false,
                showCart = true,
                showLogo = false,
                showImageRight = true
            )

        }
        composable(AppDestinations.RIDES) {
            /*AppScaffold(
                content = { ComingSoon() },
                onBackButtonClicked = { appViewModel.onBackButtonClicked() },
                onDashboardButtonClicked = { appViewModel.onDashboardButtonClicked() },
                onCartButtonClicked = { appViewModel.onCartButtonClicked() },
                navigateTo = { appViewModel.navigate(it) },
                drawerMenuItems = emptyList(),
                userProfiles = emptyList(),
                onSelectProfile = {},
                activeProfile = null
            )*/
            //      RiderDash(navController = navController)
            MainRide(navController = navController, startLocale = start.value)
        }
        composable(RiderDestination.RIDEOPTIONS) {

            RideOptions(navController = navController, changeExpressRoute = {
                ExpresRoute.value = "options"
            }, startLocale = start.value)
        }

        composable(RiderDestination.RIDERPICKUP) {

            RiderPickup(navController = navController, startLocale = start.value)
        }

        composable(RiderDestination.DROPYHERE) {

            DropyHere(navController = navController, startLocale = start.value)
        }
        composable(RiderDestination.ARRIVEDPAY) {

            ArrivedPay(navController = navController, changeType = {
                PaymentRoute.value = "review"
            }, startLocale = start.value)
        }

        composable(RiderDestination.RIDERDASHBOARD) {

            RiderDash(navController = navController)
        }
        composable(RiderDestination.RIDERJOBSEARCH) {

            start.value?.let { it1 -> JobSearch(start = it1, navController = navController) }
        }
        composable(RiderDestination.RIDERINCOMINGORDERS) {
            val appUiState = appViewModel.appUiState.collectAsState()
            val uiState by incomingJobViewModel.incomingJobsUiState.collectAsState()
            val shopHomeuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()



            AppScaffold(
                content = {

                    IncomingJobContent(
                        navController = navController,
                        incomingJobViewModel = incomingJobViewModel,
                        shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                        uiState = uiState,
                        appViewModel = appViewModel,
                        appHomePageViewModel = appHomePageViewModel
                    )

                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )

        }
        composable(RiderDestination.RIDERPICKCUSTOMER) {
            val appUiState = appViewModel.appUiState.collectAsState()
            val uiState by incomingJobViewModel.pickCustomerUiState.collectAsState()
            val incomingJobuiState by incomingJobViewModel.incomingJobsUiState.collectAsState()
            val appuiState by appViewModel.appUiState.collectAsState()


            AppScaffold(
                content = {
                    PickCustomer(
                        scanQr = {

                            scanQr(navController)
                        },
                        useCode = {
                            pinState.value = "scanQr"
                            appViewModel.navigate(AppDestinations.PIN)
                        },
                        incomingJobViewModel = incomingJobViewModel,
                        uiState = uiState,
                        appuiState = appuiState,
                        type = uiState.section,
                        incomingJobUiState = incomingJobuiState
                    )
                },
                pageLoading = uiState.pageLoading,
                actionLoading = uiState.actionLoading,
                errorList = uiState.errorList,
                messageList = uiState.messageList,
                onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
                onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
                onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
                navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
                drawerMenuItems = appUiState.value.drawerMenuItems,
                userProfiles = appUiState.value.userProfiles,
                onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
                activeProfile = appUiState.value.activeProfile,
                showimg = true,
                showCart = false
            )


        }

        composable(RiderBackside.ADDRIDER) {

            AddRider(
                appViewModel = appViewModel,
                addRiderViewModel = addRiderViewModel,
                becomeRiderViewModel = becomeRiderViewModel,
                choosePlace = openSearchmapdialog
            )
        }
        composable(RiderBackside.RIDERLOCATION) {

            AddRiderLocation(
                appViewModel = appViewModel,
                addRiderViewModel = addRiderViewModel,
                openSearchPlaces = openSearchmapdialog
            )
        }
        composable(RiderBackside.RIDERIMAGE) {

            AddRiderImage(
                appViewModel = appViewModel,
                addRiderViewModel = addRiderViewModel,
                choosePhoto = {
                    choosePhoto("rider", it)
                })
        }
        composable(AppDestinations.BALANCE) {

            BalanceScreen()
        }
        /*     composable(AppDestinations.REVIEWRIDER) {

                 ReviewRiderScreen(navController = navController)
             }*/
        /*  composable(AppDestinations.REVIEWTHANKS) {

              ReviewThanksScreen()
          }*/
    }

}