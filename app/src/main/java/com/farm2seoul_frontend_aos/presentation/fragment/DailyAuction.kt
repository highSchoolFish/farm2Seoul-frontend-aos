package com.farm2seoul_frontend_aos.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.farm2seoul_frontend_aos.databinding.FragmentDailyAuctionBinding
import com.farm2seoul_frontend_aos.presentation.activity.MainActivity
import com.farm2seoul_frontend_aos.presentation.adapter.PagingDataRecyclerViewAdapter
import com.farm2seoul_frontend_aos.presentation.viewmodel.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DailyAuction : Fragment() {
    private var mBinding: FragmentDailyAuctionBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerViewAdapter: PagingDataRecyclerViewAdapter
    private val fragment1ViewModel: MainActivityViewModel by activityViewModels()
    private var error = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDailyAuctionBinding.inflate(inflater, container, false)

        //검색 결과 Observing -> RecyclerView 어댑터 설정
        fragment1ViewModel.getSearch().observe(viewLifecycleOwner, Observer {
            if (it != "") setSearchAdapter()
            else setBasicAdapter()
        })

        //날짜 통신
        fragment1ViewModel.date()

        //날짜 통신 값 Observe
        fragment1ViewModel.date.observe(viewLifecycleOwner, Observer {
            binding.auctionDate.text = it
        })

        //날짜 통신시 Exception 발생시 Error Fragment 호출
        fragment1ViewModel.error.observe(viewLifecycleOwner, Observer {
            error = it

            if (error) {
                binding.auctionData.visibility = View.INVISIBLE
                binding.nonAuctionData.visibility = View.VISIBLE
            }
            else {
                binding.auctionData.visibility = View.VISIBLE
                binding.nonAuctionData.visibility = View.INVISIBLE
            }
        })

        //Retry 클릭시 프래그먼트 재호출
        binding.retry.setOnClickListener {
            setBasicAdapter()
            fragment1ViewModel.date()
            (activity as MainActivity).refreshFragment(this)
        }

        return binding.root
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    /** 전체 일별 경매 목록 RecyclerView 어댑터 **/
    private fun setBasicAdapter() {
        recyclerViewAdapter = PagingDataRecyclerViewAdapter()
        binding.dailyAuctionRecyclerView.adapter = recyclerViewAdapter

        lifecycleScope.launch {
            fragment1ViewModel.getData.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    /** 검색 일별 경매 목록 RecyclerView 어댑터 **/
    private fun setSearchAdapter() {
        recyclerViewAdapter = PagingDataRecyclerViewAdapter()
        binding.dailyAuctionRecyclerView.adapter = recyclerViewAdapter

        lifecycleScope.launch {
            fragment1ViewModel.getSearchData.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}