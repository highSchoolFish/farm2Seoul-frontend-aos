package com.farm2seoul_frontend_aos.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.farm2seoul_frontend_aos.presentation.adapter.FragmentViewPagerAdapter
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.databinding.ActivityMainBinding
import com.farm2seoul_frontend_aos.presentation.fragment.DailyAuction
import com.farm2seoul_frontend_aos.presentation.fragment.Information
import com.farm2seoul_frontend_aos.presentation.fragment.Favorites
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

        binding.searchButton.setOnClickListener {
            mainActivityViewModel.search(binding.editText.text.toString())
            binding.fragmentLayout.currentItem = 0
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    /** ViewPager + TabLayout + Fragment **/
    private fun fragmentManage() {
        val fragmentList = listOf(DailyAuction(), Information(), Favorites())

        val tabName = listOf("일별경매", "정보 마당", "즐겨찾기")
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
                when (tab?.position) {
                    0 -> {
                        val view = fragmentList[0].view?.findViewById<RecyclerView>(R.id.daily_auction_recycler_view)
                        view?.smoothScrollToPosition(0)
                    }

                    1 -> {
                        val view = fragmentList[1].view?.findViewById<ScrollView>(R.id.info_scroll_view)
                        view?.fullScroll(ScrollView.FOCUS_UP)
                    }

                    2 -> {
                        val view = fragmentList[2].view?.findViewById<RecyclerView>(R.id.favorite_recycler_view)
                        view?.smoothScrollToPosition(0)
                    }
                }
            }
        })
    }

    /** Fragment 새로고침 **/
    fun refreshFragment(fragment: Fragment) {
        viewPagerAdapter.refreshFragment(0, fragment)
    }
}