package sim.farm2seoul.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import sim.farm2seoul.R
import sim.farm2seoul.databinding.DailyAuctionDetailBinding
import sim.farm2seoul.presentation.viewmodel.DailyAuctionDetailViewModel
import java.text.DecimalFormat

class DailyAuctionDetail : AppCompatActivity() {
    private var mBinding: DailyAuctionDetailBinding? = null
    private val binding get() = mBinding!!
    private val dailyAuctionDetailViewModel: DailyAuctionDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DailyAuctionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

        initData()
        initChartData()
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    /** 인텐트 데이터 설정 **/
    private fun initData() {
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

    /** 차트 데이터 설정 **/
    private fun initChartData() {
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
}