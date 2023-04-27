package com.farm2seoul_frontend_aos.data.repository

import com.farm2seoul_frontend_aos.data.model.Recent3MonthResponse
import com.farm2seoul_frontend_aos.data.model.Recent4WeekResponse
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

    @GET("/api/v1/auctions/unit/average-prices/last-4-weeks")
    suspend fun getRecent4Week(
        @Query("name") name: String,
        @Query("grade") grade: String,
        @Query("quantity") quantity: String,
        @Query("unit") unit: String
    ): List<Recent4WeekResponse>

    @GET("/api/v1/auctions/average-prices/last-3-month")
    suspend fun getRecent3Month(
        @Query("name") name: String,
        @Query("grade") grade: String,
        @Query("quantity") quantity: String,
        @Query("unit") unit: String
    ): List<Recent3MonthResponse>
}