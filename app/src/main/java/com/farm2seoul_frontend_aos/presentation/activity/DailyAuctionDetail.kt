package com.farm2seoul_frontend_aos.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.databinding.DailyAuctionDetailBinding
import com.farm2seoul_frontend_aos.presentation.viewmodel.DailyAuctionDetailViewModel

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

        val date = intent.getStringExtra("INVEST_DT")
        val year = date?.substring(0, 4)
        val month = date?.substring(4, 6)
        val day = date?.substring(6, 8)
        val string = "$year.$month.$day"
        binding.topAppBar.title = string

        binding.dailyAuctionPumname.text = intent.getStringExtra("PUMNAME")
        binding.dailyAuctionUnitqty.text = intent.getStringExtra("UNITQTY")
        binding.dailyAuctionUnitname.text = intent.getStringExtra("UNITNAME")
        binding.dailyAuctionHighprice.text = intent.getStringExtra("MAXPRICE")
        binding.dailyAuctionLowprice.text = intent.getStringExtra("MINPRICE")
        binding.dailyAuctionAvgprice.text = intent.getStringExtra("AVGPRICE")
        when (intent.getStringExtra("GRADENAME")) {
            "특" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.special_medal)
            "상" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.high_medal)
            "중" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.middle_medal)
            "하" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.low_medal)
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}