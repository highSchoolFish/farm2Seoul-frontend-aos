package com.farm2seoul_frontend_aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import androidx.core.widget.NestedScrollView
import com.farm2seoul_frontend_aos.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var viewPagerAdapter: FragmentViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentManage()
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    private fun fragmentManage() {
        val fragmentList = listOf(Fragment1(), Fragment2(), Fragment3())
        val tabName = listOf("일별경매", "가게별 정보", "즐겨찾기")
        val tabIcon = listOf(R.drawable.daily_auction, R.drawable.store_info, R.drawable.star)

        viewPagerAdapter = FragmentViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)
        binding.fragmentLayout.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.fragmentLayout) {tab, pos ->
            tab.text = tabName[pos]
            tab.setIcon(tabIcon[pos])
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                binding.scrollLayout.fullScroll(NestedScrollView.FOCUS_UP)
            }
        })
    }
}