package com.farm2seoul_frontend_aos.presentation.fragment

import android.content.Intent
import android.os.Bundle
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
import com.farm2seoul_frontend_aos.presentation.adapter.FavoritesRecyclerViewAdapter
import com.farm2seoul_frontend_aos.presentation.viewmodel.MainActivityViewModel

class Favorites : Fragment(), FavoritesSearchInterface {
    private var mBinding: FragmentFavoritesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var favoritesRecyclerViewAdapter: FavoritesRecyclerViewAdapter
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private var datas = mutableListOf<String>()
    var searchItem = ""
    private lateinit var viewPager: ViewPager2

    /** 프래그먼트 onResume시 recyclerView 어댑터 갱신 **/
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

    /** 즐겨찾기 탭 RecyclerView 어댑터 **/
    private fun initRecycler() {
        favoritesRecyclerViewAdapter = FavoritesRecyclerViewAdapter(this)
        binding.favoriteRecyclerView.adapter = favoritesRecyclerViewAdapter

        //SharedPreference에서 key 값만 가져와 Map<>으로 저장
        val map = Farm2SeoulApplication.preference.getAllPreference().keys

        //Map<>의 값들을 datas에 추가
        datas.apply {
            for (elem in map) {
                add(elem)
            }
        }

        favoritesRecyclerViewAdapter.datas = datas
        favoritesRecyclerViewAdapter.notifyDataSetChanged()
    }

    /** 즐겨찾기 탭 RecyclerView 어댑터의 ViewHolder에서 item 이름 가져와 일별 경매 탭에서 검색 결과 출력 **/
    override fun itemSearch(item: String) {
        searchItem = item
        mainActivityViewModel.search(searchItem)
        val view = activity?.findViewById<ViewPager2>(R.id.fragment_layout)
        if (view != null) viewPager = view
        view?.currentItem = 0
    }
}