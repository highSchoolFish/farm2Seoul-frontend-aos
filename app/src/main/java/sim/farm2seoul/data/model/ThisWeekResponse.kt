package sim.farm2seoul.data.model

import com.google.gson.annotations.SerializedName

data class ThisWeekResponse (
    @SerializedName("dayOfWeek")
    val dayOfWeek : String,
    @SerializedName("average")
    val average : Double
)