package com.farm2seoul_frontend_aos.presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.data.repository.Farm2SeoulApplication
import com.farm2seoul_frontend_aos.data.repository.FavoritesSearchInterface
import com.farm2seoul_frontend_aos.databinding.FragmentFavoritesBinding
import com.farm2seoul_frontend_aos.presentation.activity.FavoritesDetail
import com.farm2seoul_frontend_aos.presentation.activity.MainActivity
import com.farm2seoul_frontend_aos.presentation.adapter.FavoritesRecyclerViewAdapter
import com.farm2seoul_frontend_aos.presentation.viewmodel.MainActivityViewModel

class Favorites : Fragment(), FavoritesSearchInterface {
    private var mBinding: FragmentFavoritesBinding? = null
    private val binding get() = mBinding!!
    lateinit var favoritesRecyclerViewAdapter: FavoritesRecyclerViewAdapter
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    lateinit var mainActivity: MainActivity
    private var datas = mutableListOf<String>()
    var searchItem = ""
    private lateinit var viewPager : ViewPager2

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        datas.removeAll(datas)
        initRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.addButton.setOnClickListener {
            val intent = Intent(context, FavoritesDetail::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    private fun initRecycler() {
        favoritesRecyclerViewAdapter = FavoritesRecyclerViewAdapter(mainActivity, this)
        binding.favoriteRecyclerView.adapter = favoritesRecyclerViewAdapter

        val map = Farm2SeoulApplication.preference.getAllPreference().keys

        datas.apply {
            for (elem in map) {
                add(elem)
            }
        }
        favoritesRecyclerViewAdapter.datas = datas
        favoritesRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun itemSearch(item: String) {
        searchItem = item
        mainActivityViewModel.search(searchItem)
        val view = activity?.findViewById<ViewPager2>(R.id.fragment_layout)
        if (view != null) viewPager = view
        view?.currentItem = 0
        Log.d("logd", searchItem)
    }

}