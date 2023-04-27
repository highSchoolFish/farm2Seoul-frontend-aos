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

        val name = intent.getStringExtra("PUMNAME").toString()
        val grade = intent.getStringExtra("GRADENAME").toString()
        val quantity = intent.getStringExtra("UNITQTY").toString() + "0"
        val unit = intent.getStringExtra("UNITNAME").toString()
        dailyAuctionDetailViewModel.initChart(binding.chart)
        dailyAuctionDetailViewModel.setThisWeekData(binding.chart, name, grade, quantity, unit)

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.this_week -> {
                        dailyAuctionDetailViewModel.setThisWeekData(binding.chart, name, grade, quantity, unit)
                    }

                    R.id.recent_4week -> {
                        dailyAuctionDetailViewModel.setRecent4WeekData(binding.chart, name, grade, quantity, unit)
                    }

                    R.id.recent_3month -> {
                        dailyAuctionDetailViewModel.setRecent3MonthData(binding.chart, name, grade, quantity, unit)
                    }
                }
            }
        }
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
}