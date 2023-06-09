package sim.farm2seoul.data.repository

import sim.farm2seoul.data.model.GarakGradePriceResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenApiRetrofitInterface {
    @GET("/766c476c676b6e793132345770716c57/json/GarakGradePrice/{start_index}/{end_index}/{pum_name}")
    suspend fun getGarakGradePrice(
        @Path("start_index") start_index: String,
        @Path("end_index") end_index: String,
        @Path("pum_name") pum_name: String
    ): sim.farm2seoul.data.model.GarakGradePriceResponse
}