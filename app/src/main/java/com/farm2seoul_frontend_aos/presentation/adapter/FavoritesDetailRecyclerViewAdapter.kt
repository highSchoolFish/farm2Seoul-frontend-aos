package com.farm2seoul_frontend_aos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.data.repository.Farm2SeoulApplication
import com.farm2seoul_frontend_aos.databinding.FavoritesDetailFolderBinding

class FavoritesDetailRecyclerViewAdapter() : RecyclerView.Adapter<FavoritesDetailRecyclerViewAdapter.ViewHolder>() {
    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoritesDetailFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding: FavoritesDetailFolderBinding) : RecyclerView.ViewHolder(binding.root) {
        private var bool = false

        fun bind(item: String) {
            binding.folderText.text = item
            bool = Farm2SeoulApplication.preference.getPreference(item).toBoolean()
            if (bool) binding.folderImage.setImageResource(R.drawable.folder)
            else binding.folderImage.setImageResource(R.drawable.unfolder)

            binding.folderImage.setOnClickListener {
                 if (bool) {
                     binding.folderImage.setImageResource(R.drawable.unfolder)
                     bool = false
                     Farm2SeoulApplication.preference.deletePreference(item)
                 } else {
                     binding.folderImage.setImageResource(R.drawable.folder)
                     bool = true
                     Farm2SeoulApplication.preference.setPreference(item, "true")
                }
            }
        }
    }
}