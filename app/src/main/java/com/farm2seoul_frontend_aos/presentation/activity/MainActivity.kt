package com.farm2seoul_frontend_aos.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.farm2seoul_frontend_aos.presentation.adapter.FragmentViewPagerAdapter
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.databinding.ActivityMainBinding
import com.farm2seoul_frontend_aos.presentation.fragment.Fragment1
import com.farm2seoul_frontend_aos.presentation.fragment.Fragment2
import com.farm2seoul_frontend_aos.presentation.fragment.Fragment3
import com.farm2seoul_frontend_aos.presentation.viewmodel.MainActivityViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var viewPagerAdapter: FragmentViewPagerAdapter
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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

        /** ViewPager2 Fragment code */
        /*
        val tabName = listOf("일별경매", "가게별 정보", "즐겨찾기")
        val tabIcon = listOf(R.drawable.daily_auction, R.drawable.store_info, R.drawable.star)

        viewPagerAdapter = FragmentViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)
        binding.fragmentLayout.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.fragmentLayout) {tab, pos ->
            tab.text = tabName[pos]
            tab.setIcon(tabIcon[pos])
        }.attach()
        */

        replaceView(fragmentList[0])

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.scrollLayout.fullScroll(NestedScrollView.FOCUS_UP)
                when (tab?.position) {
                    0 -> replaceView(fragmentList[0])
                    1 -> replaceView(fragmentList[1])
                    2 -> replaceView(fragmentList[2])
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                binding.scrollLayout.fullScroll(NestedScrollView.FOCUS_UP)
            }
        })
    }

    private fun replaceView(tab: Fragment) {
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, it).commit()
        }
    }
}