package com.example.dropy.ui.app.navigation.shopsnavigation

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.screens.shops.frontside.allshopcategories.AllShopCategories
import com.example.dropy.ui.screens.shops.frontside.allshopcategories.AllShopCategoriesViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPage
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CompleteEtaDelivery.PaymentCompleteEtaDelivery
import com.example.dropy.ui.screens.shops.frontside.checkout.deliveryinformation.DeliverInformation
import com.example.dropy.ui.screens.shops.frontside.checkout.ordercomplete.OrderComplete
import com.example.dropy.ui.screens.shops.frontside.checkout.payment.OrderPaymentPage
import com.example.dropy.ui.screens.shops.frontside.checkout.scanQr.ScanQrShop
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrder
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
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.collectLatest

object ShopsFrontDestination {
    const val SHOPS_HOME = "shopsHome"
    const val SHOPS_MAP = "shopsMap"
    const val CATEGORY_SINGLE = "categorySingle"
    const val SHOP_CATEGORIES = "allShopCategories"
    const val SINGLE_CATEGORY = "singleCategory/{categoryId}"
    const val SINGLE_SHOP = "singleShop/{shopId}"
    const val SINGLE_PRODUCT = "singleProduct/{productId}"
    const val CART = "cart"
    const val CUSTOMER_DASHBOARD = "customerDashboard"
    const val CUSTOMER_ADDRESS_BOOK = "customerAddressBook"
    const val SINGLE_ADDRESS = "singleAddress"
    const val ADD_DELIVERY_INFO = "addDeliveryInfo"
    const val ORDER_PAYMENT = "orderPayment"
    const val PAYMENT_COMPLETE_ETA_DELIVERY = "paymentcompleteetadelivery"
    const val TRACK_YOUR_ORDER = "trackyourOrder"
    const val RECEIPT = "receipt"
    const val SCAN_QR_SHOP = "scanqrshop"
    const val ORDER_COMPLETE = "ordercomplete"
    const val CUSTOMER_ORDER_HISTORY = "customerOrderHistory"
}

fun NavGraphBuilder.shopsFrontNavGraph(
    navController: NavController,
    appViewModel: AppViewModel,
    checkoutViewModel: CheckoutViewModel,
    onPlaceNameChanged: (String) -> Unit,
    openSearchmapdialog: () -> Unit,
    suggestedLocales: List<AddressDataClass>,
    start: LatLng?
) {
    navigation(
        startDestination = ShopsFrontDestination.SHOPS_HOME,
        route = AppDestinations.SHOPS_FRONT
    ) {

    }
}
