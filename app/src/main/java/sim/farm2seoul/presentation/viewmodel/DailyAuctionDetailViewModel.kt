package sim.farm2seoul.presentation.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sim.farm2seoul.data.repository.*
import javax.inject.Inject

@HiltViewModel
class DailyAuctionDetailViewModel @Inject constructor() : ViewModel() {

    private val retrofitInterface: Farm2SeoulRetrofitInterface = Farm2SeoulRetrofitBuilder.create()

    /** 이번주 가격 변동 데이터 **/
    fun setThisWeekData(
        chart: LineChart,
        name: String,
        grade: String,
        quantity: String,
        unit: String
    ) {
        viewModelScope.launch {
            chart.setScaleEnabled(false)

            val title = "거래 현황"
            val valueList = ArrayList<Entry>()
            val response = retrofitInterface.getThisWeek(name, grade, quantity, unit)

            response.forEachIndexed { index, value ->
                valueList.add(Entry(index.toFloat(), value.average.toFloat()))
            }

            val lineDataSet = LineDataSet(valueList, title)
            lineDataSet.setColors(Color.parseColor("#2C5044"))
            lineDataSet.setCircleColor(Color.parseColor("#2C5044"))
            lineDataSet.lineWidth = 3f
            lineDataSet.circleRadius = 5f
            lineDataSet.circleHoleRadius = 3f
            lineDataSet.valueTextSize = 15f
            chart.xAxis.valueFormatter = ThisWeekFormatter()

            val data = LineData(lineDataSet)
            chart.data = data
            chart.invalidate()
        }
    }

    /** 최근 4주간 데이터 **/
    fun setRecent4WeekData(
        chart: LineChart,
        name: String,
        grade: String,
        quantity: String,
        unit: String
    ) {
        viewModelScope.launch {
            chart.setScaleEnabled(false)

            val title = "거래 현황"
            val valueList = ArrayList<Entry>()
            val response = retrofitInterface.getRecent4Week(name, grade, quantity, unit)

            response.forEachIndexed { index, value ->
                valueList.add(Entry(index.toFloat(), value.averagePrice.toFloat()))
            }

            val lineDataSet = LineDataSet(valueList, title)
            lineDataSet.setColors(Color.parseColor("#2C5044"))
            lineDataSet.setCircleColor(Color.parseColor("#2C5044"))
            lineDataSet.lineWidth = 3f
            lineDataSet.circleRadius = 5f
            lineDataSet.circleHoleRadius = 3f
            lineDataSet.valueTextSize = 15f
            chart.xAxis.valueFormatter = Recent4WeekFormatter()

            val data = LineData(lineDataSet)
            chart.data = data
            chart.invalidate()
        }
    }

    /** 최근 3개월간 데이터 **/
    fun setRecent3MonthData(
        chart: LineChart,
        name: String,
        grade: String,
        quantity: String,
        unit: String
    ) {
        viewModelScope.launch {
            chart.setScaleEnabled(false)

            val title = "거래 현황"
            val valueList = ArrayList<Entry>()
            val response = retrofitInterface.getRecent3Month(name, grade, quantity, unit)

            response.forEachIndexed { index, value ->
                valueList.add(Entry(index.toFloat(), value.average.toFloat()))
            }

            val lineDataSet = LineDataSet(valueList, title)
            lineDataSet.setColors(Color.parseColor("#2C5044"))
            lineDataSet.setCircleColor(Color.parseColor("#2C5044"))
            lineDataSet.lineWidth = 3f
            lineDataSet.circleRadius = 5f
            lineDataSet.circleHoleRadius = 3f
            lineDataSet.valueTextSize = 15f
            chart.xAxis.valueFormatter = Recent3MonthFormatter()

            val data = LineData(lineDataSet)
            chart.data = data
            chart.invalidate()
        }
    }

    /** 차트 설정 **/
    fun initChart(chart: LineChart) {
        chart.run {
            //그래프에 수치 구분선
            setDrawGridBackground(false)
            //드래그 여부
            isDragEnabled = false
            //터치 여부
            setTouchEnabled(false)
            //X축으로 늘리기
            isScaleXEnabled = false
            //Y축으로 늘리기
            isScaleYEnabled = false
            //라벨 설정
            description.text = "(평균가 기준)"
            //라벨 사이즈 설정
            description.textSize = 13f
            //데이터 없을 때 나오는 텍스트
            setNoDataText("데이터가 존재하지 않습니다.")
            //y축의 자동 스케일링 활성화
            isAutoScaleMinMaxEnabled = true

            xAxis.run {
                //X축 노출 여부
                isEnabled = true
                //X축 노출
                setDrawAxisLine(false)
                //X축 grid 노출
                setDrawGridLines(true)
                //라벨 위치 설정
                position = XAxis.XAxisPosition.BOTTOM
                //간격 설정
                granularity = 1f
                //라벨 크기
                textSize = 15f
                //라벨 색상
                textColor = Color.parseColor("#2C5044")
            }

            axisLeft.run {
                //Y축 왼쪽 노출
                isEnabled = false
                //Y축 왼쪽 라인 노출
                setDrawAxisLine(false)
                //Y축 왼쪽 라인 grid 노출
                setDrawGridLines(false)
                //Y축 왼쪽 라인에 라벨 노출
                setDrawLabels(false)
            }

            axisRight.run {
                //Y축 오른쪽 라인 노출
                setDrawAxisLine(false)
                //Y축 오른쪽 라인 grid 노출
                setDrawGridLines(false)
                //Y축 오른쪽 라인 사용
                isEnabled = false
            }
        }
    }
}