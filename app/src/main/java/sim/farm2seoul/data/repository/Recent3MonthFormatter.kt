package sim.farm2seoul.data.repository

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class Recent3MonthFormatter : ValueFormatter() {
    private val days = arrayOf("3달전", "2달전", "1달전")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}