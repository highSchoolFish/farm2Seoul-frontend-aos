package com.farm2seoul_frontend_aos.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Farm2SeoulRetrofitBuilder {
    //BASE URL
    private const val BASE_URL = "http://high-school-fish.com:8081/"

    fun create(): Farm2SeoulRetrofitInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Farm2SeoulRetrofitInterface::class.java)
    }
}