package com.farm2seoul_frontend_aos.data.model

import com.google.gson.annotations.SerializedName

data class ThisWeekResponse (
    @SerializedName("dayOfWeek")
    val dayOfWeek : String,
    @SerializedName("average")
    val average : Double
)