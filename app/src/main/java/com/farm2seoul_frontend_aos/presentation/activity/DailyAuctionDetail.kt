package com.farm2seoul_frontend_aos.presentation.activity

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.databinding.DailyAuctionDetailBinding
import com.farm2seoul_frontend_aos.presentation.viewmodel.DailyAuctionDetailViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.DecimalFormat

class DailyAuctionDetail : AppCompatActivity() {
    private var mBinding: DailyAuctionDetailBinding? = null
    private val binding get() = mBinding!!
    private val dailyAuctionDetailViewModel: DailyAuctionDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DailyAuctionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initChart(binding.chart)
        setData(binding.chart)
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    private fun initData() {
        setSupportActionBar(binding.topAppBar)

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

        val date = intent.getStringExtra("INVEST_DT")
        val year = date?.substring(0, 4)
        val month = date?.substring(4, 6)
        val day = date?.substring(6, 8)
        val string = "$year.$month.$day"
        binding.topAppBar.title = string

        val format = DecimalFormat("#,###")
        binding.dailyAuctionPumname.text = intent.getStringExtra("PUMNAME")
        binding.dailyAuctionUnitqty.text = intent.getStringExtra("UNITQTY")
        binding.dailyAuctionUnitname.text = intent.getStringExtra("UNITNAME")
        binding.dailyAuctionHighprice.text = format.format(intent.getStringExtra("MAXPRICE").toString().split(".")[0].toInt())
        binding.dailyAuctionLowprice.text = format.format(intent.getStringExtra("MINPRICE").toString().split(".")[0].toInt())
        binding.dailyAuctionAvgprice.text = format.format(intent.getStringExtra("AVGPRICE").toString().split(".")[0].toInt())
        when (intent.getStringExtra("GRADENAME")) {
            "특" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.special_medal)
            "상" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.high_medal)
            "중" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.middle_medal)
            "하" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.low_medal)
        }
    }

    private fun initChart(chart : LineChart) {
        //그래프에 수치 구분선
        chart.setDrawGridBackground(true)
        //드래그 여부
        chart.isDragEnabled = false
        //X축으로 늘리기
        chart.isScaleXEnabled = false
        //Y축으로 늘리기
        chart.isScaleYEnabled = false
        //X축 노출 여부
        chart.xAxis.isEnabled = false
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
        chart.xAxis.setDrawAxisLine(true)
        //X축 grid 노출
        chart.xAxis.setDrawGridLines(false)
    }

    private fun setData(chart : LineChart) {
        chart.setScaleEnabled(false)
        val title = "거래 현황"
        val valueList = ArrayList<Entry>()

        //temporary data
        for (i in 0 until 5) {
            valueList.add(Entry(i.toFloat(), i * 100f))
        }

        val lineDataSet = LineDataSet(valueList, title)
        lineDataSet.setColors(
            Color.rgb(207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187),
            Color.rgb(118, 174, 175), Color.rgb(42, 109, 130))

        val data = LineData(lineDataSet)
        chart.data = data
        chart.invalidate()
    }
}