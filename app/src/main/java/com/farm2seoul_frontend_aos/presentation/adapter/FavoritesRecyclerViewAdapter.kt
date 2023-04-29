package com.farm2seoul_frontend_aos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.data.repository.FavoritesSearchInterface
import com.farm2seoul_frontend_aos.databinding.FavoritesDetailFolderBinding

class FavoritesRecyclerViewAdapter(private val favoritesSearchInterface : FavoritesSearchInterface) : RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder>()  {
    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoritesDetailFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding : FavoritesDetailFolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.folderText.text = item
            binding.folderImage.setImageResource(R.drawable.folder)

            binding.folderImage.setOnClickListener {
                favoritesSearchInterface.itemSearch(item)
            }
        }
    }
}