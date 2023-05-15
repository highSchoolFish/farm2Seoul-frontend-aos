package sim.farm2seoul.data.repository

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class Recent4WeekFormatter : ValueFormatter() {
    private val days = arrayOf("4주전", "3주전", "2주전", "1주전")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}