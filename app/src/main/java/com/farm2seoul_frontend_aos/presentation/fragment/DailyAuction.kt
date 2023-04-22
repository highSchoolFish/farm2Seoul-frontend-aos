package com.farm2seoul_frontend_aos.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.farm2seoul_frontend_aos.databinding.DailyAuctionBinding
import com.farm2seoul_frontend_aos.presentation.activity.MainActivity
import com.farm2seoul_frontend_aos.presentation.adapter.PagingDataRecyclerViewAdapter
import com.farm2seoul_frontend_aos.presentation.viewmodel.DailyAuctionViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DailyAuction : Fragment() {
    private var mBinding: DailyAuctionBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerViewAdapter: PagingDataRecyclerViewAdapter
    private val fragment1ViewModel: DailyAuctionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DailyAuctionBinding.inflate(inflater, container, false)

        fragment1ViewModel.date()
        fragment1ViewModel.date.observe(viewLifecycleOwner, Observer {
            binding.auctionDate.text = it
        })

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