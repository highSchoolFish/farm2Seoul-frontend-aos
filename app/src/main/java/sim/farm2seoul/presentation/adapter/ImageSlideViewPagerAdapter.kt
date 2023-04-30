package sim.farm2seoul.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sim.farm2seoul.databinding.InformationImageSlideBinding

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