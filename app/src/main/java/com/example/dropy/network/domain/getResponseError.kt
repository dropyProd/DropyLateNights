package com.example.dropy.domain


import com.example.dropy.network.models.commondataclasses.ErrorResponseDataClass
import com.example.dropy.network.models.commondataclasses.ErrorShopQrRespone
import com.example.dropy.network.models.commondataclasses.RiderIncomingJobErrorResponse
import com.example.dropy.network.models.commondataclasses.VerifyQrErrorResponse
import com.example.dropy.network.models.errors.creatinguser.ErrorCreatingUserResponse
import com.google.gson.Gson
import retrofit2.Response

fun gerResponseError(response: String): ErrorResponseDataClass {

    val gson = Gson()
    return gson.fromJson(response, ErrorResponseDataClass::class.java)
}

fun gerResponseErrorCreatingUser(response: String): ErrorCreatingUserResponse {

    val gson = Gson()
    return gson.fromJson(response, ErrorCreatingUserResponse::class.java)
}
data class ErrorAcceptingJob(
    val error: String? = null,
    val status: Int? = null,
)
fun gerResponseErrorAcceptingJob(response: String): ErrorAcceptingJob {

    val gson = Gson()
    return gson.fromJson(response, ErrorAcceptingJob::class.java)
}
fun gerResponseErrorShopQr(response: String): ErrorShopQrRespone {

    val gson = Gson()
    return gson.fromJson(response, ErrorShopQrRespone::class.java)
}
fun gerResponseErrorSRiderjobs(response: String): RiderIncomingJobErrorResponse {

    val gson = Gson()
    return gson.fromJson(response, RiderIncomingJobErrorResponse::class.java)
}
fun gerResponseErrorSVerifyQr(response: String): VerifyQrErrorResponse {

    val gson = Gson()
    return gson.fromJson(response, VerifyQrErrorResponse::class.java)
}