package com.example.dropy.network.mappers.commons

import com.example.dropy.network.models.addproduct.AddProductRes
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass

internal fun ActionResultDataClass.toDomain(): ActionResultDataClass {
    return ActionResultDataClass(
        message = message, resultCode = resultCode
    )
}