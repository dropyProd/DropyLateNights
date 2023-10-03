package com.example.dropy.network.mappers.cart

import com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails.Order
import com.example.dropy.network.models.PostCartResponse.PostCartResponse
import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass

internal fun GetCartResponse.toDomain(): GetCartResponse {
    return GetCartResponse(
    )
}

internal fun PostCartResponse.toDomain(): PostCartResponse {
    return PostCartResponse(
        message = message, resultCode = resultCode
    )
}

internal fun Order.toDomain(): Order {
    return Order(
        order_details = order_details
    )
}