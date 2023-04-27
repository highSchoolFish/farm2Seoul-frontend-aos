package com.farm2seoul_frontend_aos.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.farm2seoul_frontend_aos.databinding.FragmentDailyAuctionBinding
import com.farm2seoul_frontend_aos.presentation.activity.MainActivity
import com.farm2seoul_frontend_aos.presentation.adapter.PagingDataRecyclerViewAdapter
import com.farm2seoul_frontend_aos.presentation.viewmodel.DailyAuctionViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DailyAuction : Fragment() {
    private var mBinding: FragmentDailyAuctionBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerViewAdapter: PagingDataRecyclerViewAdapter
    private val fragment1ViewModel: DailyAuctionViewModel by viewModels()
    private var error = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDailyAuctionBinding.inflate(inflater, container, false)

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
            fragment1ViewModel.date()
            (activity as MainActivity).refreshFragment(this)
        }

        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        recyclerViewAdapter = PagingDataRecyclerViewAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter

        lifecycleScope.launch {
            fragment1ViewModel.getData.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }

        /*lifecycleScope.launch {

            fragment1ViewModel.auctionResult.observe(viewLifecycleOwner) {
                recyclerViewAdapter.submitData(lifecycle, it)
            }

            fragment1ViewModel.getSearchData()
        }*/
    }
}