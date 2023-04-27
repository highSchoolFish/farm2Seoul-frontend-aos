package com.farm2seoul_frontend_aos.data.repository

import com.farm2seoul_frontend_aos.data.model.GarakGradePriceResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {

    @GET("/766c476c676b6e793132345770716c57/json/GarakGradePrice/{start_index}/{end_index}/{pum_name}")
    suspend fun getGarakGradePrice(
        @Path("start_index") start_index: String,
        @Path("end_index") end_index: String,
        @Path("pum_name") pum_name: String
    ): GarakGradePriceResponse
}