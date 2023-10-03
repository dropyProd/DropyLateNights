package com.example.dropy.ui.app.navigation.shopsnavigation

import android.util.Log
import android.widget.Toast
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.OrderReady
import com.example.dropy.ui.screens.OrderReadyViewModel
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.dropymainmodel.DropyMainParcels
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProduct
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProductViewModel
import com.example.dropy.ui.screens.shops.backside.addproduct.OptionsDialog
import com.example.dropy.ui.screens.shops.backside.addproduct.ProductNameDialog
import com.example.dropy.ui.screens.shops.backside.addshop.AddShop
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.ShopUploads
import com.example.dropy.ui.screens.shops.backside.addshop.addshopimages.AddShopImages
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetails
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.productdetails.ProductDetails
import com.example.dropy.ui.screens.shops.backside.productdetails.ProductDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.shopdashboard.ShopDashboard
import com.example.dropy.ui.screens.shops.backside.shopdashboard.ShopDashboardViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrders
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.backside.shoporderhistory.ShopOrderHistory
import com.example.dropy.ui.screens.shops.backside.shoporderhistory.ShopOrderHistoryViewModel
import com.example.dropy.ui.screens.shops.backside.shopproducts.ShopProducts
import com.example.dropy.ui.screens.shops.backside.shopproducts.ShopProductsViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.productpage.ProductPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


object ShopsBacksideNavigation {
    const val SHOP_DASHBOARD = "shopDashboard"
    const val INCOMING_ORDERS = "shopIncomingOrders"
    const val ORDER_HISTORY = "shopOrderHistory"
    const val ORDER_DETAILS = "orderDetails"
    const val ORDER_READY = "orderReady"
    const val SHOP_PRODUCTS = "shopProducts"
    const val ADD_PRODUCT = "addProduct"
    const val PRODUCT_DETAILS = "productDetails"
    const val ADD_SHOP = "addShop"
    const val SHOP_UPLOADS = "shopUploads"
    const val ADD_SHOP_LOCATION = "addShopLocation"

}


fun NavGraphBuilder.shopsBacksideNavGraph(
    navController: NavController,
    appViewModel: AppViewModel,
    choosePhoto: (section: String, type: String) -> Unit,
    addProductViewModel: AddProductViewModel,
    addShopViewModel: AddShopViewModel,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    orderDetailsViewModel: OrderDetailsViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    productPageViewModel: ProductPageViewModel,
    cartPageViewModel: CartPageViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    changeType: () -> Unit,
    clearImages: () -> Unit,
) {
    navigation(
        startDestination = ShopsBacksideNavigation.SHOP_DASHBOARD,
        route = AppDestinations.SHOPS_BACK
    ) {

        val route =
            mutableStateOf("")

        val id = mutableStateOf(0)
        val numberInStack = mutableStateOf(0)

        composable(ShopsBacksideNavigation.ADD_SHOP) {

            AddShop(addShopViewModel = addShopViewModel, cartPageViewModel = cartPageViewModel)
        }

        composable(ShopsBacksideNavigation.SHOP_UPLOADS) {

            /*  ShopUploads(addShopViewModel = addShopViewModel)*/
            AddShopImages(addShopViewModel = addShopViewModel, choosePhoto = {
                choosePhoto("shop", it)
            }, cartPageViewModel = cartPageViewModel)
            val uiState by addShopViewModel.addShopUiState.collectAsState()

            ProductNameDialog(
                show = addShopViewModel.showProductCategoryDialog.value,
                dismiss = {
                    addShopViewModel.dismissDialog()
                    route.value = "createshop"
                    //  addProductViewModel.clearValues()
                    appViewModel.navigate(ShopsBacksideNavigation.ADD_PRODUCT)
                },
                addProductViewModel = addProductViewModel,
                addShopViewModel = addShopViewModel,
                addProductCategory = {
                    addProductViewModel.addProductCategory(
                        appHomePageViewModel.homePageUiState.value.popularShops.toMutableList(),
                        uiState.shopName
                    )
                },
                appHomePageViewModel = appHomePageViewModel,
                appViewModel = appViewModel
            )
        }

        composable(ShopsBacksideNavigation.SHOP_DASHBOARD) {
            val shopDashboardViewModel: ShopDashboardViewModel = hiltViewModel()
            if (shopDashboardViewModel.appViewModel == null) {
                shopDashboardViewModel.appViewModel = appViewModel
            }

            ShopDashboard(
                shopDashboardViewModel = shopDashboardViewModel,
                shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                appViewModel = appViewModel
            )
        }

        composable(ShopsBacksideNavigation.INCOMING_ORDERS) {

            val context = LocalContext.current

            LaunchedEffect(key1 = true, block = {
                /* appViewModel.appUiState.value.activeProfile?.id?.let {
                     shopIncomingOrdersViewModel.getPendingOrders(it)
                 }*/
                shopIncomingOrdersViewModel.getPendingOrders(context)
            })

            ShopIncomingOrders(
                shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                orderDetailsViewModel = orderDetailsViewModel,
                trackYourOrderViewModel = trackYourOrderViewModel
            )
        }

        composable(ShopsBacksideNavigation.ORDER_HISTORY) {
            val shopOrderHistoryViewModel: ShopOrderHistoryViewModel = hiltViewModel()
            if (shopOrderHistoryViewModel.appViewModel == null) {
                shopOrderHistoryViewModel.appViewModel = appViewModel
            }
            LaunchedEffect(key1 = true, block = {
                appViewModel.appUiState.value.activeProfile?.id?.let {
                    shopIncomingOrdersViewModel.getCompletedOrders(it)
                }
            })
            ShopOrderHistory(
                shopOrderHistoryViewModel = shopOrderHistoryViewModel,
                shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                orderDetailsViewModel = orderDetailsViewModel,
                changeType = changeType,
                trackYourOrderViewModel = trackYourOrderViewModel,
                shopHomePageViewModel = shopHomePageViewModel
            )
        }

        composable(ShopsBacksideNavigation.SHOP_PRODUCTS) {
            val shopProductsViewModel: ShopProductsViewModel = hiltViewModel()
            if (shopProductsViewModel.appViewModel == null) {
                shopProductsViewModel.appViewModel = appViewModel
            }

            val uiState by addShopViewModel.addShopUiState.collectAsState()
            val context = LocalContext.current


            LaunchedEffect(key1 = true, block = {
                if (appViewModel.appUiState.value.activeProfile?.type.equals(ProfileTypes.CUSTOMER)) {
                    appHomePageViewModel.homePageUiState.value.popularShops.forEach {
                        if (uiState.shopName.equals(it.shop_name)) {
                            it.id?.let { it1 ->
                                //  shopHomePageViewModel.settempshopId(it1)
                                shopHomePageViewModel.getGetShopProducts(
                                    it1
                                )

                                /* appViewModel.appUiState.value.userProfiles.forEach {
                                     Toast.makeText(context, "${shopHomePageViewModel.tempshopid.value}     ${it.id}", Toast.LENGTH_SHORT).show()
                                     if (it.id.equals(shopHomePageViewModel.tempshopid.value)) {
                                         appViewModel.onSelectProfile(it)
                                     }
                                 }*/

                            }
                        }
                    }
                } else {
                    appViewModel.appUiState.value.activeProfile?.id?.let { it1 ->
                        shopHomePageViewModel.getGetShopProducts(
                            it1.toString()
                        )
                    }
                }
            })


            val scope = rememberCoroutineScope()
            ShopProducts(
                shopProductsViewModel = shopProductsViewModel,
                shopHomePageViewModel = shopHomePageViewModel,
                addProductViewModel = addProductViewModel,
                appViewModel = appViewModel,
                addShopViewModel = addShopViewModel,
                moveAddProduct = {
                    scope.launch {
                        route.value = "editproduct"
                        id.value = it.id!!
                        it.number_in_stack?.let { it1 -> addProductViewModel.setNumberStock(it1.toString()) }
                        appViewModel.appUiState.value.activeProfile?.id?.let { id ->
                            addProductViewModel.setId(
                                id.toString()
                            )
                        }

                        val res =
                            productPageViewModel.getProductDetails(id.value, shopHomePageViewModel)
                        Log.d("hujuj", "shopsBacksideNavGraph: $res")
                        if (res != null) {
                            addProductViewModel.setValues(res, shopHomePageViewModel)

                            for (i in 0..3) {
                                if (i == 1) {
                                    addProductViewModel.getShopProductCategories()

                                } else if (i == 2) {
                                    shopProductsViewModel.onAddProductClicked()
                                }
                                delay(1200)
                            }

                        }

                    }
                },
                cartPageViewModel = cartPageViewModel
            )

            ProductNameDialog(
                show = addShopViewModel.showProductCategoryDialog.value,
                dismiss = {
                    addShopViewModel.dismissDialog()
                    route.value = ""
                    addProductViewModel.clearValues()
                    shopProductsViewModel.onAddProductClicked()
                },
                dismissme = {
                    addShopViewModel.dismissDialog()
                    route.value = ""
                    // addProductViewModel.clearValues()
                    appViewModel.navigate(ShopsBacksideNavigation.SHOP_PRODUCTS)
                },
                addProductViewModel = addProductViewModel,
                addShopViewModel = addShopViewModel,
                addProductCategory = {
                    if (appViewModel.appUiState.value.activeProfile?.type.equals(ProfileTypes.CUSTOMER)) {
                        addProductViewModel.addProductCategory(
                            appHomePageViewModel.homePageUiState.value.popularShops.toMutableList(),
                            uiState.shopName
                        )
                    } else {
                        appViewModel.appUiState.value.activeProfile?.name?.let { it1 ->
                            addProductViewModel.addProductCategory(
                                appHomePageViewModel.homePageUiState.value.popularShops.toMutableList(),
                                it1
                            )
                        }
                    }
                },
                appHomePageViewModel = appHomePageViewModel,
                appViewModel = appViewModel
            )


            OptionsDialog(
                show = addShopViewModel.showOptionsDialog.value,
                appViewModel = appViewModel,
                addShopViewModel = addShopViewModel,
                dismissme = {
                    addShopViewModel.dismissOptionsDialog()
                }, changeType = {
                    addProductViewModel.clearValues()
                    route.value = ""
                    addShopViewModel.dismissOptionsDialog()
                })
        }

        composable(ShopsBacksideNavigation.PRODUCT_DETAILS) {
            val productsDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
            if (productsDetailsViewModel.appViewModel == null) {
                productsDetailsViewModel.appViewModel = appViewModel
            }
            ProductDetails(productDetailsViewModel = productsDetailsViewModel)
        }

        composable(ShopsBacksideNavigation.ORDER_DETAILS) {
            val context = LocalContext.current
            LaunchedEffect(key1 = true, block = {
//                orderDetailsViewModel.getOrderDetails()
                shopHomePageViewModel.getCartItemNew(cartPageViewModel, context = context)
                orderDetailsViewModel.setIncomingCartItems()
            })
            OrderDetails(
                orderDetailsViewModel = orderDetailsViewModel,
                shopIncomingOrdersViewModel = shopIncomingOrdersViewModel
            )
        }

        composable(ShopsBacksideNavigation.ORDER_READY) {
            val appUiState = appViewModel.appUiState.collectAsState()
            val orderReadyViewModel: OrderReadyViewModel = hiltViewModel()
            AppScaffold(
                content = {
                    OrderReady(
                        startClicked = orderReadyViewModel::startTimer,
                        onMinutesChanged = orderReadyViewModel::setMinutes,
                        orderReadyViewModel = orderReadyViewModel
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

        composable(ShopsBacksideNavigation.ADD_PRODUCT) {
            //  val addProductViewModel: AddProductViewModel = hiltViewModel()
            if (addProductViewModel.appViewModel == null) {
                addProductViewModel.appViewModel = appViewModel
            }


            LaunchedEffect(key1 = true, block = {
                //   if (addProductViewModel.refresh.value) addProductViewModel.getShopProductCategories()
                appHomePageViewModel.getAllShops()
            })
            if (route.value.equals("editproduct")) {
                /* LaunchedEffect(key1 = true, block = {
                     val res =
                         productPageViewModel.getProductDetails(id.value, shopHomePageViewModel)
                     Log.d("hujuj", "shopsBacksideNavGraph: $res")
                     if (res != null) {
                         addProductViewModel.setValues(res, shopHomePageViewModel)
                     }
                 })*/
            } else {

            }
            AddProduct(
                addProductViewModel = addProductViewModel,
                choosePhoto = {
                    choosePhoto("product", it)
                },
                route = route.value,
                clearImages = clearImages,
                shopHomePageViewModel = shopHomePageViewModel,
                appHomePageViewModel = appHomePageViewModel,
                addShopViewModel = addShopViewModel
            )
        }

    }
}