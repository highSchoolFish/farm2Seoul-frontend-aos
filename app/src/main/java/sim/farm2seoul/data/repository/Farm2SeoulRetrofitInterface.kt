package sim.farm2seoul.data.repository

import sim.farm2seoul.data.model.Recent3MonthResponse
import sim.farm2seoul.data.model.Recent4WeekResponse
import sim.farm2seoul.data.model.ThisWeekResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Farm2SeoulRetrofitInterface {

    @GET("/api/v1/auctions/this-week")
    suspend fun getThisWeek(
        @Query("name") name: String,
        @Query("grade") grade: String,
        @Query("quantity") quantity: String,
        @Query("unit") unit: String
    ): List<sim.farm2seoul.data.model.ThisWeekResponse>

    @GET("/api/v1/auctions/unit/average-prices/last-4-weeks")
    suspend fun getRecent4Week(
        @Query("name") name: String,
        @Query("grade") grade: String,
        @Query("quantity") quantity: String,
        @Query("unit") unit: String
    ): List<sim.farm2seoul.data.model.Recent4WeekResponse>

    @GET("/api/v1/auctions/average-prices/last-3-month")
    suspend fun getRecent3Month(
        @Query("name") name: String,
        @Query("grade") grade: String,
        @Query("quantity") quantity: String,
        @Query("unit") unit: String
    ): List<sim.farm2seoul.data.model.Recent3MonthResponse>
}