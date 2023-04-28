package com.farm2seoul_frontend_aos.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.data.repository.Farm2SeoulApplication

class FavoritesDetailRecyclerViewAdapter(private val context : Context) : RecyclerView.Adapter<FavoritesDetailRecyclerViewAdapter.ViewHolder>() {
    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesDetailRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorites_detail_folder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FavoritesDetailRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var bool = false
        private val folder_text : TextView = itemView.findViewById(R.id.folder_text)
        private val folder_image : ImageView = itemView.findViewById(R.id.folder_image)

        fun bind(item: String) {
            folder_text.text = item
            bool = Farm2SeoulApplication.preference.getPreference(item).toBoolean()
            if (bool) folder_image.setImageResource(R.drawable.folder)
            else folder_image.setImageResource(R.drawable.unfolder)

            folder_image.setOnClickListener {
                 if (bool) {
                    folder_image.setImageResource(R.drawable.unfolder)
                    bool = false
                     Farm2SeoulApplication.preference.deletePreference(item)
                }else {
                    folder_image.setImageResource(R.drawable.folder)
                    bool = true
                    Farm2SeoulApplication.preference.setPreference(item, "true")
                }
            }
        }
    }
}