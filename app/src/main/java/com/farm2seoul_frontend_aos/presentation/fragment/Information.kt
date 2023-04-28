package com.farm2seoul_frontend_aos.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.databinding.FragmentInfomationBinding
import com.farm2seoul_frontend_aos.presentation.activity.DailyAuctionDetail
import com.farm2seoul_frontend_aos.presentation.activity.InformationDetail
import com.farm2seoul_frontend_aos.presentation.adapter.ImageSlideViewPagerAdapter

class Information : Fragment(), View.OnClickListener {
    private var mBinding: FragmentInfomationBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentInfomationBinding.inflate(inflater, container, false)

        setImageSliderAdapter()

        binding.market1.setOnClickListener(this)
        binding.market2.setOnClickListener(this)
        binding.market3.setOnClickListener(this)
        binding.market4.setOnClickListener(this)
        binding.market5.setOnClickListener(this)
        binding.market6.setOnClickListener(this)
        binding.market7.setOnClickListener(this)
        binding.market8.setOnClickListener(this)
        binding.market9.setOnClickListener(this)
        binding.market10.setOnClickListener(this)
        binding.market11.setOnClickListener(this)
        binding.market.setOnClickListener(this)
        binding.marketNotice.setOnClickListener(this)
        binding.marketChulha.setOnClickListener(this)
        binding.marketTime.setOnClickListener(this)
        binding.marketInuri.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    private val imageSlideList = ArrayList<Int>().apply {
        add(R.drawable.market_place1)
        add(R.drawable.market_place2)
        add(R.drawable.market_place3)
    }

    private fun setImageSliderAdapter() {
        binding.imageSliderViewpager.apply {
            adapter = ImageSlideViewPagerAdapter(imageSlideList)
            requestDisallowInterceptTouchEvent(true)
        }
        binding.imageSliderViewpagerIndicator.attachTo(binding.imageSliderViewpager)
    }

    override fun onClick(v: View?) {
        if(v != null) {
            when (v.id) {
                R.id.market1 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.egreenland.co.kr"))
                    startActivity(intent)
                }

                R.id.market2 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://newgp.nonghyup.com/index.jsp"))
                    startActivity(intent)
                }

                R.id.market3 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ejoongang.co.kr"))
                    startActivity(intent)
                }

                R.id.market4 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.donghwafp.com/web/Main.aspx"))
                    startActivity(intent)
                }

                R.id.market5 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkck.co.kr/index.aspx"))
                    startActivity(intent)
                }

                R.id.market6 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dagreen.co.kr/main/main.asp"))
                    startActivity(intent)
                }

                R.id.market7 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kdsusan.co.kr/main2/"))
                    startActivity(intent)
                }

                R.id.market8 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.suhyup.co.kr/suhyup/index.do"))
                    startActivity(intent)
                }

                R.id.market9 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.seoulgunhae.com"))
                    startActivity(intent)
                }

                R.id.market10 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sbbot.com/main/index.do"))
                    startActivity(intent)
                }

                R.id.market11 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ksfresh.co.kr"))
                    startActivity(intent)
                }

                R.id.market -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.garak.co.kr/main/main.do"))
                    startActivity(intent)
                }

                R.id.market_notice -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.garak.co.kr/bbs/selectPageListBbs.do?bbs_code=B0004"))
                    startActivity(intent)
                }

                R.id.market_chulha -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.garak.co.kr/chulha/indexChulha.do"))
                    startActivity(intent)
                }

                R.id.market_time -> {
                    val intent = Intent(context, InformationDetail::class.java)
                    startActivity(intent)
                }

                R.id.market_inuri -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://inuri30.garak.co.kr/main/main.do"))
                    startActivity(intent)
                }
            }
        }
    }
}