package com.farm2seoul_frontend_aos.presentation.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.core.view.size
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farm2seoul_frontend_aos.data.model.ThisWeekResponse
import com.farm2seoul_frontend_aos.data.repository.Farm2SeoulRetrofitBuilder
import com.farm2seoul_frontend_aos.data.repository.Farm2SeoulRetrofitInterface
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyAuctionDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val retrofitInterface: Farm2SeoulRetrofitInterface = Farm2SeoulRetrofitBuilder.create()

    fun setThisWeekData(chart : LineChart, name: String, grade: String, quantity: String, unit: String) {
        viewModelScope.launch {
            chart.setScaleEnabled(false)

            val title = "거래 현황"
            val valueList = ArrayList<Entry>()
            val response = retrofitInterface.getThisWeek(name, grade, quantity, unit)

            response.forEachIndexed { index, value ->
                valueList.add(Entry(index.toFloat(), value.average.toFloat()))
            }

            val lineDataSet = LineDataSet(valueList, title)
            /*lineDataSet.setColors(
                Color.rgb(207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187),
                Color.rgb(118, 174, 175), Color.rgb(42, 109, 130))*/

            val data = LineData(lineDataSet)
            chart.data = data
            chart.invalidate()
        }
    }

    fun setRecent4WeekData(chart : LineChart, name: String, grade: String, quantity: String, unit: String) {
        viewModelScope.launch {
            chart.setScaleEnabled(false)

            val title = "거래 현황"
            val valueList = ArrayList<Entry>()
            val response = retrofitInterface.getRecent4Week(name, grade, quantity, unit)

            response.forEachIndexed { index, value ->
                valueList.add(Entry(index.toFloat(), value.averagePrice.toFloat()))
            }

            val lineDataSet = LineDataSet(valueList, title)
            /*lineDataSet.setColors(
                Color.rgb(207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187),
                Color.rgb(118, 174, 175), Color.rgb(42, 109, 130))*/

            val data = LineData(lineDataSet)
            chart.data = data
            chart.invalidate()
        }
    }

    fun setRecent3MonthData(chart : LineChart, name: String, grade: String, quantity: String, unit: String) {
        viewModelScope.launch {
            chart.setScaleEnabled(false)

            val title = "거래 현황"
            val valueList = ArrayList<Entry>()
            val response = retrofitInterface.getRecent3Month(name, grade, quantity, unit)

            response.forEachIndexed { index, value ->
                valueList.add(Entry(index.toFloat(), value.average.toFloat()))
            }

            val lineDataSet = LineDataSet(valueList, title)
            /*lineDataSet.setColors(
                Color.rgb(207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187),
                Color.rgb(118, 174, 175), Color.rgb(42, 109, 130))*/

            val data = LineData(lineDataSet)
            chart.data = data
            chart.invalidate()
        }
    }

    fun initChart(chart : LineChart) {
        //그래프에 수치 구분선
        chart.setDrawGridBackground(false)
        //드래그 여부
        chart.isDragEnabled = false
        //터치 여부
        chart.setTouchEnabled(false)
        //X축으로 늘리기
        chart.isScaleXEnabled = false
        //Y축으로 늘리기
        chart.isScaleYEnabled = false
        //X축 노출 여부
        chart.xAxis.isEnabled = true
        //Y축 왼쪽 노출
        chart.axisLeft.isEnabled = false
        //Y축 왼쪽 라인 노출
        chart.axisLeft.setDrawAxisLine(false)
        //Y축 왼쪽 라인 grid 노출
        chart.axisLeft.setDrawGridLines(false)
        //Y축 왼쪽 라인에 라벨 노출
        chart.axisLeft.setDrawLabels(false)
        //Y축 오른쪽 라인 노출
        chart.axisRight.setDrawAxisLine(false)
        //Y축 오른쪽 라인 grid 노출
        chart.axisRight.setDrawGridLines(false)
        //Y축 오른쪽 라인 사용
        chart.axisRight.isEnabled = false
        //X축 노출
        chart.xAxis.setDrawAxisLine(false)
        //X축 grid 노출
        chart.xAxis.setDrawGridLines(true)
        //라벨 설정
        chart.description.text = "(평균가 기준)"
        //라벨 위치 설정
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //간격 설정
        chart.xAxis.granularity = 1f
        //라벨 크기
        chart.xAxis.textSize = 10f
    }
}