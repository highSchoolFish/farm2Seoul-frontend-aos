package sim.farm2seoul.data.repository

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class Recent4WeekFormatter : ValueFormatter() {
    private val days = arrayOf("지지지난주", "지지난주", "지난주", "이번주")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}