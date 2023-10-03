package com.example.dropy.network.mappers.authentication

import com.example.dropy.network.models.checkuser.CheckUserResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.createUser.CreateUserResponse

internal fun CheckUserResponse.toDomain(): CheckUserResponse {
    return CheckUserResponse(
        resultCode = resultCode, userExists = userExists
    )
}

internal fun CreateUserResponse.toDomain(): CreateUserResponse {
    return CreateUserResponse(
        resultCode = resultCode, message = message
    )
}