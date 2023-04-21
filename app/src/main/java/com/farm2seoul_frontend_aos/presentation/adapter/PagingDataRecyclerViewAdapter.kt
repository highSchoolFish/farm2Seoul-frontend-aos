package com.farm2seoul_frontend_aos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.data.model.RowItems
import com.farm2seoul_frontend_aos.databinding.DailyAuctionCardBinding
import java.text.DecimalFormat

class PagingDataRecyclerViewAdapter() :
    PagingDataAdapter<RowItems, PagingDataRecyclerViewAdapter.ViewHolder>(ItemDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagingDataRecyclerViewAdapter.ViewHolder {
        val binding = DailyAuctionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingDataRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: DailyAuctionCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RowItems?) {
            item?.let {
                when (item.GRADENAME) {
                    "특" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.special_medal)
                    "상" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.high_medal)
                    "중" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.middle_medal)
                    "하" -> binding.dailyAuctionGradeImage.setImageResource(R.drawable.low_medal)
                }
                val qty = item.UNITQTY.toString() + "/"
                binding.dailyAuctionUnitqty.text = qty
                binding.dailyAuctionUnitname.text = item.UNITNAME
                binding.dailyAuctionPumname.text = item.PUMNAME
                val won = item.AVGPRICE.toString().split(".")
                val format = DecimalFormat("#,###")
                val string = format.format(won[0].toInt()) + "원"
                binding.dailyAuctionAvgprice.text = string
            }
        }
    }

    //ItemDiffCallback : DiffUtil.Callback 상속하는 콜백 클래스
    object ItemDiffCallback : DiffUtil.ItemCallback<RowItems>() {
        //두 객체를 비교해 링크가 같은지 체크
        override fun areItemsTheSame(oldItem: RowItems, newItem: RowItems): Boolean {
            return oldItem.PUMNAME == newItem.PUMNAME && oldItem.GRADENAME == newItem.GRADENAME
        }

        //두 객체의 내용이 같은지 확인
        override fun areContentsTheSame(oldItem: RowItems, newItem: RowItems): Boolean {
            return oldItem == newItem
        }

    }
}