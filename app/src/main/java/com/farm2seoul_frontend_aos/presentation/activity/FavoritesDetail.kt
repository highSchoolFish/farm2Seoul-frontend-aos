package com.farm2seoul_frontend_aos.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farm2seoul_frontend_aos.databinding.FavoritesDetailBinding
import com.farm2seoul_frontend_aos.presentation.adapter.FavoritesDetailRecyclerViewAdapter
import com.opencsv.CSVReader
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStreamReader

@AndroidEntryPoint
class FavoritesDetail : AppCompatActivity() {
    private var mBinding: FavoritesDetailBinding? = null
    private val binding get() = mBinding!!
    lateinit var favoritesDetailRecyclerViewAdapter: FavoritesDetailRecyclerViewAdapter
    var datas = mutableListOf<String>()
    var dataString = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = FavoritesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

        binding.saveButton.setOnClickListener {
            finish()
        }

        initRecycler()
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    /** CSV 파일 로드, RecyclerView 어댑터 연결 **/
    private fun initRecycler() {
        favoritesDetailRecyclerViewAdapter = FavoritesDetailRecyclerViewAdapter()
        binding.favoriteRecyclerView.adapter = favoritesDetailRecyclerViewAdapter

        val assetManager = this.assets
        val inputStream = assetManager.open("농수산물.csv")

        val reader = CSVReader(InputStreamReader(inputStream))

        var nextLine: Array<String>? = arrayOf()
        while (nextLine.apply {
                nextLine = reader.readNext()
            } != null) {
            val str = nextLine?.toList().toString()
            dataString.add(str.substring(1, str.length - 1))
        }

        datas.apply {
            addAll(dataString)
            remove(dataString[0])
            remove(dataString[dataString.size - 1])
            favoritesDetailRecyclerViewAdapter.datas = datas
            favoritesDetailRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}