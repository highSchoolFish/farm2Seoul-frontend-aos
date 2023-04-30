package sim.farm2seoul.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sim.farm2seoul.R
import sim.farm2seoul.data.repository.FavoritesSearchInterface
import sim.farm2seoul.databinding.FavoritesDetailFolderBinding

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