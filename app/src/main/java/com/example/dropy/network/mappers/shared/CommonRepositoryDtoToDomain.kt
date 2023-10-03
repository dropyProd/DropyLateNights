package com.example.dropy.network.mappers.shared

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponse
import com.example.dropy.network.models.directions.DirectionRespnse

internal fun DirectionRespnse.toDomain(): DirectionRespnse {
    return DirectionRespnse(
        geocoded_waypoints = geocoded_waypoints, routes = routes, status = status
    )
}
internal fun DeliveryMethodResponse.toDomain(): DeliveryMethodResponse {
    return DeliveryMethodResponse()
}