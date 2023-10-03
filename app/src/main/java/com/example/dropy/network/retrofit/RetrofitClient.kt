package com.example.dropy.network.retrofit

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private const val BASE_URL = "https://dropyapi.herokuapp.com/"


    //instance of the marketplace API
    val instance: DropyApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(DropyApi::class.java)
    }
}