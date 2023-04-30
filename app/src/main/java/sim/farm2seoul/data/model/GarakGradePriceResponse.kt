package sim.farm2seoul.data.model

import com.google.gson.annotations.SerializedName

data class GarakGradePriceResponse(
    @SerializedName("GarakGradePrice")
    val garakGradePrice: sim.farm2seoul.data.model.GarakGradePrice
)

data class GarakGradePrice(
    @SerializedName("row")
    val row: List<sim.farm2seoul.data.model.RowItems>
)

data class RowItems(
    @SerializedName("PUMNAME")
    val PUMNAME: String,
    @SerializedName("GRADENAME")
    val GRADENAME: String,
    @SerializedName("UNITQTY")
    val UNITQTY: Double,
    @SerializedName("UNITNAME")
    val UNITNAME: String,
    @SerializedName("MAXPRICE")
    val MAXPRICE: Double,
    @SerializedName("MINPRICE")
    val MINPRICE: Double,
    @SerializedName("AVGPRICE")
    val AVGPRICE: Double,
    @SerializedName("INVEST_DT")
    val INVEST_DT: String
)
