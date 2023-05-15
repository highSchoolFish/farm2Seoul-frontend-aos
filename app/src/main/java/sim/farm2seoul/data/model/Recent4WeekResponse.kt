package sim.farm2seoul.data.model

import com.google.gson.annotations.SerializedName

data class Recent4WeekResponse (
    @SerializedName("weekName")
    val weekName : String,
    @SerializedName("average")
    val averagePrice : Double
)