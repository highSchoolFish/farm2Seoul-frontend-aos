package sim.farm2seoul.data.model

import com.google.gson.annotations.SerializedName

data class Recent3MonthResponse (
    @SerializedName("month")
    val month : String,
    @SerializedName("average")
    val average : Double
)