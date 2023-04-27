package com.farm2seoul_frontend_aos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.farm2seoul_frontend_aos.R

class ImageSlideViewPagerAdapter(var imageList: ArrayList<Int>) : RecyclerView.Adapter<ImageSlideViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ImageSlideViewPagerAdapter.ViewHolder, position: Int) {
        holder.imageList.setImageResource(imageList[position])
    }

    override fun getItemCount(): Int  = imageList.size

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.information_image_slide, parent, false)) {
        val imageList = itemView.findViewById<ImageView>(R.id.image_slide)
    }
}