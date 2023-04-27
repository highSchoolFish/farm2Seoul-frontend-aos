package com.farm2seoul_frontend_aos.data.model

import com.google.gson.annotations.SerializedName

data class Recent3MonthResponse (
    @SerializedName("month")
    val month : String,
    @SerializedName("average")
    val average : Double
)