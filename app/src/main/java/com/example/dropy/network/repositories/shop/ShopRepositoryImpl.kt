package com.example.dropy.network.repositories.shop

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.domain.gerResponseErrorShopQr
import com.example.dropy.network.models.addproduct.AddProductRes
import com.example.dropy.network.models.addproductcategory.AddProductCategoryRes
import com.example.dropy.network.models.addshop.AddShopResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.deleteproductcategory.DeleteProductCategoryResponse
import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.services.*
import com.example.dropy.network.services.shops.ShopsBackendService
import com.example.dropy.network.services.shops.ShopsFrontService
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ShopRepositoryImpl(
    private val shopsFrontService: ShopsFrontService,
    private val shopsBackendService: ShopsBackendService,
):ShopRepository {



}