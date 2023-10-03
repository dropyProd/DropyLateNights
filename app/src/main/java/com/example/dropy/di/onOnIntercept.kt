package com.example.dropy.di

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


@Throws(IOException::class)
private fun onOnIntercept(chain: Interceptor.Chain): Response? {
/*    try {
        val response: Response = chain.proceed(chain.request())
        val content: String = UtilityMethods.convertResponseToString(response)
        Log.d(TAG, lastCalledMethodName.toString() + " - " + content)
        return response.newBuilder().body(create(response.body().contentType(), content)).build()
    } catch (exception: SocketTimeoutException) {
        exception.printStackTrace()
        if (listener != null) listener.onConnectionTimeout()
    }
    return chain.proceed(chain.request())*/
    return null
}