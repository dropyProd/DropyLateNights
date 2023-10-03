package com.example.dropy.network.mappers.shops.frontside

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.customerorders.CustomerOrdersResponse
import com.example.dropy.network.models.customerqr.CustomerQrResponse
import com.example.dropy.network.models.favouriteshop.favouriteShopRes.FavouriteShopResponse
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.models.shops.ShopsResponse

internal fun ShopsResponse.toDomain(): ShopsResponse {
    return ShopsResponse(
        shops = shops
    )
}

internal fun ShopCategoriesResponse.toDomain(): ShopCategoriesResponse {
    return ShopCategoriesResponse()
}

internal fun ProductDetailsResponse.toDomain(): ProductDetailsResponse {
    return ProductDetailsResponse(
        id = id,
        product_category = product_category,
        product_description = product_description,
        product_images = product_images,
        productMainImage = productMainImage,
        product_name = product_name,
        product_price = product_price,
        shopshop_idId = shopshop_idId
    )
}

internal fun ShopDetailsResponse.toDomain(): ShopDetailsResponse {
    return ShopDetailsResponse(
        id = id,
        number_0f_followers = number_0f_followers,
        number_of_orders = number_of_orders,
        phone_number = phone_number,
        product_categories = product_categories,
        products = products,
        shop_cover_photo_url = shop_cover_photo_url,
        shop_location = shop_location,
        shop_logo_url = shop_logo_url,
        shop_name = shop_name
    )
}

internal fun ActionResultDataClass.toDomain(): ActionResultDataClass {
    return ActionResultDataClass(
        message = message, resultCode = resultCode
    )
}

internal fun FavouriteShopResponse.toDomain(): FavouriteShopResponse {
    return FavouriteShopResponse(
        data = data, status = status
    )
}
internal fun CustomerQrResponse.toDomain(): CustomerQrResponse {
    return CustomerQrResponse(
        encoded_customer_id = encoded_customer_id,
        encoded_order_number = encoded_order_number,
        statuscode = statuscode
    )
}

internal fun CustomerOrdersResponse.toDomain(): CustomerOrdersResponse {
    return CustomerOrdersResponse(
        response = response, status = status
    )
}