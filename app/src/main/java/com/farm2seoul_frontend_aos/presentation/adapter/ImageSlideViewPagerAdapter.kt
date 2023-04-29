package com.farm2seoul_frontend_aos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farm2seoul_frontend_aos.databinding.InformationImageSlideBinding

class ImageSlideViewPagerAdapter(var imageList: ArrayList<Int>) : RecyclerView.Adapter<ImageSlideViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = InformationImageSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int  = imageList.size

    inner class ViewHolder(private val binding: InformationImageSlideBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (imageResId: Int) {
            binding.imageSlide.setImageResource(imageResId)
        }
    }
}