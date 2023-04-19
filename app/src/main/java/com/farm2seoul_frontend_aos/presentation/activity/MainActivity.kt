package com.farm2seoul_frontend_aos.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.farm2seoul_frontend_aos.presentation.adapter.FragmentViewPagerAdapter
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.databinding.ActivityMainBinding
import com.farm2seoul_frontend_aos.presentation.fragment.Fragment1
import com.farm2seoul_frontend_aos.presentation.fragment.Fragment2
import com.farm2seoul_frontend_aos.presentation.fragment.Fragment3
import com.google.android.material.tabs.TabLayout

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

/*        viewPagerAdapter = FragmentViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)
        binding.fragmentLayout.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.fragmentLayout) {tab, pos ->
            tab.text = tabName[pos]
            tab.setIcon(tabIcon[pos])
        }.attach()*/

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

        /*binding.fragmentLayout.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val recyclerView = binding.fragmentLayout.getChildAt(0) as RecyclerView
                val view = recyclerView.layoutManager?.findViewByPosition(position)

                view?.post {
                    val wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
                    val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    view.measure(wMeasureSpec, hMeasureSpec)

                    if (binding.fragmentLayout.layoutParams.height != view.measuredHeight) {
                        binding.fragmentLayout.layoutParams = (binding.fragmentLayout.layoutParams).also { lp -> lp.height = view.measuredHeight }
                        binding.scrollLayout.post {
                            binding.scrollLayout.requestLayout()
                        }
                    }
                }
            }
        })*/
    }

    private fun replaceView(tab: Fragment) {
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, it).commit()
        }
    }
}