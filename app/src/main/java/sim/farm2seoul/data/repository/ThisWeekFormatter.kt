package sim.farm2seoul.data.repository

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class ThisWeekFormatter : ValueFormatter() {
    private val days = arrayOf("월", "화", "수", "목", "금", "토")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}