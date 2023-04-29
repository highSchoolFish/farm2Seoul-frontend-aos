package com.farm2seoul_frontend_aos.data.repository

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class Recent3MonthFormatter : ValueFormatter() {
    private val days = arrayOf("지지난달", "지난달", "이번달")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}