package com.farm2seoul_frontend_aos.data.repository

import com.farm2seoul_frontend_aos.data.model.ThisWeekResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Farm2SeoulRetrofitInterface {

    @GET("/api/v1/auctions/this-week")
    suspend fun getThisWeek(
        @Query("name") name: String,
        @Query("grade") grade: String,
        @Query("quantity") quantity: String,
        @Query("unit") unit: String
    ): List<ThisWeekResponse>
}