package com.farm2seoul_frontend_aos.data.model

import com.google.gson.annotations.SerializedName

data class Recent4WeekResponse (
    @SerializedName("weekName")
    val weekName : String,
    @SerializedName("averagePrice")
    val averagePrice : Double
)