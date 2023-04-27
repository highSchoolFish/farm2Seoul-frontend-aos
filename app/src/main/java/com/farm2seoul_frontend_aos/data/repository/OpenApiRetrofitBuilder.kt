package com.farm2seoul_frontend_aos.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenApiRetrofitBuilder {
    //BASE URL
    private const val BASE_URL = "http://openAPI.seoul.go.kr:8088/"

    fun create(): OpenApiRetrofitInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenApiRetrofitInterface::class.java)
    }
}