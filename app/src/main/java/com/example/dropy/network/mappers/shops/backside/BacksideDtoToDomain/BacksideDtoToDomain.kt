package com.example.dropy.network.mappers.shops.backside.BacksideDtoToDomain

import com.example.dropy.network.models.addproduct.AddProductRes
import com.example.dropy.network.models.addproductcategory.AddProductCategoryRes
import com.example.dropy.network.models.addshop.AddShopResponse
import com.example.dropy.network.models.completedorders.CompletedOrdersResponse
import com.example.dropy.network.models.deleteproductcategory.DeleteProductCategoryResponse
import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.pendingOrders.ShopPendingOrders
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.network.models.shops.ShopsResponse

internal fun ShopQrResponse.toDomain(): ShopQrResponse {
    return ShopQrResponse(
        statuscode = statuscode, unique_delivery_id = unique_delivery_id
    )
}

internal fun GetShopProductCategoriesResponse.toDomain(): GetShopProductCategoriesResponse {
    return GetShopProductCategoriesResponse(
        product_categories = product_categories
    )
}

internal fun ShopProductCategoriesResponse.toDomain(): ShopProductCategoriesResponse {
    return ShopProductCategoriesResponse(
    )
}

internal fun AddProductCategoryRes.toDomain(): AddProductCategoryRes {
    return AddProductCategoryRes(
        message = message, resultCode = resultCode
    )
}

internal fun AddShopResponse.toDomain(): AddShopResponse {
    return AddShopResponse(
        message = message, resultCode = resultCode
    )
}

internal fun AddProductRes.toDomain(): AddProductRes {
    return AddProductRes(
        message = message, resultCode = resultCode
    )
}

internal fun DeleteProductCategoryResponse.toDomain(): DeleteProductCategoryResponse {
    return DeleteProductCategoryResponse(
        message = message, resultCode = resultCode
    )
}

internal fun ShopPendingOrders.toDomain(): ShopPendingOrders {
    return ShopPendingOrders(
        pending_orders = pending_orders
    )
}

internal fun CompletedOrdersResponse.toDomain(): CompletedOrdersResponse {
    return CompletedOrdersResponse(
        done_orders = done_orders
    )
}

